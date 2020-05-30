// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.implementation.http;

import com.azure.cosmos.implementation.HttpConstants;
import com.azure.cosmos.implementation.apachecommons.lang.StringUtils;
import com.azure.cosmos.implementation.guava25.collect.ImmutableMap;

import java.util.Map;

/**
 * Some doc TBD
 */
public abstract class HttpHeaders
{
    private static final ImmutableMap<String, String> BYPASS_LIST = ImmutableMap.of(
        "x-ms-activity-id", "A"
    );

    public void setActivityId(String value) {
        putInternal(HttpConstants.Headers.ACTIVITY_ID, value);
    }

    public String getActivityId() {
        return getValueInternal(HttpConstants.Headers.ACTIVITY_ID);
    }

    /**
     * Create a HttpHeaders instance with the provided initial headers.
     *
     * @param mergeHeaders the map of initial headers
     */
    public void putAll(Map<String, String> mergeHeaders) {
        for (final Map.Entry<String, String> header : mergeHeaders.entrySet()) {
            put(header.getKey(), header.getValue());
        }
    }

    /**
     * Gets the number of headers in the collection.
     *
     * @return the number of headers in this collection.
     */
    public abstract int size();

    /**
     * Set a header.
     *
     * if header with same name already exists then the value will be overwritten.
     * if value is null and header with provided name already exists then it will be removed.
     *
     * @param name the name
     * @param value the value
     * @return this HttpHeaders
     */
    public void put(String name, String value) {
        if (BYPASS_LIST.containsKey(name)) {
            throw new IllegalArgumentException("Use preferred name for header:" + name);
        }

        putInternal(name, value);
    }

    protected abstract void putInternal(String name, String value);

    /**
     * Set a header.
     *
     * if header with same name already exists then the value will be overwritten.
     * if value is null and header with provided name already exists then it will be removed.
     *
     * @param name the name
     * @return this HttpHeaders
     */
    public void remove(String name) {
        if (BYPASS_LIST.containsKey(name)) {
            throw new IllegalArgumentException("Use preferred name for header:" + name);
        }

        removeInternal(name);
    }

    protected abstract void removeInternal(String name);

    /**
     * Get the header value for the provided header name. Null will be returned if the header
     * name isn't found.
     *
     * @param name the name of the header to look for
     * @return The String value of the header, or null if the header isn't found
     */
    public String getValue(String name) {
        if (BYPASS_LIST.containsKey(name)) {
            throw new IllegalArgumentException("Use preferred name for header:" + name);
        }

        return getValueInternal(name);
    }

    protected abstract String getValueInternal(String name);

    /**
     * Get the header values for the provided header name. Null will be returned if
     * the header name isn't found.
     *
     * @param name the name of the header to look for
     * @return the values of the header, or null if the header isn't found
     */
    public String[] getValues(String name) {
        final String headerValue = getValue(name);
        if(headerValue == null) {
            return null;
        }

        return StringUtils.split(headerValue, ",");
    }

    /**
     * Get {@link Map} representation of the HttpHeaders collection.
     *
     * @return the headers as map
     */
    public abstract Map<String, String> exportHeaders();
}

