package org.xcolab.util.http.client;

import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.enums.ResourceEnum;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.client.interfaces.IdentifiableHttpResource;

/**
 * A virtual rest resource that does not require type information as it is never queried.
 *
 * This class serves as a placeholder for other services' entities in nested resource calls. These
 * resources can be used as a parent for a {@link RestResource2} and can then be used to resolve
 * the corresponding {@link QueryId} when a call is made to the nested resource.
 *
 * @param <ResourceT> the resource type of the nested object - usually either an entity in another
 * service or {@link Void}
 * @param <IdT> the type of the resource identifier - often a {@link Long} or {@link String}
 */
public class VirtualRestResource<ResourceT, IdT>
        implements IdentifiableHttpResource<ResourceT, IdT> {

    private final ResourceEnum resourceEnum;

    private VirtualRestResource(ResourceEnum resourceEnum) {
        this.resourceEnum = resourceEnum;
    }

    public static <ResourceT, IdT> VirtualRestResource<ResourceT, IdT> of(
            ResourceEnum resourceEnum) {
        return new VirtualRestResource<>(resourceEnum);
    }

    @Override
    public QueryId<ResourceT, IdT> id(IdT id) {
        return new QueryId<>(this, id);
    }

    @Override
    public UriBuilder getResourceUrl() {
        return getBaseUrl().resource(resourceEnum.getResourceName());
    }

    @Override
    public UriBuilder getResourceUrl(Object resourceId) {
        return getBaseUrl().resource(resourceEnum.getResourceName(), resourceId);
    }

    @Override
    public UriBuilder getBaseUrl() {
        return resourceEnum.getCoLabService().getBaseUrl(ServiceNamespace.instance());
    }
}
