// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cosmos.implementation.http;

import java.util.Map;

/**
 * Some doc TBD
 */
public abstract class  HttpHeaders
{
    // Need to find the right abstraction for Java
    public String ACTIVITY_ID = null;
    // public String ETAG = null;

    // public String LSN = null;
    // public String ITEM_LSN = null;
    // public String LLSN = null;
    // public String ITEM_LLSN = null;
    // public String COMMITTED_LSN = null;
    public String SERVER_VERSION = null;

    // public int REQUEST_CHARGE = 0;

    public String SESSION_TOKEN = null;
    public String CONSISTENCY_LEVEL = null;

    public String PARTITION_KEY = null;
    // public String EFFECTIVE_PARTITION_KEY = null;
    // public String COLLECTION_RID = null;

    // public String PARTITION_INDEX = null;
    // public String SERVICE_INDEX = null;

    // public int PARTITION_COUNT = 0;
    // public int WRITE_QUORUM = 0;

    // public String READ_REGIONS = null;
    // public String LAST_CHANGE_UTC = null;

    // public String NUMBER_READ_REGIONS = null;

    /**
     * Create a HttpHeaders instance with the provided initial headers.
     *
     * @param mergeHeaders the map of initial headers
     */
    public abstract void putAll(Map<String, String> mergeHeaders);

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
    public abstract void put(String name, String value);

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
    public abstract void putExtended(String name, String value);

    /**
     * Set a header.
     *
     * if header with same name already exists then the value will be overwritten.
     * if value is null and header with provided name already exists then it will be removed.
     *
     * @param name the name
     * @return this HttpHeaders
     */
    public abstract void remove(String name);

    /**
     * Get the header value for the provided header name. Null will be returned if the header
     * name isn't found.
     *
     * @param name the name of the header to look for
     * @return The String value of the header, or null if the header isn't found
     */
    public abstract String getValue(String name);

    /**
     * Get the header value for the provided header name. Null will be returned if the header
     * name isn't found.
     *
     * Its an expensive and generic one
     *
     * @param name the name of the header to look for
     * @return The String value of the header, or null if the header isn't found
     */
    public abstract String getValueExtended(String name);

    /**
     * Get the header values for the provided header name. Null will be returned if
     * the header name isn't found.
     *
     * @param name the name of the header to look for
     * @return the values of the header, or null if the header isn't found
     */
    public abstract String[] getValues(String name);

    /**
     * Get {@link Map} snapshot representation of the HttpHeaders collection.
     * Its a copy of the current snapshot. Its expensive and might impact performance
     *
     * @return the headers as map
     */
    public abstract Map<String, String> exportHeaders();
}

