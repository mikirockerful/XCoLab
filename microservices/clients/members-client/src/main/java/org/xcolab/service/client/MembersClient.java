package org.xcolab.service.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.pojo.MemberCategory;
import org.xcolab.pojo.Role_;
import org.xcolab.pojo.User_;

import java.util.List;

public final class MembersClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/members-service";

    private static final RestTemplate restTemplate = new RestTemplate();

    private MembersClient() { }

    public static User_ getMember(long memberId) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/members/" + memberId);
        return restTemplate.getForObject(uriBuilder.build().toString(), User_.class);
    }

    public static List<User_> listMembers(String categoryFilterValue, String screenNameFilterValue, String sortField,
                                          boolean ascOrder, int firstUser, int lastUser) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/members")
                        .queryParam("firstRecord", firstUser)
                        .queryParam("lastRecord", lastUser);

        if (sortField != null && !sortField.isEmpty()) {
            uriBuilder.queryParam("sort", ((ascOrder) ? ("") : ("-")) + sortField);
        }
        if (screenNameFilterValue != null && !screenNameFilterValue.isEmpty()) {
            uriBuilder.queryParam("screenName", screenNameFilterValue);
        }
        if (categoryFilterValue != null && !categoryFilterValue.isEmpty()) {
            uriBuilder.queryParam("category", categoryFilterValue);
        }

        ResponseEntity<List<User_>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<User_>>() {
                });

        return response.getBody();
    }

    public static Integer countMembers(String categoryFilterValue, String screenNameFilterValue) {
        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/members/count");
        if (screenNameFilterValue != null) {
            uriBuilder.queryParam("screenName", screenNameFilterValue);
        }
        if (categoryFilterValue != null) {
            uriBuilder.queryParam("category", categoryFilterValue);
        }
        return restTemplate.getForObject(uriBuilder.build().toString(), Integer.class);
    }

    public static Integer getMemberActivityCount(Long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/activityCount");
        return restTemplate.getForObject(uriBuilder.build().toString(), Integer.class);
    }

    public static Integer getMemberMaterializedPoints(Long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/materializedPoints");
        return restTemplate.getForObject(uriBuilder.build().toString(), Integer.class);
    }

    public static List<Role_> getMemberRoles(Long memberId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/members/" + memberId + "/roles");

        ResponseEntity<List<Role_>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Role_>>() {
                });

        return response.getBody();
    }

    public static MemberCategory getMemberCategory(Long roleId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/membercategories/" + roleId + "");

        return restTemplate.getForObject(uriBuilder.build().toString(), MemberCategory.class);
    }
}
