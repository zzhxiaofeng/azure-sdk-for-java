// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.implementation.http;

import com.azure.cosmos.implementation.apachecommons.lang.NotImplementedException;
import java.util.HashMap;
import java.util.Map;

/**
 * A collection of headers on an HTTP request or response.
 */
public class HashMapHttpHeaders extends HttpHeaders {
    private Map<String, String> headers;

    private HashMapHttpHeaders() {
        throw new NotImplementedException("Unsupported usage");
    }

    public HashMapHttpHeaders(int size) {
        this.headers = new HashMap<>(size);
    }

    public int size() {
        return headers.size();
    }

    public void putInternal(String name, String value) {
        final String headerKey = normalizeName(name);
        if (value == null) {
            headers.remove(headerKey);
        } else {
            headers.put(headerKey, value);
        }
    }

    public void removeInternal(String name) {
        final String headerKey = normalizeName(name);
        headers.remove(headerKey);
    }

    public String getValueInternal(String name) {
        return headers.get(normalizeName(name));
    }

    public Map<String, String> exportHeadersInternal() {
        return headers;
    }

    private String normalizeName(String input) {
        // return input.toLowerCase(Locale.ROOT);
        return  input;
    }
}
