package org.xcolab.view.util.entity;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.activities.pojo.ActivityEntry;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ActivityUtil {

    private static final long AGGREGATION_TIME_WINDOW = Duration.ofHours(1).get(ChronoUnit.MILLIS);

    public static List<ActivityEntry> retrieveAllActivities(int pagestart, int next) {
        return ActivitiesClientUtil.getActivityEntries(pagestart, next, null, null);
    }

    public static List<ActivityEntry> groupActivities(List<ActivityEntry> activities) {
        //find all activities of same type
        Map<String, List<ActivityEntry>> activitiesMap = new HashMap<>(10000);
        for (ActivityEntry a : activities) {
            if (!activitiesMap.containsKey(getSocialActivityKey(a))) {
                activitiesMap.put(getSocialActivityKey(a), new LinkedList<>());
            }
            activitiesMap.get(getSocialActivityKey(a)).add(a);
        }
        return clusterActivities(activitiesMap);
    }


    public static int getAllActivitiesCount() {
        return ActivitiesClientUtil.countActivities(null, null);
    }

    public static int getActivitiesCount(long userId) {
        return ActivitiesClientUtil.countActivities(userId, null);
    }

    private static List<ActivityEntry> clusterActivities(
            Map<String, List<ActivityEntry>> activitiesMap) {
        //cluster
        List<ActivityEntry> aggregatedActivities = new LinkedList<>();
        Comparator<ActivityEntry> sorter =
                Comparator.comparingLong(o -> o.getCreateDate().getTime());
        for (Collection<ActivityEntry> activitiesMapValue : activitiesMap.values()) {
            List<ActivityEntry> socialActivities =
                    new ArrayList<>(activitiesMapValue); //convert to array for sorting
            socialActivities.sort(sorter);

            ActivityEntry curMin = null;
            for (ActivityEntry socialActivity : socialActivities) {
                if (curMin == null ||
                        socialActivity.getCreateDate().getTime() - curMin.getCreateDate().getTime()
                                < AGGREGATION_TIME_WINDOW) {
                    curMin = socialActivity;
                } else {
                    aggregatedActivities.add(curMin);
                    curMin = socialActivity;
                }
            }
            aggregatedActivities.add(curMin);
        }

        // Sort the activities in reverse order (sort by date DESC)
        aggregatedActivities.sort(Collections.reverseOrder(sorter));

        return aggregatedActivities;
    }

    private static String getSocialActivityKey(ActivityEntry sa) {
        return sa.getPrimaryType() + "_" + sa.getClassPrimaryKey() + "_" + sa.getSecondaryType()
                + "_" + sa.getMemberId();
    }
}
