package org.xcolab.portlets.proposals.view.action;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;

import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class VoteOnProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"action=voteOnProposalAction"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response) 
                    throws PortalException, SystemException, ProposalsAuthorizationException {
        
        if (proposalsContext.getPermissions(request).getCanVote()) {
            long proposalId = proposalsContext.getProposal(request).getProposalId();
            long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();
            long userId = proposalsContext.getUser(request).getUserId();
            if (ProposalLocalServiceUtil.hasUserVoted(proposalId, contestPhaseId, userId)) {
                ProposalLocalServiceUtil.removeVote(contestPhaseId, userId);
            }
            else {
                ProposalLocalServiceUtil.addVote(proposalId, contestPhaseId, userId);
            }
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to vote on proposal ");
        }
    }

}
