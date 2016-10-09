package org.xcolab.portlets.proposals.view;



import org.xcolab.client.modeling.RomaClientUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.utils.IdListUtil;

import javax.portlet.PortletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.mit.cci.roma.client.Simulation;

@Controller
@RequestMapping("view")
public class ProposalModelTabController extends BaseProposalTabController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_ACTIONSIMPACTS"})
    public String show(Model model, @RequestParam(required = false) boolean edit, PortletRequest request) 
            throws PortalException, SystemException {

        setCommonModelAndPageAttributes(request, model, ProposalTab.ACTIONSIMPACTS);
        
        if (edit) {
        	Map<Long, String> modelIdsWithNames = getModelIdsAndNames(proposalsContext.getContest(request).getContestPK());
        	if (modelIdsWithNames.size() > 1) {
        		model.addAttribute("availableModels", modelIdsWithNames);
        	}
        	
            return "proposalModel_edit";
        }
        return "proposalModel";
    }

    public Map<Long, String> getModelIdsAndNames(long contestPK) throws SystemException, PortalException {
        List<Long> modelIds = getModelIds(contestPK);

        Map<Long, String> ret = new HashMap<>();
        for (Long modelId: modelIds) {
            try {
                Simulation s = RomaClientUtil.repository().getSimulation(modelId);
                ret.put(s.getId(), s.getName());

            }
            catch (IOException e) {
                throw new PortalException(e);
            }
        }
        return ret;
    }
    public List<Long> getModelIds(long contestPK) throws SystemException, PortalException {
        try {
            Contest contest = ContestClient.getContest(contestPK);

            List<Long> modelIds = new ArrayList<>();

            if (StringUtils.isNotBlank(contest.getOtherModels())) {
                modelIds.addAll(IdListUtil.getIdsFromString(contest.getOtherModels()));
            }
            if (!modelIds.contains(contest.getDefaultModelId())) {
                modelIds.add(contest.getDefaultModelId());
            }

            return modelIds;
        }catch (ContestNotFoundException ignored){
            return null;
        }
    }

}
