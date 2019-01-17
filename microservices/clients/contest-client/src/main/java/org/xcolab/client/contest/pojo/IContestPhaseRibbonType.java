package org.xcolab.client.contest.pojo;

public interface IContestPhaseRibbonType {

    Long getId();

    void setId(Long id);

    Integer getRibbon();

    void setRibbon(Integer ribbon);

    String getTitle();

    void setTitle(String title);

    String getHoverText();

    void setHoverText(String hoverText);

    Boolean getShowText();

    void setShowText(Boolean showText);

    String getDescription();

    void setDescription(String description);

    Boolean getCopyOnPromote();

    void setCopyOnPromote(Boolean copyOnPromote);

    Integer getSortOrder();

    void setSortOrder(Integer sortOrder);
}
