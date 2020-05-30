// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.implementation.http;

import com.azure.cosmos.implementation.HttpConstants;
import com.azure.cosmos.implementation.apachecommons.lang.StringUtils;
import com.azure.cosmos.implementation.guava25.base.Function;
import com.azure.cosmos.implementation.guava25.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Some doc TBD
 */
public abstract class HttpHeaders
{
    private static final boolean enableValidation = HttpHeadersFactory.getBooleanConfig(HttpHeadersFactory.includePromotedHeaderValidation);
    private static final ImmutableMap<String, String> BYPASS_LIST = getPromotedHeadersMap();

    private static ImmutableMap<String, String> getPromotedHeadersMap() {
        ImmutableMap.Builder<String, String> builder = new ImmutableMap.Builder<>();
        builder.put("x-ms-serviceversion", "A");
        builder.put("x-ms-session-token", "A");
        builder.put("x-ms-documentdb-partitionkey", "A");
        builder.put("x-ms-consistency-level", "A");
        builder.put("x-ms-activity-id", "A");
        builder.put("etag", "A");
        builder.put("x-ms-request-charge", "A");

        return builder.build();
    }

    public String ActivityId = null;
    public String SessionToken = null;
    public String ConsistencyLevel = null;
    public String ServiceVersion = null;
    public String PartitionKey = null;

    public String Etag = null;
    public String RequestCharge = null;

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
        if (enableValidation && BYPASS_LIST.containsKey(name)) {
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
        if (enableValidation && BYPASS_LIST.containsKey(name)) {
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
        if (enableValidation && BYPASS_LIST.containsKey(name)) {
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
    public Map<String, String> exportHeaders() {
        return exportHeaders(null);
    }

    public Map<String, String> exportHeaders(Function<String, Boolean> nameFilerFunc) {
        // TODO: Revisit hardcoded value - 5
        Map<String, String> headerCopy = new HashMap<>(exportHeadersInternal().size() + 5);

        for(final Map.Entry<String, String> header : exportHeadersInternal().entrySet()) {
            if(nameFilerFunc == null || !nameFilerFunc.apply(header.getKey())) {
                headerCopy.put(header.getKey(), header.getValue());
            }
        }

        copyHeader(HttpConstants.Headers.ACTIVITY_ID, ActivityId, nameFilerFunc, headerCopy);
        copyHeader(HttpConstants.Headers.SESSION_TOKEN, SessionToken, nameFilerFunc, headerCopy);
        copyHeader(HttpConstants.Headers.CONSISTENCY_LEVEL, ConsistencyLevel, nameFilerFunc, headerCopy);
        copyHeader(HttpConstants.Headers.SERVER_VERSION, ServiceVersion, nameFilerFunc, headerCopy);
        copyHeader(HttpConstants.Headers.PARTITION_KEY_RANGE_ID, PartitionKey, nameFilerFunc, headerCopy);
        copyHeader(HttpConstants.Headers.E_TAG, Etag, nameFilerFunc, headerCopy);
        copyHeader(HttpConstants.Headers.REQUEST_CHARGE, RequestCharge, nameFilerFunc, headerCopy);

        return headerCopy;
    }

    private void copyHeader(String headername, String headerValue, Function<String,
            Boolean> nameFilerFunc, Map<String, String> target) {
        if (headerValue != null && (nameFilerFunc == null || !nameFilerFunc.apply(headername))) {
            target.put(HttpConstants.Headers.REQUEST_CHARGE, headerValue);
        }
    }

    public void includeIfNotExistsHeaders(Map<String, String> defaultHeaders) {
        for (final Map.Entry<String, String> header : defaultHeaders.entrySet()) {
            switch (header.getKey().toLowerCase()) {
                case HttpConstants.Headers.ACTIVITY_ID:
                    if (ActivityId == null) {
                        ActivityId = header.getValue();
                    }
                    break;
                case HttpConstants.Headers.SESSION_TOKEN:
                    if (SessionToken == null) {
                        SessionToken = header.getValue();
                    }
                    break;
                case HttpConstants.Headers.CONSISTENCY_LEVEL:
                    if (ConsistencyLevel == null) {
                        ConsistencyLevel = header.getValue();
                    }
                    break;
                case HttpConstants.Headers.SERVER_VERSION:
                    if (ServiceVersion == null) {
                        ServiceVersion = header.getValue();
                    }
                    break;
                case HttpConstants.Headers.PARTITION_KEY:
                    if (PartitionKey == null) {
                        PartitionKey = header.getValue();
                    }
                    break;
                case HttpConstants.Headers.E_TAG:
                    if (Etag == null) {
                        Etag = header.getValue();
                    }
                    break;
                case HttpConstants.Headers.REQUEST_CHARGE:
                    if (RequestCharge == null) {
                        RequestCharge = header.getValue();
                    }
                    break;
                default:
                    put(header.getKey(), header.getValue());
                    break;
            }
        }
    }

    protected abstract Map<String, String> exportHeadersInternal();
}

