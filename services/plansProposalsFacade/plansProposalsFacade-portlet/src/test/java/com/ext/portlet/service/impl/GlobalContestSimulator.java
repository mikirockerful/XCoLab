package com.ext.portlet.service.impl;

import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.*;
import com.ext.portlet.service.ProposalAttributeLocalService;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by Manuel Thurner on 22/09/14.
 */
public class GlobalContestSimulator {
    protected static XCoLabTest testInstance;
    private static long DAY_IN_MS = 1000 * 60 * 60 * 24;
    protected static SimpleDateFormat dateFormat;
    protected static Random random;

    protected int contestPhaseIdCount = 0;
    protected Map<Integer, List<Proposal>> sideProposals;
    protected List<Proposal> globalProposals;
    protected List<Integer> globalProposalsInLastPhase;
    protected Contest globalContest;
    protected List<Contest> sideContests;
    protected List<User> users;

    protected Map<Integer, List<User>> globalProposalsTeamMembers;
    protected Map<Integer, Map<Integer, List<User>>> sideProposalsTeamMembers;

    protected int amountOfUsers;
    protected double pointsToBeDistributed;
    protected boolean hasContestEnded;
    protected int amountOfGlobalProposals;
    protected int amountOfSideContests;
    protected int amountOfProposalsPerSideContest;
    protected double probabilityToLinkToOtherProposal;
    protected double probabilityOfBeingAdvancedToNextPhase;
    protected double probabilityOfAdditionalTeamMember;

    private List<ContestPhase> contestPhasesCreated;

    protected Map<Integer, List<Integer>> globalProposalLinksToGlobalProposals;
    protected Map<Integer, Map<Integer, List<Integer>>> globalProposalLinksToSideProposals;

    public static void initSimulatorWithTestEnvironment(XCoLabTest testInstance) {
        GlobalContestSimulator.testInstance = testInstance;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        random = new Random();

    }

    public GlobalContestSimulator() {
    }

    public void initializeContests(int amountOfUsers,
                              double pointsToBeDistributed,
                              boolean hasContestEnded,
                              int amountOfGlobalProposals,
                              int amountOfSideContests,
                              int amountOfProposalsPerSideContest,
                              double probabilityToLinkToOtherProposal,
                              double probabilityOfBeingAdvancedToNextPhase,
                              double probabilityOfAdditionalTeamMember
    ) throws SystemException, PortalException, ParseException {
        this.amountOfUsers = amountOfUsers;
        this.pointsToBeDistributed = pointsToBeDistributed;
        this.hasContestEnded = hasContestEnded;
        this.amountOfGlobalProposals = amountOfGlobalProposals;
        this.amountOfSideContests = amountOfSideContests;
        this.amountOfProposalsPerSideContest = amountOfProposalsPerSideContest;
        this.probabilityToLinkToOtherProposal = probabilityToLinkToOtherProposal;
        this.probabilityOfBeingAdvancedToNextPhase = probabilityOfBeingAdvancedToNextPhase;
        this.probabilityOfAdditionalTeamMember = probabilityOfAdditionalTeamMember;

        if (users == null || users.isEmpty()) {
            this.createUsers();
        }
        this.createGlobalContestAndProposals();
        this.createSideContestsAndProposals();
        this.createLinksBetweenProposals();
    }

    public void deleteContestsAndProposals() throws SystemException {
        //delete all contestPhases
        for (ContestPhase cp : contestPhasesCreated) {
            testInstance.contestPhaseLocalService.deleteContestPhase(cp);
        }
        contestPhasesCreated = null;

        //delete links between proposals
        List<ProposalAttribute> proposalAttributes = testInstance.proposalAttributeLocalService.getProposalAttributes(0, Integer.MAX_VALUE);
        for (ProposalAttribute pa: proposalAttributes) {
            testInstance.proposalAttributeLocalService.deleteProposalAttribute(pa);
        }
        globalProposalLinksToGlobalProposals = null;
        globalProposalLinksToSideProposals = null;

        //delete Proposal 2 phases
        List<Proposal2Phase> p2ps = testInstance.proposal2PhaseLocalService.getProposal2Phases(0, Integer.MAX_VALUE);
        for (Proposal2Phase p2p: p2ps) {
            testInstance.proposal2PhaseLocalService.deleteProposal2Phase(p2p);
        }

        //Delete proposals
        for (int i = 0; i < globalProposals.size(); i++) {
            Proposal p = globalProposals.get(i);
            testInstance.userLocalService.deleteGroupUsers(p.getGroupId(), globalProposalsTeamMembers.get(i));
            testInstance.proposalLocalService.deleteProposal(p);
        }
        for (int j = 0; j < sideContests.size(); j++) {
            for (int i = 0; i < amountOfProposalsPerSideContest; i++) {
                Proposal p = sideProposals.get(j).get(i);
                testInstance.userLocalService.deleteGroupUsers(p.getGroupId(), sideProposalsTeamMembers.get(j).get(i));
                testInstance.proposalLocalService.deleteProposal(p);
            }
        }

        //delete contests
        testInstance.contestLocalService.deleteContest(globalContest);
        globalContest = null;
        for (int j = 0; j < sideContests.size(); j++) {
            testInstance.contestLocalService.deleteContest(sideContests.get(j));
        }
        sideContests = null;
    }


