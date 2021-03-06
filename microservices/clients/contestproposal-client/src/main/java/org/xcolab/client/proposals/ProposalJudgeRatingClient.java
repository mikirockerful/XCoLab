package org.xcolab.client.proposals;

import org.xcolab.client.contest.resources.ProposalResource;
import org.xcolab.client.proposals.enums.ProposalJudgeType;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRating;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingType;
import org.xcolab.client.proposals.pojo.evaluation.judges.ProposalRatingValue;
import org.xcolab.util.http.client.RestResource1;

import java.util.List;

public final class ProposalJudgeRatingClient {

    private final RestResource1<ProposalRating, Long> proposalRatingResource;
    private final RestResource1<ProposalRatingValue, Long> proposalRatingValueResource;
    private final RestResource1<ProposalRatingType, Long> proposalRatingTypeResource;

    public ProposalJudgeRatingClient() {
        proposalRatingResource = new RestResource1<>(ProposalResource.PROPOSAL_RATING,
                ProposalRating.TYPES);
        proposalRatingValueResource = new RestResource1<>(ProposalResource.PROPOSAL_RATING_VALUE,
                ProposalRatingValue.TYPES);
        proposalRatingTypeResource = new RestResource1<>(ProposalResource.PROPOSAL_RATING_TYPE,
                ProposalRatingType.TYPES);
    }

    public List<ProposalRating> getProposalRatingsByProposalUserContestPhase(Long proposalId,
            Long contestPhaseId, Long userId) {
        return proposalRatingResource.list()
                //.withCache(CacheKeys.withClass(ProposalRating.class)
                //                .withParameter("proposalId", proposalId)
                //                .withParameter("contestPhaseId", contestPhaseId)
                //                .withParameter("userId", userId).asList(),
                //        CacheRetention.MISC_MEDIUM)
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("contestPhaseId", contestPhaseId)
                .optionalQueryParam("userId", userId)
                .execute();
    }

    public List<ProposalRating> getFellowRatingsForProposal(long proposalId, long contestPhaseId) {
        return getRatingsForProposal(proposalId, contestPhaseId, ProposalJudgeType.FELLOW.getId());
    }


    public  List<ProposalRatingType> getRatingTypesForJudges() {
        return this.getRatingTypesForJudgeType(ProposalJudgeType.JUDGE.getId());
    }

    public List<ProposalRatingType> getRatingTypesForFellows() {
            return this.getRatingTypesForJudgeType(ProposalJudgeType.FELLOW.getId());
    }

    private List<ProposalRatingType> getRatingTypesForJudgeType(int judgeType) {
        return proposalRatingTypeResource.list()
                .queryParam("judgeType", judgeType)
                .queryParam("active", true)
                .execute();
    }

    protected List<ProposalRating> getRatingsForProposal(long proposalId, long contestPhaseId,
            int judgeType) {

        return proposalRatingResource
                .collectionService("findByProposalIdJudgeTypeJudgeIdContestPhaseId",
                        ProposalRating.TYPES.getTypeReference())
                .queryParam("proposalId", proposalId)
                .queryParam("judgeType", judgeType)
                .queryParam("contestPhaseId", contestPhaseId)
                .getList();
    }

    public List<ProposalRating> getJudgeRatingsForProposal(long proposalId, long contestPhaseId) {
        return getRatingsForProposal(proposalId, contestPhaseId, ProposalJudgeType.JUDGE.getId());
    }

    public List<ProposalRating> getJudgeRatingsForProposalAndUser(long userId, long proposalId,
            long contestPhaseId) {
        return getRatingsForProposalAndUser(proposalId, ProposalJudgeType.JUDGE.getId(), userId,
                contestPhaseId);
    }

    protected List<ProposalRating> getRatingsForProposalAndUser(long proposalId, int judgeType,
            long userId, long contestPhaseId) {
        return proposalRatingResource
                .collectionService("findByProposalIdJudgeTypeJudgeIdContestPhaseId",
                        ProposalRating.TYPES.getTypeReference())
                .queryParam("proposalId", proposalId)
                .queryParam("judgeType", judgeType)
                .queryParam("userId", userId)
                .queryParam("contestPhaseId", contestPhaseId)
                .getList();

    }

    public List<ProposalRating> getFellowRatingForProposalAndUser(long userId, long proposalId,
            long contestPhaseId) {
        return getRatingsForProposalAndUser(proposalId, ProposalJudgeType.FELLOW.getId(), userId,
                contestPhaseId);
    }

    public ProposalRating createProposalRating(ProposalRating proposalRating) {
        return proposalRatingResource
                .create(new ProposalRating(proposalRating))
                .execute();
    }

    public boolean updateProposalRating(ProposalRating proposalRating) {
        return proposalRatingResource
                .update(new ProposalRating(proposalRating), proposalRating.getId())
                .execute();
    }

    public ProposalRatingValue getProposalRatingValue(long id) {
        return proposalRatingValueResource.get(id)
                .execute();
    }

    public List<ProposalRatingValue> getProposalRatingValuesByProposalRatingTypeId(Long ratingTypeId) {
        return proposalRatingValueResource.list()
                .optionalQueryParam("ratingTypeId", ratingTypeId)
                .execute();
    }

    public ProposalRatingType getProposalRatingType(long id) {
        return proposalRatingTypeResource.get(id)
                .execute();
    }
}
