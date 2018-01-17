package org.xcolab.util.enums.activity;

import org.springframework.util.Assert;

public enum ActivityEntryType {

    MEMBER(10038L),
    DISCUSSION(39202L),
    PROPOSAL(1368503L),
    CONTEST(39701L);

    private final Long primaryTypeId;

    ActivityEntryType(Long type) {
        this.primaryTypeId = type;
    }

    public Long getPrimaryTypeId(){
        return this.primaryTypeId;
    }


    public static ActivityEntryType getActivityEntryTypeByPrimaryType(Long tierType)  {
        Assert.notNull(tierType, "ActivityEntryType cannot be null");
        for (ActivityEntryType activityType : ActivityEntryType.values()) {
            if (activityType.getPrimaryTypeId().longValue() == tierType) {
                return activityType;
            }
        }
        return null;
    }
}