    private void createUsers() throws SystemException {
        users = new ArrayList<User>();
        for (int i = 0; i < amountOfUsers; i++) {
            users.add(testInstance.createUser(i+1));
        }
    }


    private List<User> setTeamMembers(Proposal proposal, User author) throws SystemException, PortalException {
        //sometimes the admin user is still in the user group
        testInstance.userLocalService.deleteGroupUser(proposal.getGroupId(), testInstance.adminId);

        List<User> teamMembers = new ArrayList<User>();
        teamMembers.add(author);
        for (int i = 1; doWithProbability(probabilityOfAdditionalTeamMember/i); i++) {
            teamMembers.add(users.get(randomInt(0, amountOfUsers)));
        }
        testInstance.userLocalService.addGroupUsers(proposal.getGroupId(), teamMembers);

        return teamMembers;
    }

    private void createGlobalContestAndProposals() throws SystemException, PortalException, ParseException {
        //create global contest
        globalContest = testInstance.contestLocalService.createNewContest(testInstance.adminId, "Test-Global-Contest");
        globalContest.setPoints(pointsToBeDistributed);
        globalContest.setDefaultParentPointType(1);
        testInstance.contestLocalService.updateContest(globalContest);

        contestPhasesCreated = new ArrayList<ContestPhase>();
        //create phases
        final int dayDeviation = 2;
        final int dayOffset;
        if (hasContestEnded) {
            dayOffset = randomInt(5, 30);
        } else {
            dayOffset = randomInt(0, 5)-5;
        }

        String sCp1StartDate = generateRandomIsoDatePair(30+dayOffset, dayDeviation)[0];
        String[] sCp1To2Transition = generateRandomIsoDatePair(24+dayOffset, dayDeviation);
        String[] sCp2To3Transition = generateRandomIsoDatePair(17+dayOffset, dayDeviation);
        String[] sCp3To4Transition = generateRandomIsoDatePair(10+dayOffset, dayDeviation);
        String[] sCp4To5Transition = generateRandomIsoDatePair(3+dayOffset, dayDeviation);
        String[] sCp5To6Transition = generateRandomIsoDatePair(-2+dayOffset, dayDeviation);
        ContestPhase gCp1 = createContestPhase(globalContest, 1, false, "PROMOTE_DONE", sCp1StartDate, sCp1To2Transition[0]);
        ContestPhase gCp2 = createContestPhase(globalContest, 16, true, "PROMOTE_DONE", sCp1To2Transition[1], sCp2To3Transition[0]);
        ContestPhase gCp3 = createContestPhase(globalContest, 18, false, "PROMOTE_DONE", sCp2To3Transition[1], sCp3To4Transition[0]);
        ContestPhase gCp4 = createContestPhase(globalContest, 19, true, "PROMOTE_DONE", sCp3To4Transition[1], sCp4To5Transition[0]);
        ContestPhase gCp5 = createContestPhase(globalContest, 15, false, "PROMOTE_DONE", sCp4To5Transition[1], sCp5To6Transition[0]);
        ContestPhase gCp6 = createContestPhase(globalContest, 17, false, "", sCp5To6Transition[1], null);

        int lastPhase;
        Date now = new Date();
        if (now.after(dateFormat.parse(sCp5To6Transition[0]))) {
            lastPhase = 6;
        } else if (now.after(dateFormat.parse(sCp4To5Transition[0]))) {
            lastPhase = 5;
        } else if (now.after(dateFormat.parse(sCp3To4Transition[0]))) {
            lastPhase = 4;
        } else if (now.after(dateFormat.parse(sCp2To3Transition[0]))) {
            lastPhase = 3;
        } else if (now.after(dateFormat.parse(sCp1To2Transition[0]))) {
            lastPhase = 2;
        } else {
            lastPhase = 1;
        }

        //create proposals, authored by a random user, advance them with a probability to the next phases
        globalProposals = new ArrayList<Proposal>();
        globalProposalsTeamMembers = new HashMap<Integer, List<User>>();
        globalProposalsInLastPhase = new ArrayList<>();
        for (int i = 0; i < amountOfGlobalProposals; i++) {
            User author = users.get(randomInt(0, amountOfUsers));
            Proposal proposal = testInstance.proposalLocalService.create(author.getUserId(), gCp1.getContestPhasePK());

            //create team members
            globalProposalsTeamMembers.put(i, setTeamMembers(proposal, author));
            globalProposals.add(proposal);

            if (lastPhase == 1) {
                globalProposalsInLastPhase.add(i);
            } else if (lastPhase > 1) {
                //copy to second phase
                copyProposalToPhase(proposal, gCp2);

                //copy some of the proposals to other phases
                if (lastPhase == 2) {
                    globalProposalsInLastPhase.add(i);
                } else if (lastPhase > 2 && doWithProbability(probabilityOfBeingAdvancedToNextPhase)) {
                    copyProposalToPhase(proposal, gCp3);
                    if (lastPhase == 3) {
                        globalProposalsInLastPhase.add(i);
                    } else if (lastPhase > 3) {
                        copyProposalToPhase(proposal, gCp4);
                        if (lastPhase == 4) {
                            globalProposalsInLastPhase.add(i);
                        } else if (lastPhase > 4 && doWithProbability(probabilityOfBeingAdvancedToNextPhase)) {
                            copyProposalToPhase(proposal, gCp5);
                            if (lastPhase == 5) {
                                globalProposalsInLastPhase.add(i);
                            } else if (lastPhase > 5) {
                                globalProposalsInLastPhase.add(i);
                                copyProposalToPhase(proposal, gCp6);
                            }

                        }
                    }
                }
            }
        }
    }

