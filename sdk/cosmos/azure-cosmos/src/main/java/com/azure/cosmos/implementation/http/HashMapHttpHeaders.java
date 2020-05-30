// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.implementation.http;

import com.azure.cosmos.implementation.HttpConstants;
import com.azure.cosmos.implementation.apachecommons.lang.NotImplementedException;
import com.azure.cosmos.implementation.apachecommons.lang.StringUtils;
import com.azure.cosmos.implementation.guava25.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * A collection of headers on an HTTP request or response.
 */
public class HashMapHttpHeaders extends HttpHeaders {
    private Map<String, String> headers;

    private static final ImmutableMap<String, String> BYPASS_LIST = ImmutableMap.of(
        "x-ms-serviceversion", "A",
        "x-ms-session-token", "A",
        "x-ms-consistency-level", "A",
        "x-ms-activity-id", "A",
        "x-ms-documentdb-partitionkey", "A");

    /**
     * Create an empty HttpHeaders instance.
     */
    private HashMapHttpHeaders() {
        throw new NotImplementedException("Unsupported usage");
    }

    /**
     * Create an HttpHeaders instance with the given size.
     */
    public HashMapHttpHeaders(int size) {
        this.headers = new HashMap<>(size);
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
    public int size() {
        return headers.size();
    }

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
        final String headerKey = normalizeName(name);
        if (BYPASS_LIST.containsKey(headerKey)) {
            throw new IllegalArgumentException("Use preferred name for header:" + headerKey);
        }

        if (value == null) {
            headers.remove(headerKey);
        } else {
            headers.put(headerKey, value);
        }
    }

    public void putExtended(String name, String value) {
        final String headerKey = normalizeName(name);
        if (headerKey.equalsIgnoreCase(HttpConstants.Headers.SESSION_TOKEN)) {
            SESSION_TOKEN = value;
        } else if (headerKey.equalsIgnoreCase(HttpConstants.Headers.ACTIVITY_ID)) {
            ACTIVITY_ID = value;
        } else if (headerKey.equalsIgnoreCase(HttpConstants.Headers.CONSISTENCY_LEVEL)) {
            CONSISTENCY_LEVEL = value;
        } else if (headerKey.equalsIgnoreCase(HttpConstants.Headers.SERVER_VERSION)) {
            SERVER_VERSION = value;
        } else if (headerKey.equalsIgnoreCase(HttpConstants.Headers.PARTITION_KEY)) {
            PARTITION_KEY = value;
        } else {
            put(name, value);
        }
    }

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
        final String headerKey = normalizeName(name);
        headers.remove(headerKey);
    }

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

        return headers.get(normalizeName(name));
    }

    public String getValueExtended(String name) {
        final String headerKey = normalizeName(name);
        if (headerKey.equalsIgnoreCase(HttpConstants.Headers.SESSION_TOKEN)) {
            return SESSION_TOKEN;
        }
        if (headerKey.equalsIgnoreCase(HttpConstants.Headers.ACTIVITY_ID)) {
            return ACTIVITY_ID;
        }
        if (headerKey.equalsIgnoreCase(HttpConstants.Headers.CONSISTENCY_LEVEL)) {
            return CONSISTENCY_LEVEL;
        }
        if (headerKey.equalsIgnoreCase(HttpConstants.Headers.SERVER_VERSION)) {
            return SERVER_VERSION;
        }
        if (headerKey.equalsIgnoreCase(HttpConstants.Headers.PARTITION_KEY)) {
            return PARTITION_KEY;
        }

        return getValue(name);
    }

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
    public Map<String, String> exportHeaders() {
        Map<String, String> newHeaders = new HashMap<>(headers);

        // TODO:kirankk ability to assert on complete list below
        if (SESSION_TOKEN != null) {
            newHeaders.put(HttpConstants.Headers.SESSION_TOKEN, SESSION_TOKEN);
        }
        if (ACTIVITY_ID != null) {
            newHeaders.put(HttpConstants.Headers.ACTIVITY_ID, ACTIVITY_ID);
        }
        if (CONSISTENCY_LEVEL != null) {
            newHeaders.put(HttpConstants.Headers.CONSISTENCY_LEVEL, CONSISTENCY_LEVEL);
        }
        if (SERVER_VERSION != null) {
            newHeaders.put(HttpConstants.Headers.SERVER_VERSION, SERVER_VERSION);
        }
        if (PARTITION_KEY != null) {
            newHeaders.put(HttpConstants.Headers.PARTITION_KEY, PARTITION_KEY);
        }

        return newHeaders;
    }

    private String normalizeName(String input) {
        // return input.toLowerCase(Locale.ROOT);
        return  input;
    }
}
