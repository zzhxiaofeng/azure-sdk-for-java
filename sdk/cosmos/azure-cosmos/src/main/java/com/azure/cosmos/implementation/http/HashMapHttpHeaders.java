// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.implementation.http;

import com.azure.cosmos.implementation.apachecommons.lang.NotImplementedException;
import com.azure.cosmos.implementation.apachecommons.lang.StringUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A collection of headers on an HTTP request or response.
 */
public class HashMapHttpHeaders implements HttpHeaders {
    private Map<String, String> headers;

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
        if (value == null) {
            headers.remove(headerKey);
        } else {
            headers.put(headerKey, value);
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
        return headers.get(normalizeName(name));
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
        return headers;
    }

    private String normalizeName(String input) {
        // return input.toLowerCase(Locale.ROOT);
        return  input;
    }
}
