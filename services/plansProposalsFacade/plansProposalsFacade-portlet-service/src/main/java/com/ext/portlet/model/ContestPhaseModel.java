package com.ext.portlet.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the ContestPhase service. Represents a row in the &quot;xcolab_ContestPhase&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.ext.portlet.model.impl.ContestPhaseModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.ext.portlet.model.impl.ContestPhaseImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhase
 * @see com.ext.portlet.model.impl.ContestPhaseImpl
 * @see com.ext.portlet.model.impl.ContestPhaseModelImpl
 * @generated
 */
public interface ContestPhaseModel extends BaseModel<ContestPhase> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. All methods that expect a contest phase model instance should use the {@link ContestPhase} interface instead.
     */

    /**
     * Returns the primary key of this contest phase.
     *
     * @return the primary key of this contest phase
     */
    public long getPrimaryKey();

    /**
     * Sets the primary key of this contest phase.
     *
     * @param primaryKey the primary key of this contest phase
     */
    public void setPrimaryKey(long primaryKey);

    /**
     * Returns the contest phase p k of this contest phase.
     *
     * @return the contest phase p k of this contest phase
     */
    public long getContestPhasePK();

    /**
     * Sets the contest phase p k of this contest phase.
     *
     * @param ContestPhasePK the contest phase p k of this contest phase
     */
    public void setContestPhasePK(long ContestPhasePK);

    /**
     * Returns the contest p k of this contest phase.
     *
     * @return the contest p k of this contest phase
     */
    public long getContestPK();

    /**
     * Sets the contest p k of this contest phase.
     *
     * @param ContestPK the contest p k of this contest phase
     */
    public void setContestPK(long ContestPK);

    /**
     * Returns the contest phase type of this contest phase.
     *
     * @return the contest phase type of this contest phase
     */
    public long getContestPhaseType();

    /**
     * Sets the contest phase type of this contest phase.
     *
     * @param ContestPhaseType the contest phase type of this contest phase
     */
    public void setContestPhaseType(long ContestPhaseType);

    /**
     * Returns the contest schedule p k of this contest phase.
     *
     * @return the contest schedule p k of this contest phase
     */
    public long getContestSchedulePK();

    /**
     * Sets the contest schedule p k of this contest phase.
     *
     * @param ContestSchedulePK the contest schedule p k of this contest phase
     */
    public void setContestSchedulePK(long ContestSchedulePK);

    /**
     * Returns the fellow screening active of this contest phase.
     *
     * @return the fellow screening active of this contest phase
     */
    public boolean getFellowScreeningActive();

    /**
     * Returns <code>true</code> if this contest phase is fellow screening active.
     *
     * @return <code>true</code> if this contest phase is fellow screening active; <code>false</code> otherwise
     */
    public boolean isFellowScreeningActive();

    /**
     * Sets whether this contest phase is fellow screening active.
     *
     * @param fellowScreeningActive the fellow screening active of this contest phase
     */
    public void setFellowScreeningActive(boolean fellowScreeningActive);

    /**
     * Returns the contest phase autopromote of this contest phase.
     *
     * @return the contest phase autopromote of this contest phase
     */
    @AutoEscape
    public String getContestPhaseAutopromote();

    /**
     * Sets the contest phase autopromote of this contest phase.
     *
     * @param contestPhaseAutopromote the contest phase autopromote of this contest phase
     */
    public void setContestPhaseAutopromote(String contestPhaseAutopromote);

    /**
     * Returns the contest phase description override of this contest phase.
     *
     * @return the contest phase description override of this contest phase
     */
    @AutoEscape
    public String getContestPhaseDescriptionOverride();

    /**
     * Sets the contest phase description override of this contest phase.
     *
     * @param ContestPhaseDescriptionOverride the contest phase description override of this contest phase
     */
    public void setContestPhaseDescriptionOverride(
        String ContestPhaseDescriptionOverride);

    /**
     * Returns the phase active override of this contest phase.
     *
     * @return the phase active override of this contest phase
     */
    public boolean getPhaseActiveOverride();

    /**
     * Returns <code>true</code> if this contest phase is phase active override.
     *
     * @return <code>true</code> if this contest phase is phase active override; <code>false</code> otherwise
     */
    public boolean isPhaseActiveOverride();

    /**
     * Sets whether this contest phase is phase active override.
     *
     * @param phaseActiveOverride the phase active override of this contest phase
     */
    public void setPhaseActiveOverride(boolean phaseActiveOverride);

    /**
     * Returns the phase inactive override of this contest phase.
     *
     * @return the phase inactive override of this contest phase
     */
    public boolean getPhaseInactiveOverride();

    /**
     * Returns <code>true</code> if this contest phase is phase inactive override.
     *
     * @return <code>true</code> if this contest phase is phase inactive override; <code>false</code> otherwise
     */
    public boolean isPhaseInactiveOverride();

    /**
     * Sets whether this contest phase is phase inactive override.
     *
     * @param phaseInactiveOverride the phase inactive override of this contest phase
     */
    public void setPhaseInactiveOverride(boolean phaseInactiveOverride);

    /**
     * Returns the phase start date of this contest phase.
     *
     * @return the phase start date of this contest phase
     */
    public Date getPhaseStartDate();

    /**
     * Sets the phase start date of this contest phase.
     *
     * @param PhaseStartDate the phase start date of this contest phase
     */
    public void setPhaseStartDate(Date PhaseStartDate);

    /**
     * Returns the phase end date of this contest phase.
     *
     * @return the phase end date of this contest phase
     */
    public Date getPhaseEndDate();

    /**
     * Sets the phase end date of this contest phase.
     *
     * @param PhaseEndDate the phase end date of this contest phase
     */
    public void setPhaseEndDate(Date PhaseEndDate);

    /**
     * Returns the phase buffer end dated of this contest phase.
     *
     * @return the phase buffer end dated of this contest phase
     */
    public Date getPhaseBufferEndDated();

    /**
     * Sets the phase buffer end dated of this contest phase.
     *
     * @param PhaseBufferEndDated the phase buffer end dated of this contest phase
     */
    public void setPhaseBufferEndDated(Date PhaseBufferEndDated);

    /**
     * Returns the next status of this contest phase.
     *
     * @return the next status of this contest phase
     */
    @AutoEscape
    public String getNextStatus();

    /**
     * Sets the next status of this contest phase.
     *
     * @param nextStatus the next status of this contest phase
     */
    public void setNextStatus(String nextStatus);

    /**
     * Returns the created of this contest phase.
     *
     * @return the created of this contest phase
     */
    public Date getCreated();

    /**
     * Sets the created of this contest phase.
     *
     * @param created the created of this contest phase
     */
    public void setCreated(Date created);

    /**
     * Returns the updated of this contest phase.
     *
     * @return the updated of this contest phase
     */
    public Date getUpdated();

    /**
     * Sets the updated of this contest phase.
     *
     * @param updated the updated of this contest phase
     */
    public void setUpdated(Date updated);

    /**
     * Returns the author ID of this contest phase.
     *
     * @return the author ID of this contest phase
     */
    public long getAuthorId();

    /**
     * Sets the author ID of this contest phase.
     *
     * @param authorId the author ID of this contest phase
     */
    public void setAuthorId(long authorId);

    @Override
    public boolean isNew();

    @Override
    public void setNew(boolean n);

    @Override
    public boolean isCachedModel();

    @Override
    public void setCachedModel(boolean cachedModel);

    @Override
    public boolean isEscapedModel();

    @Override
    public Serializable getPrimaryKeyObj();

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj);

    @Override
    public ExpandoBridge getExpandoBridge();

    @Override
    public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

    @Override
    public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

    @Override
    public void setExpandoBridgeAttributes(ServiceContext serviceContext);

    @Override
    public Object clone();

    @Override
    public int compareTo(ContestPhase contestPhase);

    @Override
    public int hashCode();

    @Override
    public CacheModel<ContestPhase> toCacheModel();

    @Override
    public ContestPhase toEscapedModel();

    @Override
    public ContestPhase toUnescapedModel();

    @Override
    public String toString();

    @Override
    public String toXmlString();
}