    private void createSideContestsAndProposals() throws SystemException, PortalException, ParseException {
        //create side contests
        sideProposals = new HashMap<Integer, List<Proposal>>();
        sideContests = new ArrayList<Contest>();
        sideProposalsTeamMembers = new HashMap<>();
        for (int i = 0; i < amountOfSideContests; i++) {
            Contest sideContest = testInstance.contestLocalService.createNewContest(testInstance.adminId, "Test-Side-Contest-"+(i+1));
            sideContest.setPoints(0);
            sideContest.setDefaultParentPointType(6);
            testInstance.contestLocalService.updateContest(sideContest);
            //create phases
            final int dayDeviation = 2;
            final int dayOffset = randomInt(0, 30)-15;
            String sCp1StartDate = generateRandomIsoDatePair(30+dayOffset, dayDeviation)[0];
            String[] sCp1To2Transition = generateRandomIsoDatePair(24+dayOffset, dayDeviation);
            String[] sCp2To3Transition = generateRandomIsoDatePair(17+dayOffset, dayDeviation);
            String[] sCp3To4Transition = generateRandomIsoDatePair(10+dayOffset, dayDeviation);
            String[] sCp4To5Transition = generateRandomIsoDatePair(3+dayOffset, dayDeviation);
            String[] sCp5To6Transition = generateRandomIsoDatePair(-2+dayOffset, dayDeviation);
            ContestPhase sCp1 = createContestPhase(sideContest, 1, false, "PROMOTE_DONE", sCp1StartDate, sCp1To2Transition[0]);
            ContestPhase sCp2 = createContestPhase(sideContest, 16, true, "PROMOTE_DONE", sCp1To2Transition[1], sCp2To3Transition[0]);
            ContestPhase sCp3 = createContestPhase(sideContest, 18, false, "PROMOTE_DONE", sCp2To3Transition[1], sCp3To4Transition[0]);
            ContestPhase sCp4 = createContestPhase(sideContest, 19, true, "PROMOTE_DONE", sCp3To4Transition[1], sCp4To5Transition[0]);
            ContestPhase sCp5 = createContestPhase(sideContest, 15, false, "PROMOTE_DONE", sCp4To5Transition[1], sCp5To6Transition[0]);
            ContestPhase sCp6 = createContestPhase(sideContest, 17, false, "", sCp5To6Transition[1], null);

            sideProposals.put(i, new ArrayList<Proposal>());
            sideProposalsTeamMembers.put(i, new HashMap<Integer, List<User>>());

            for (int j = 0; j < amountOfProposalsPerSideContest; j++) {
                User author = users.get(randomInt(0, amountOfUsers));
                Proposal proposal = testInstance.proposalLocalService.create(author.getUserId(), sCp1.getContestPhasePK());
                sideProposals.get(i).add(proposal);
                sideProposalsTeamMembers.get(i).put(j, setTeamMembers(proposal, author));

                //copy to second phase
                copyProposalToPhase(proposal, sCp2);
                if (doWithProbability(probabilityOfBeingAdvancedToNextPhase)) {
                    copyProposalToPhase(proposal, sCp3);
                    copyProposalToPhase(proposal, sCp4);
                    if (doWithProbability(probabilityOfBeingAdvancedToNextPhase)) {
                        copyProposalToPhase(proposal, sCp5);
                        if (hasContestEnded) {
                            copyProposalToPhase(proposal, sCp6);
                        }
                    }
                }
            }
            sideContests.add(sideContest);
        }
    }

