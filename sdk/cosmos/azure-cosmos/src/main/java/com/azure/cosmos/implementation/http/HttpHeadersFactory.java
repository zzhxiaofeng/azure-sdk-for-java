// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.implementation.http;

import com.azure.cosmos.implementation.apachecommons.lang.StringUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A collection of headers on an HTTP request or response.
 */
public class HttpHeadersFactory {
    public static HttpHeaders create() {
        return new HashMapHttpHeaders();
    }

    public static HttpHeaders create(int initialCapacity) {
        return new HashMapHttpHeaders(initialCapacity);
    }
}
