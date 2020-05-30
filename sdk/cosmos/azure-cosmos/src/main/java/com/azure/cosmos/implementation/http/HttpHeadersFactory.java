// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.implementation.http;

import com.azure.cosmos.implementation.HttpConstants;
import com.azure.cosmos.implementation.apachecommons.lang.StringUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A collection of headers on an HTTP request or response.
 */
public class HttpHeadersFactory {
    private static final String defaultHeaderCountName = "com.azure.cosmos.http.default.headerssize";
    private static final int systemDefaultHeadersCount = 32; // Only re-allocations on 23rd header insert

    // Not marked volatile and will eventually catch-up
    // read-optimized and paying memory barrier cost
    private static int defaultHeadersCount = 0;

    public static HttpHeaders create() {
        return create(getDefaultHeadersCount());
    }

    public static HttpHeaders create(int initialCapacity) {
        return new HashMapHttpHeaders(initialCapacity);
    }

    private static int getDefaultHeadersCount() {
        if (defaultHeadersCount == 0) {
            String value = System.getProperty(defaultHeaderCountName);
            if (StringUtils.isNotEmpty(value)) {
                try {
                    defaultHeadersCount = Integer.parseInt(value);
                } catch (NumberFormatException ex) {
                }
            }

            if (defaultHeadersCount == 0) {
                defaultHeadersCount = systemDefaultHeadersCount;
            }
        }

        return defaultHeadersCount;
    }


    public static HttpHeaders fromNettyHeaders(io.netty.handler.codec.http.HttpHeaders nettyHeaders) {
        HttpHeaders newHeaders = HttpHeadersFactory.create(nettyHeaders.size());
        nettyHeaders.forEach(e ->
            {
                if (HttpConstants.Headers.ACTIVITY_ID.equalsIgnoreCase(e.getKey())) {
                    newHeaders.setActivityId(e.getValue());
                } else {
                    newHeaders.put(e.getKey(), e.getValue());
                }
            });

        return newHeaders;
    }
}
