/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.synapse.v2019_06_01_preview.implementation;

import com.microsoft.azure.management.synapse.v2019_06_01_preview.AutoScaleProperties;
import org.joda.time.DateTime;
import com.microsoft.azure.management.synapse.v2019_06_01_preview.AutoPauseProperties;
import com.microsoft.azure.management.synapse.v2019_06_01_preview.LibraryRequirements;
import com.microsoft.azure.management.synapse.v2019_06_01_preview.NodeSize;
import com.microsoft.azure.management.synapse.v2019_06_01_preview.NodeSizeFamily;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;
import com.microsoft.azure.Resource;

/**
 * Big Data pool.
 * A Big Data pool.
 */
@JsonFlatten
public class BigDataPoolResourceInfoInner extends Resource {
    /**
     * The state of the Big Data pool.
     */
    @JsonProperty(value = "properties.provisioningState")
    private String provisioningState;

    /**
     * Auto-scaling properties.
     */
    @JsonProperty(value = "properties.autoScale")
    private AutoScaleProperties autoScale;

    /**
     * The time when the Big Data pool was created.
     */
    @JsonProperty(value = "properties.creationDate")
    private DateTime creationDate;

    /**
     * Auto-pausing properties.
     */
    @JsonProperty(value = "properties.autoPause")
    private AutoPauseProperties autoPause;

    /**
     * The Spark events folder.
     */
    @JsonProperty(value = "properties.sparkEventsFolder")
    private String sparkEventsFolder;

    /**
     * The number of nodes in the Big Data pool.
     */
    @JsonProperty(value = "properties.nodeCount")
    private Integer nodeCount;

    /**
     * Library version requirements.
     */
    @JsonProperty(value = "properties.libraryRequirements")
    private LibraryRequirements libraryRequirements;

    /**
     * The Apache Spark version.
     */
    @JsonProperty(value = "properties.sparkVersion")
    private String sparkVersion;

    /**
     * The default folder where Spark logs will be written.
     */
    @JsonProperty(value = "properties.defaultSparkLogFolder")
    private String defaultSparkLogFolder;

    /**
     * The level of compute power that each node in the Big Data pool has.
     * Possible values include: 'None', 'Small', 'Medium', 'Large'.
     */
    @JsonProperty(value = "properties.nodeSize")
    private NodeSize nodeSize;

    /**
     * The kind of nodes that the Big Data pool provides. Possible values
     * include: 'None', 'MemoryOptimized'.
     */
    @JsonProperty(value = "properties.nodeSizeFamily")
    private NodeSizeFamily nodeSizeFamily;

    /**
     * Get the state of the Big Data pool.
     *
     * @return the provisioningState value
     */
    public String provisioningState() {
        return this.provisioningState;
    }

    /**
     * Set the state of the Big Data pool.
     *
     * @param provisioningState the provisioningState value to set
     * @return the BigDataPoolResourceInfoInner object itself.
     */
    public BigDataPoolResourceInfoInner withProvisioningState(String provisioningState) {
        this.provisioningState = provisioningState;
        return this;
    }

    /**
     * Get auto-scaling properties.
     *
     * @return the autoScale value
     */
    public AutoScaleProperties autoScale() {
        return this.autoScale;
    }

    /**
     * Set auto-scaling properties.
     *
     * @param autoScale the autoScale value to set
     * @return the BigDataPoolResourceInfoInner object itself.
     */
    public BigDataPoolResourceInfoInner withAutoScale(AutoScaleProperties autoScale) {
        this.autoScale = autoScale;
        return this;
    }

    /**
     * Get the time when the Big Data pool was created.
     *
     * @return the creationDate value
     */
    public DateTime creationDate() {
        return this.creationDate;
    }

    /**
     * Set the time when the Big Data pool was created.
     *
     * @param creationDate the creationDate value to set
     * @return the BigDataPoolResourceInfoInner object itself.
     */
    public BigDataPoolResourceInfoInner withCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    /**
     * Get auto-pausing properties.
     *
     * @return the autoPause value
     */
    public AutoPauseProperties autoPause() {
        return this.autoPause;
    }

    /**
     * Set auto-pausing properties.
     *
     * @param autoPause the autoPause value to set
     * @return the BigDataPoolResourceInfoInner object itself.
     */
    public BigDataPoolResourceInfoInner withAutoPause(AutoPauseProperties autoPause) {
        this.autoPause = autoPause;
        return this;
    }

