/*
 * Copyright (c) 2015 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */

package org.opendaylight.openflowplugin.api.openflow;

import java.util.Collection;
import java.util.Map;
import org.opendaylight.controller.md.sal.binding.api.BindingService;
import org.opendaylight.controller.md.sal.binding.api.DataBroker;
import org.opendaylight.controller.md.sal.binding.api.NotificationPublishService;
import org.opendaylight.controller.md.sal.binding.api.NotificationService;
import org.opendaylight.controller.md.sal.common.api.clustering.EntityOwnershipService;
import org.opendaylight.controller.sal.binding.api.RpcProviderRegistry;
import org.opendaylight.mdsal.singleton.common.api.ClusterSingletonServiceProvider;
import org.opendaylight.openflowjava.protocol.spi.connection.SwitchConnectionProvider;

/**
 * Plugin services provider
 */
public interface OpenFlowPluginProvider extends AutoCloseable, BindingService {

    /**
     * Method sets openflow java's connection providers.
     */
    void setSwitchConnectionProviders(Collection<SwitchConnectionProvider> switchConnectionProvider);

    /**
     * setter
     *
     * @param dataBroker
     */
    void setDataBroker(DataBroker dataBroker);

    void setRpcProviderRegistry(RpcProviderRegistry rpcProviderRegistry);

    void setNotificationProviderService(NotificationService notificationProviderService);

    void setNotificationPublishService(NotificationPublishService notificationPublishService);

    void setClusteringSingletonServicesProvider(ClusterSingletonServiceProvider singletonServicesProvider);

    void setEntityOwnershipServiceProvider(EntityOwnershipService entityOwnershipService);
    /**
     * Method initializes all DeviceManager, RpcManager and related contexts.
     */
    void initialize();

    /**
     * This parameter indicates whether it is mandatory for switch to support OF1.3 features : table, flow, meter,group.
     * If this is set to true and switch doesn't support these features its connection will be denied.
     * @param switchFeaturesMandatory
     */
    void setSwitchFeaturesMandatory(final boolean switchFeaturesMandatory);

    boolean isSwitchFeaturesMandatory();

    boolean isStatisticsPollingOn();

    void setStatisticsPollingOn(final boolean isStatisticsPollingOn);

    /**
     * Backward compatibility feature - exposing rpc for statistics polling (result is provided in form of async notification)
     *
     * @param isStatisticsRpcEnabled
     */
    void setIsStatisticsRpcEnabled(boolean isStatisticsRpcEnabled);

    void setBarrierCountLimit(int barrierCountLimit);

    void setBarrierInterval(long barrierTimeoutLimit);

    void setEchoReplyTimeout(long echoReplyTimeout);

    void setFlowRemovedNotification(boolean isFlowRemovedNotificationOn);

    void update(Map<String,Object> props);

    void setSkipTableFeatures(boolean skipTableFeatures);

    void setBasicTimerDelay(long basicTimerDelay);

    void setMaximumTimerDelay(long maximumTimerDelay);
}
