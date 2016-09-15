package org.xcolab.service.proposal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.service.proposal.domain.pointsdistributionconfiguration.PointsDistributionConfigurationDao;
import org.xcolab.model.tables.pojos.PointsDistributionConfiguration;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class PointsDistributionConfigurationController {

    @Autowired
    private PointsDistributionConfigurationDao pointsDistributionConfigurationDao;

    @RequestMapping(value = "/pointsDistributionConfigurations", method = RequestMethod.POST)
    public PointsDistributionConfiguration createPointsDistributionConfiguration(@RequestBody PointsDistributionConfiguration pointsDistributionConfiguration) {
        pointsDistributionConfiguration.setCreateDate(new Timestamp(new Date().getTime()));
        return this.pointsDistributionConfigurationDao.create(pointsDistributionConfiguration);
    }

    @RequestMapping(value = "/pointsDistributionConfigurations", method = {RequestMethod.GET, RequestMethod.HEAD})
    public List<PointsDistributionConfiguration> getPointsDistributionConfigurations(
            @RequestParam(required = false) Long proposalId,
            @RequestParam(required = false) Long pointTypeId
    ) {
        return pointsDistributionConfigurationDao.findByGiven(proposalId, pointTypeId);
    }

    @RequestMapping(value = "/pointsDistributionConfigurations/removeByProposalId", method = RequestMethod.DELETE)
    public String deletePointsDistributionConfiguration(@RequestParam("proposalId") Long proposalId)
            throws NotFoundException {

        if (proposalId == null || proposalId == 0) {
            throw new NotFoundException("No PointsDistributionConfiguration with id given");
        } else {
            this.pointsDistributionConfigurationDao.deleteByProposalId(proposalId);
            return "PointsDistributionConfiguration deleted successfully";

        }
    }


}
