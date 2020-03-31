/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * jkl
 */

package com.microsoft.azure.management.apimanagement.v2019_12_01.implementation;

import com.microsoft.azure.arm.model.implementation.WrapperImpl;
import com.microsoft.azure.management.apimanagement.v2019_12_01.Gateways;
import rx.Completable;
import rx.functions.Func1;
import rx.Observable;
import com.microsoft.azure.Page;
import com.microsoft.azure.management.apimanagement.v2019_12_01.GatewayContract;
import com.microsoft.azure.management.apimanagement.v2019_12_01.GatewayKeysContract;
import com.microsoft.azure.management.apimanagement.v2019_12_01.GatewayTokenContract;
import com.microsoft.azure.management.apimanagement.v2019_12_01.KeyType;
import com.microsoft.azure.management.apimanagement.v2019_12_01.GatewayTokenRequestContract;

class GatewaysImpl extends WrapperImpl<GatewaysInner> implements Gateways {
    private final ApiManagementManager manager;

    GatewaysImpl(ApiManagementManager manager) {
        super(manager.inner().gateways());
        this.manager = manager;
    }

    public ApiManagementManager manager() {
        return this.manager;
    }

    @Override
    public GatewayContractImpl define(String name) {
        return wrapModel(name);
    }

    private GatewayContractImpl wrapModel(GatewayContractInner inner) {
        return  new GatewayContractImpl(inner, manager());
    }

    private GatewayContractImpl wrapModel(String name) {
        return new GatewayContractImpl(name, this.manager());
    }

    @Override
    public Observable<GatewayContract> listByServiceAsync(final String resourceGroupName, final String serviceName) {
        GatewaysInner client = this.inner();
        return client.listByServiceAsync(resourceGroupName, serviceName)
        .flatMapIterable(new Func1<Page<GatewayContractInner>, Iterable<GatewayContractInner>>() {
            @Override
            public Iterable<GatewayContractInner> call(Page<GatewayContractInner> page) {
                return page.items();
            }
        })
        .map(new Func1<GatewayContractInner, GatewayContract>() {
            @Override
            public GatewayContract call(GatewayContractInner inner) {
                return new GatewayContractImpl(inner, manager());
            }
        });
    }

    @Override
    public Completable getEntityTagAsync(String resourceGroupName, String serviceName, String gatewayId) {
        GatewaysInner client = this.inner();
        return client.getEntityTagAsync(resourceGroupName, serviceName, gatewayId).toCompletable();
    }

    @Override
    public Observable<GatewayContract> getAsync(String resourceGroupName, String serviceName, String gatewayId) {
        GatewaysInner client = this.inner();
        return client.getAsync(resourceGroupName, serviceName, gatewayId)
        .map(new Func1<GatewayContractInner, GatewayContract>() {
            @Override
            public GatewayContract call(GatewayContractInner inner) {
                return new GatewayContractImpl(inner, manager());
            }
        });
    }

    @Override
    public Completable deleteAsync(String resourceGroupName, String serviceName, String gatewayId, String ifMatch) {
        GatewaysInner client = this.inner();
        return client.deleteAsync(resourceGroupName, serviceName, gatewayId, ifMatch).toCompletable();
    }

    @Override
    public Observable<GatewayKeysContract> listKeysAsync(String resourceGroupName, String serviceName, String gatewayId) {
        GatewaysInner client = this.inner();
        return client.listKeysAsync(resourceGroupName, serviceName, gatewayId)
        .map(new Func1<GatewayKeysContractInner, GatewayKeysContract>() {
            @Override
            public GatewayKeysContract call(GatewayKeysContractInner inner) {
                return new GatewayKeysContractImpl(inner, manager());
            }
        });
    }

    @Override
    public Completable regenerateKeyAsync(String resourceGroupName, String serviceName, String gatewayId, KeyType keyType) {
        GatewaysInner client = this.inner();
        return client.regenerateKeyAsync(resourceGroupName, serviceName, gatewayId, keyType).toCompletable();
    }

    @Override
    public Observable<GatewayTokenContract> generateTokenAsync(String resourceGroupName, String serviceName, String gatewayId, GatewayTokenRequestContract parameters) {
        GatewaysInner client = this.inner();
        return client.generateTokenAsync(resourceGroupName, serviceName, gatewayId, parameters)
        .map(new Func1<GatewayTokenContractInner, GatewayTokenContract>() {
            @Override
            public GatewayTokenContract call(GatewayTokenContractInner inner) {
                return new GatewayTokenContractImpl(inner, manager());
            }
        });
    }

}