    /**
     * Get the Spark events folder.
     *
     * @return the sparkEventsFolder value
     */
    public String sparkEventsFolder() {
        return this.sparkEventsFolder;
    }

    /**
     * Set the Spark events folder.
     *
     * @param sparkEventsFolder the sparkEventsFolder value to set
     * @return the BigDataPoolResourceInfoInner object itself.
     */
    public BigDataPoolResourceInfoInner withSparkEventsFolder(String sparkEventsFolder) {
        this.sparkEventsFolder = sparkEventsFolder;
        return this;
    }

    /**
     * Get the number of nodes in the Big Data pool.
     *
     * @return the nodeCount value
     */
    public Integer nodeCount() {
        return this.nodeCount;
    }

    /**
     * Set the number of nodes in the Big Data pool.
     *
     * @param nodeCount the nodeCount value to set
     * @return the BigDataPoolResourceInfoInner object itself.
     */
    public BigDataPoolResourceInfoInner withNodeCount(Integer nodeCount) {
        this.nodeCount = nodeCount;
        return this;
    }

    /**
     * Get library version requirements.
     *
     * @return the libraryRequirements value
     */
    public LibraryRequirements libraryRequirements() {
        return this.libraryRequirements;
    }

    /**
     * Set library version requirements.
     *
     * @param libraryRequirements the libraryRequirements value to set
     * @return the BigDataPoolResourceInfoInner object itself.
     */
    public BigDataPoolResourceInfoInner withLibraryRequirements(LibraryRequirements libraryRequirements) {
        this.libraryRequirements = libraryRequirements;
        return this;
    }

    /**
     * Get the Apache Spark version.
     *
     * @return the sparkVersion value
     */
    public String sparkVersion() {
        return this.sparkVersion;
    }

    /**
     * Set the Apache Spark version.
     *
     * @param sparkVersion the sparkVersion value to set
     * @return the BigDataPoolResourceInfoInner object itself.
     */
    public BigDataPoolResourceInfoInner withSparkVersion(String sparkVersion) {
        this.sparkVersion = sparkVersion;
        return this;
    }

    /**
     * Get the default folder where Spark logs will be written.
     *
     * @return the defaultSparkLogFolder value
     */
    public String defaultSparkLogFolder() {
        return this.defaultSparkLogFolder;
    }

    /**
     * Set the default folder where Spark logs will be written.
     *
     * @param defaultSparkLogFolder the defaultSparkLogFolder value to set
     * @return the BigDataPoolResourceInfoInner object itself.
     */
    public BigDataPoolResourceInfoInner withDefaultSparkLogFolder(String defaultSparkLogFolder) {
        this.defaultSparkLogFolder = defaultSparkLogFolder;
        return this;
    }

    /**
     * Get the level of compute power that each node in the Big Data pool has. Possible values include: 'None', 'Small', 'Medium', 'Large'.
     *
     * @return the nodeSize value
     */
    public NodeSize nodeSize() {
        return this.nodeSize;
    }

    /**
     * Set the level of compute power that each node in the Big Data pool has. Possible values include: 'None', 'Small', 'Medium', 'Large'.
     *
     * @param nodeSize the nodeSize value to set
     * @return the BigDataPoolResourceInfoInner object itself.
     */
    public BigDataPoolResourceInfoInner withNodeSize(NodeSize nodeSize) {
        this.nodeSize = nodeSize;
        return this;
    }

    /**
     * Get the kind of nodes that the Big Data pool provides. Possible values include: 'None', 'MemoryOptimized'.
     *
     * @return the nodeSizeFamily value
     */
    public NodeSizeFamily nodeSizeFamily() {
        return this.nodeSizeFamily;
    }

    /**
     * Set the kind of nodes that the Big Data pool provides. Possible values include: 'None', 'MemoryOptimized'.
     *
     * @param nodeSizeFamily the nodeSizeFamily value to set
     * @return the BigDataPoolResourceInfoInner object itself.
     */
    public BigDataPoolResourceInfoInner withNodeSizeFamily(NodeSizeFamily nodeSizeFamily) {
        this.nodeSizeFamily = nodeSizeFamily;
        return this;
    }

}