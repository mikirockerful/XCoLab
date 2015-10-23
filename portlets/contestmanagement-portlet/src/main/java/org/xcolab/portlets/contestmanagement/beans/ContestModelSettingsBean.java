package org.xcolab.portlets.contestmanagement.beans;

import com.ext.portlet.model.Contest;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import org.xcolab.enums.ModelRegions;
import org.xcolab.portlets.contestmanagement.entities.LabelStringValue;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import edu.mit.cci.roma.client.Simulation;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Thomas on 6/16/2015.
 */
public class ContestModelSettingsBean implements Serializable {
    private final static Log _log = LogFactoryUtil.getLog(ContestModelSettingsBean.class);
    private Long defaultModelId;
    private List<Long> otherModelIds;
    private String otherModels;
    private String defaultModelSettings;
    private String modelRegion;

    public ContestModelSettingsBean() {
    }

    public ContestModelSettingsBean(Contest contest) throws Exception {
        this.defaultModelId = contest.getDefaultModelId();
        this.otherModels = contest.getOtherModels();
        this.defaultModelSettings = contest.getDefaultModelSettings();
        this.modelRegion = "";
        this.otherModelIds = new ArrayList<>();
        initOtherModelsAndRegion();
    }

    private void initOtherModelsAndRegion() throws Exception{
        if (Validator.isNotNull(this.otherModels)) {
            for (String otherModelId : this.otherModels.split(",")) {
                otherModelIds.add(Long.parseLong(otherModelId.trim()));
            }
        }
        this.modelRegion = getRegionFromDefaultModelSettings(this.defaultModelSettings);
    }

    private String getRegionFromDefaultModelSettings(String defaultModelSettingsString) throws Exception {
        JSONObject defaultModelSettings = JSONFactoryUtil.createJSONObject(defaultModelSettingsString);
        String region = "";
        if (Validator.isNotNull(defaultModelSettings)) {
            region = defaultModelSettings.has("region") ? defaultModelSettings.getString("region") : "";
        }
        return region;
    }

    public String getModelRegion() {
        return modelRegion;
    }

    public void setModelRegion(String modelRegion) {
        this.modelRegion = modelRegion;
        if(Validator.isBlank(modelRegion)) {
            this.defaultModelSettings = "";
        } else {
            JSONObject defaultModelSettings = JSONFactoryUtil.createJSONObject();
            defaultModelSettings.put("region", modelRegion);
            this.defaultModelSettings = defaultModelSettings.toString();
        }
    }

    public Long getDefaultModelId() {
        return defaultModelId;
    }

    public void setDefaultModelId(Long defaultModelId) {
        this.defaultModelId = defaultModelId;
    }

    public String getOtherModels() {
        return otherModels;
    }
    public List<Long>
    getOtherModelIds() {
        return otherModelIds;
    }

    public void setOtherModels(String otherModelIds) {
        if (otherModelIds != null) {
            String removeEverythingButNumbersAndCommas = otherModelIds.replaceAll("[^0-9,]", "").replaceAll(",,", ",");
            this.otherModels = removeEverythingButNumbersAndCommas;
        }
    }

    public void setOtherModelIds(List<Long> otherModelIds) {
        this.otherModelIds = otherModelIds;
        this.otherModels = this.otherModelIds.toString().replaceAll("\\[", "").replaceAll("\\]", "");
    }

    public String getDefaultModelSettings() {
        return defaultModelSettings;
    }

    public void setDefaultModelSettings(String defaultModelSettings) {
        if (defaultModelSettings != null) {
            String defaultModelSettingsWithDoubleQuotationMarks = defaultModelSettings.replaceAll("'", "\"");
            this.defaultModelSettings = defaultModelSettingsWithDoubleQuotationMarks;
        }
    }

    public void persist(Contest contest) throws Exception {
        if (!otherModels.isEmpty()) {
            contest.setOtherModels(otherModels);
        }
        if (defaultModelId != null) {
            contest.setDefaultModelId(defaultModelId);
        }
        if (defaultModelSettings != null) {
            contest.setDefaultModelSettings(defaultModelSettings);
        }
        contest.persist();
    }

    public static List<LabelValue> getAllModelIds() {
        List<LabelValue> allModelIds = new ArrayList<>();
        try {
            List<Simulation> simulationsSorted = new ArrayList<Simulation>(CollaboratoriumModelingService.repository().getAllSimulations());
            Collections.sort(simulationsSorted, new Comparator<Simulation>() {
                @Override
                public int compare(Simulation o1, Simulation o2) {
                    return (int) (o2.getId() - o1.getId());
                }
            });
            for (Simulation simulation : simulationsSorted) {
                allModelIds.add(new LabelValue(simulation.getId(), "(Id: " + simulation.getId() + ") " + simulation.getName()));
            }
        } catch (Exception e) {
            _log.warn("Couldn't fetch contest model Ids.", e);
        }
        return allModelIds;
    }

    public static List<LabelStringValue> getAllModelRegions() {
        List<LabelStringValue> modelRegions = new ArrayList<>();
        for (ModelRegions modelRegion : ModelRegions.values()) {
            modelRegions.add(new LabelStringValue(modelRegion.getModelRegionName(), modelRegion.getModelRegionTitle()));
        }
        return modelRegions;
    }
}
