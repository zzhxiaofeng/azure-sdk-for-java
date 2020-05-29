// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.azure.cosmos.implementation.directconnectivity;

import com.azure.core.http.HttpHeader;
import com.azure.cosmos.implementation.http.HttpHeaders;
import com.azure.cosmos.implementation.HttpConstants;
import com.azure.cosmos.implementation.http.HttpHeadersFactory;
import com.azure.cosmos.implementation.http.HttpResponse;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpUtilsTest {

    private static final String OWNER_FULL_NAME_VALUE = "dbs/RxJava.SDKTest.SharedDatabase_20190304T121302_iZc/colls/+%20-_,:.%7C~b2d67001-9000-454e-a140-abceb1756c48%20+-_,:.%7C~";

    @Test(groups = { "unit" })
    public void verifyConversionOfHttpResponseHeadersToMap() {
        HttpHeaders headersMap = HttpHeadersFactory.create();
        headersMap.put(HttpConstants.Headers.OWNER_FULL_NAME, OWNER_FULL_NAME_VALUE);

        HttpUtils.unescapeOwnerFullName(headersMap);

        HttpResponse httpResponse = Mockito.mock(HttpResponse.class);
        Mockito.when(httpResponse.headers()).thenReturn(headersMap);
        HttpHeaders httpResponseHeaders = httpResponse.headers();

        assertThat(httpResponseHeaders.size()).isEqualTo(1);
        Map.Entry<String, String> entry = httpResponseHeaders.exportHeaders().entrySet().iterator().next();
        assertThat(entry.getKey()).isEqualTo(HttpConstants.Headers.OWNER_FULL_NAME);
        assertThat(entry.getValue()).isEqualTo(HttpUtils.urlDecode(OWNER_FULL_NAME_VALUE));

        HttpUtils.unescapeOwnerFullName(httpResponseHeaders);
        assertThat(httpResponseHeaders.size()).isEqualTo(1);
        entry = httpResponseHeaders.exportHeaders().entrySet().iterator().next();
        assertThat(entry.getKey()).isEqualTo(HttpConstants.Headers.OWNER_FULL_NAME);
        assertThat(entry.getValue()).isEqualTo(HttpUtils.urlDecode(OWNER_FULL_NAME_VALUE));
    }
}