    private void createLinksBetweenProposals() throws SystemException, PortalException {
        globalProposalLinksToGlobalProposals = new HashMap<>();
        globalProposalLinksToSideProposals = new HashMap<>();

        for (int i = 0; i < amountOfGlobalProposals; i++) {
            globalProposalLinksToGlobalProposals.put(i, new ArrayList<Integer>());
            globalProposalLinksToSideProposals.put(i, new HashMap<Integer, List<Integer>>());
            String sectionText = "These are the proposals we link to:\n";

            for (int j = 0; j < amountOfGlobalProposals; j++) {
                if (doWithProbability(probabilityToLinkToOtherProposal)) {
                    sectionText += "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/" + globalContest.getContestPK() + "/planId/" + globalProposals.get(j).getProposalId() + "\n\n";
                    globalProposalLinksToGlobalProposals.get(i).add(j);
                }
            }
            for (int j = 0; j < amountOfSideContests; j++) {
                globalProposalLinksToSideProposals.get(i).put(j, new ArrayList<Integer>());
                for (int k = 0; k < amountOfProposalsPerSideContest; k++) {
                    if (doWithProbability(probabilityToLinkToOtherProposal)) {
                        sectionText += "http://127.0.0.1:8080/web/guest/plans/-/plans/contestId/" + sideContests.get(j).getContestPK() + "/planId/" + sideProposals.get(j).get(k).getProposalId() + "\n\n";
                        globalProposalLinksToSideProposals.get(i).get(j).add(k);
                    }
                }
            }

            //1300907 is the sub proposal plan section definition
            testInstance.proposalLocalService.setAttribute(globalProposals.get(i).getAuthorId(), globalProposals.get(i).getProposalId(), ProposalAttributeKeys.SECTION, 1300907L, sectionText);
        }
    }

    private ContestPhase createContestPhase(Contest c, long type, boolean fellowScreeningActive, String autoPromote, String startDate, String endDate) throws SystemException, ParseException {
        ContestPhase cp = testInstance.contestPhaseLocalService.createContestPhase(100000+contestPhaseIdCount++);
        cp.setContestPK(c.getContestPK());
        cp.setContestPhaseType(type);
        cp.setFellowScreeningActive(fellowScreeningActive);
        cp.setContestPhaseAutopromote(autoPromote);
        cp.setPhaseActiveOverride(false);
        cp.setPhaseInactiveOverride(false);
        cp.setPhaseStartDate(dateFormat.parse(startDate));
        if (endDate != null) {
            cp.setPhaseEndDate(dateFormat.parse(endDate));
        }
        testInstance.contestPhaseLocalService.updateContestPhase(cp);
        contestPhasesCreated.add(cp);

        return cp;
    }





    private static void copyProposalToPhase(Proposal p, ContestPhase cp) throws SystemException {
        Proposal2Phase p2p = testInstance.proposal2PhaseLocalService.create(p.getProposalId(), cp.getContestPhasePK());
        p2p.setVersionFrom(1);
        p2p.setVersionTo(1);
        testInstance.proposal2PhaseLocalService.updateProposal2Phase(p2p);
    }

    //returns a random int between min and max (inclusive min, exclusive max)
    public static int randomInt(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static boolean doWithProbability(double probability) {
        return (random.nextDouble() <= probability);
    }

    public static double generateProbabilityForTeamMembers(int teamSize) {
        //bias the probability towards getting 1
        //also take into account team size and divide by two thirds of the team size.
        return doWithProbability(0.1) ? 1 : random.nextDouble()/(Math.ceil(teamSize*2.0/3.0));
    }

    public  static String[] generateRandomIsoDatePair(int daysAgo, int daysDeviation) {
        Long minTime = System.currentTimeMillis() - Math.round(((daysAgo+(daysDeviation*0.5)) * DAY_IN_MS));
        Long maxTime = System.currentTimeMillis() - Math.round(((daysAgo-(daysDeviation*0.5)) * DAY_IN_MS));
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(minTime + new Random().nextInt((int) Math.abs(maxTime - minTime)));

        String[] tuple = new String[2];
        tuple[0] = dateFormat.format(cal.getTime());
        cal.add(Calendar.SECOND, 1);
        tuple[1] = dateFormat.format(cal.getTime());
        return tuple;
    }

}
