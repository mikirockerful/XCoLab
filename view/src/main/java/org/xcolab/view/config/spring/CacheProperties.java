package org.xcolab.view.config.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.xcolab.util.http.caching.CacheCustomization;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.caching.provider.CacheProvider;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties("cache")
public class CacheProperties {

    /**
     * Enable caching of in service clients.
     */
    private boolean enabled = true;

    /**
     * Custom implementation of cache provider to be used for caching.
     */
    private Class<? extends CacheProvider> provider;

    /**
     * Customize individual cache sizes and TTLs.
     */
    private final Map<CacheName, CacheCustomization> caches = new HashMap<>();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Class<? extends CacheProvider> getProvider() {
        return provider;
    }

    public void setProvider(
            Class<? extends CacheProvider> provider) {
        this.provider = provider;
    }

    public Map<CacheName, CacheCustomization> getCaches() {
        return caches;
    }
}
