/*
 * Copyright 2025 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.deploy.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.deploy.v1.CloudDeployClient;
import com.google.cloud.deploy.v1.CloudDeploySettings;
import com.google.cloud.spring.autoconfigure.core.GcpContextAutoConfiguration;
import com.google.cloud.spring.core.DefaultCredentialsProvider;
import com.google.cloud.spring.core.Retry;
import com.google.cloud.spring.core.util.RetryUtil;
import java.io.IOException;
import java.util.Collections;
import javax.annotation.Generated;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/**
 * Auto-configuration for {@link CloudDeployClient}.
 *
 * <p>Provides auto-configuration for Spring Boot
 *
 * <p>The default instance has everything set to sensible defaults:
 *
 * <ul>
 *   <li>The default transport provider is used.
 *   <li>Credentials are acquired automatically through Application Default Credentials.
 *   <li>Retries are configured for idempotent methods but not for non-idempotent methods.
 * </ul>
 */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@AutoConfiguration
@AutoConfigureAfter(GcpContextAutoConfiguration.class)
@ConditionalOnClass(CloudDeployClient.class)
@ConditionalOnProperty(
    value = "com.google.cloud.deploy.v1.cloud-deploy.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(CloudDeploySpringProperties.class)
public class CloudDeploySpringAutoConfiguration {
  private final CloudDeploySpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER = LogFactory.getLog(CloudDeploySpringAutoConfiguration.class);

  protected CloudDeploySpringAutoConfiguration(
      CloudDeploySpringProperties clientProperties, CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from CloudDeploy-specific configuration");
      }
      this.credentialsProvider =
          ((CredentialsProvider) new DefaultCredentialsProvider(this.clientProperties));
    } else {
      this.credentialsProvider = credentialsProvider;
    }
  }

  /**
   * Provides a default transport channel provider bean, corresponding to the client library's
   * default transport channel provider. If the library supports both GRPC and REST transport, and
   * the useRest property is configured, the HTTP/JSON transport provider will be used instead of
   * GRPC.
   *
   * @return a default transport channel provider.
   */
  @Bean
  @ConditionalOnMissingBean(name = "defaultCloudDeployTransportChannelProvider")
  public TransportChannelProvider defaultCloudDeployTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return CloudDeploySettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return CloudDeploySettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a CloudDeploySettings bean configured to use a DefaultCredentialsProvider and the
   * client library's default transport channel provider
   * (defaultCloudDeployTransportChannelProvider()). It also configures the quota project ID and
   * executor thread count, if provided through properties.
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in CloudDeploySpringProperties. Method-level properties will take precedence over service-level
   * properties if available, and client library defaults will be used if neither are specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link CloudDeploySettings} bean configured with {@link TransportChannelProvider}
   *     bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public CloudDeploySettings cloudDeploySettings(
      @Qualifier("defaultCloudDeployTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    CloudDeploySettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = CloudDeploySettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = CloudDeploySettings.newBuilder();
    }
    clientSettingsBuilder
        .setCredentialsProvider(this.credentialsProvider)
        .setTransportChannelProvider(defaultTransportChannelProvider)
        .setEndpoint(CloudDeploySettings.getDefaultEndpoint())
        .setHeaderProvider(this.userAgentHeaderProvider());
    if (this.clientProperties.getQuotaProjectId() != null) {
      clientSettingsBuilder.setQuotaProjectId(this.clientProperties.getQuotaProjectId());
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Quota project id set to "
                + this.clientProperties.getQuotaProjectId()
                + ", this overrides project id from credentials.");
      }
    }
    if (this.clientProperties.getExecutorThreadCount() != null) {
      ExecutorProvider executorProvider =
          CloudDeploySettings.defaultExecutorProviderBuilder()
              .setExecutorThreadCount(this.clientProperties.getExecutorThreadCount())
              .build();
      clientSettingsBuilder.setBackgroundExecutorProvider(executorProvider);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Background executor thread count is "
                + this.clientProperties.getExecutorThreadCount());
      }
    }
    Retry serviceRetry = clientProperties.getRetry();
    if (serviceRetry != null) {
      RetrySettings listDeliveryPipelinesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listDeliveryPipelinesSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .listDeliveryPipelinesSettings()
          .setRetrySettings(listDeliveryPipelinesRetrySettings);

      RetrySettings getDeliveryPipelineRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getDeliveryPipelineSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .getDeliveryPipelineSettings()
          .setRetrySettings(getDeliveryPipelineRetrySettings);

      RetrySettings listTargetsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listTargetsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listTargetsSettings().setRetrySettings(listTargetsRetrySettings);

      RetrySettings rollbackTargetRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.rollbackTargetSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.rollbackTargetSettings().setRetrySettings(rollbackTargetRetrySettings);

      RetrySettings getTargetRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getTargetSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getTargetSettings().setRetrySettings(getTargetRetrySettings);

      RetrySettings listCustomTargetTypesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listCustomTargetTypesSettings().getRetrySettings(),
              serviceRetry);
      clientSettingsBuilder
          .listCustomTargetTypesSettings()
          .setRetrySettings(listCustomTargetTypesRetrySettings);

      RetrySettings getCustomTargetTypeRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getCustomTargetTypeSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .getCustomTargetTypeSettings()
          .setRetrySettings(getCustomTargetTypeRetrySettings);

      RetrySettings listReleasesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listReleasesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listReleasesSettings().setRetrySettings(listReleasesRetrySettings);

      RetrySettings getReleaseRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getReleaseSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getReleaseSettings().setRetrySettings(getReleaseRetrySettings);

      RetrySettings abandonReleaseRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.abandonReleaseSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.abandonReleaseSettings().setRetrySettings(abandonReleaseRetrySettings);

      RetrySettings listDeployPoliciesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listDeployPoliciesSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .listDeployPoliciesSettings()
          .setRetrySettings(listDeployPoliciesRetrySettings);

      RetrySettings getDeployPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getDeployPolicySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .getDeployPolicySettings()
          .setRetrySettings(getDeployPolicyRetrySettings);

      RetrySettings approveRolloutRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.approveRolloutSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.approveRolloutSettings().setRetrySettings(approveRolloutRetrySettings);

      RetrySettings advanceRolloutRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.advanceRolloutSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.advanceRolloutSettings().setRetrySettings(advanceRolloutRetrySettings);

      RetrySettings cancelRolloutRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.cancelRolloutSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.cancelRolloutSettings().setRetrySettings(cancelRolloutRetrySettings);

      RetrySettings listRolloutsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listRolloutsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listRolloutsSettings().setRetrySettings(listRolloutsRetrySettings);

      RetrySettings getRolloutRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getRolloutSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getRolloutSettings().setRetrySettings(getRolloutRetrySettings);

      RetrySettings ignoreJobRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.ignoreJobSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.ignoreJobSettings().setRetrySettings(ignoreJobRetrySettings);

      RetrySettings retryJobRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.retryJobSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.retryJobSettings().setRetrySettings(retryJobRetrySettings);

      RetrySettings listJobRunsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listJobRunsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listJobRunsSettings().setRetrySettings(listJobRunsRetrySettings);

      RetrySettings getJobRunRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getJobRunSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getJobRunSettings().setRetrySettings(getJobRunRetrySettings);

      RetrySettings terminateJobRunRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.terminateJobRunSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .terminateJobRunSettings()
          .setRetrySettings(terminateJobRunRetrySettings);

      RetrySettings getConfigRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getConfigSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getConfigSettings().setRetrySettings(getConfigRetrySettings);

      RetrySettings getAutomationRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getAutomationSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getAutomationSettings().setRetrySettings(getAutomationRetrySettings);

      RetrySettings listAutomationsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listAutomationsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .listAutomationsSettings()
          .setRetrySettings(listAutomationsRetrySettings);

      RetrySettings getAutomationRunRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getAutomationRunSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .getAutomationRunSettings()
          .setRetrySettings(getAutomationRunRetrySettings);

      RetrySettings listAutomationRunsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listAutomationRunsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .listAutomationRunsSettings()
          .setRetrySettings(listAutomationRunsRetrySettings);

      RetrySettings cancelAutomationRunRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.cancelAutomationRunSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .cancelAutomationRunSettings()
          .setRetrySettings(cancelAutomationRunRetrySettings);

      RetrySettings listLocationsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listLocationsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listLocationsSettings().setRetrySettings(listLocationsRetrySettings);

      RetrySettings getLocationRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getLocationSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getLocationSettings().setRetrySettings(getLocationRetrySettings);

      RetrySettings setIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.setIamPolicySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.setIamPolicySettings().setRetrySettings(setIamPolicyRetrySettings);

      RetrySettings getIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getIamPolicySettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getIamPolicySettings().setRetrySettings(getIamPolicyRetrySettings);

      RetrySettings testIamPermissionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.testIamPermissionsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .testIamPermissionsSettings()
          .setRetrySettings(testIamPermissionsRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry listDeliveryPipelinesRetry = clientProperties.getListDeliveryPipelinesRetry();
    if (listDeliveryPipelinesRetry != null) {
      RetrySettings listDeliveryPipelinesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listDeliveryPipelinesSettings().getRetrySettings(),
              listDeliveryPipelinesRetry);
      clientSettingsBuilder
          .listDeliveryPipelinesSettings()
          .setRetrySettings(listDeliveryPipelinesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listDeliveryPipelines from properties.");
      }
    }
    Retry getDeliveryPipelineRetry = clientProperties.getGetDeliveryPipelineRetry();
    if (getDeliveryPipelineRetry != null) {
      RetrySettings getDeliveryPipelineRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getDeliveryPipelineSettings().getRetrySettings(),
              getDeliveryPipelineRetry);
      clientSettingsBuilder
          .getDeliveryPipelineSettings()
          .setRetrySettings(getDeliveryPipelineRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for getDeliveryPipeline from properties.");
      }
    }
    Retry listTargetsRetry = clientProperties.getListTargetsRetry();
    if (listTargetsRetry != null) {
      RetrySettings listTargetsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listTargetsSettings().getRetrySettings(), listTargetsRetry);
      clientSettingsBuilder.listTargetsSettings().setRetrySettings(listTargetsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listTargets from properties.");
      }
    }
    Retry rollbackTargetRetry = clientProperties.getRollbackTargetRetry();
    if (rollbackTargetRetry != null) {
      RetrySettings rollbackTargetRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.rollbackTargetSettings().getRetrySettings(),
              rollbackTargetRetry);
      clientSettingsBuilder.rollbackTargetSettings().setRetrySettings(rollbackTargetRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for rollbackTarget from properties.");
      }
    }
    Retry getTargetRetry = clientProperties.getGetTargetRetry();
    if (getTargetRetry != null) {
      RetrySettings getTargetRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getTargetSettings().getRetrySettings(), getTargetRetry);
      clientSettingsBuilder.getTargetSettings().setRetrySettings(getTargetRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getTarget from properties.");
      }
    }
    Retry listCustomTargetTypesRetry = clientProperties.getListCustomTargetTypesRetry();
    if (listCustomTargetTypesRetry != null) {
      RetrySettings listCustomTargetTypesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listCustomTargetTypesSettings().getRetrySettings(),
              listCustomTargetTypesRetry);
      clientSettingsBuilder
          .listCustomTargetTypesSettings()
          .setRetrySettings(listCustomTargetTypesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listCustomTargetTypes from properties.");
      }
    }
    Retry getCustomTargetTypeRetry = clientProperties.getGetCustomTargetTypeRetry();
    if (getCustomTargetTypeRetry != null) {
      RetrySettings getCustomTargetTypeRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getCustomTargetTypeSettings().getRetrySettings(),
              getCustomTargetTypeRetry);
      clientSettingsBuilder
          .getCustomTargetTypeSettings()
          .setRetrySettings(getCustomTargetTypeRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for getCustomTargetType from properties.");
      }
    }
    Retry listReleasesRetry = clientProperties.getListReleasesRetry();
    if (listReleasesRetry != null) {
      RetrySettings listReleasesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listReleasesSettings().getRetrySettings(), listReleasesRetry);
      clientSettingsBuilder.listReleasesSettings().setRetrySettings(listReleasesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listReleases from properties.");
      }
    }
    Retry getReleaseRetry = clientProperties.getGetReleaseRetry();
    if (getReleaseRetry != null) {
      RetrySettings getReleaseRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getReleaseSettings().getRetrySettings(), getReleaseRetry);
      clientSettingsBuilder.getReleaseSettings().setRetrySettings(getReleaseRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getRelease from properties.");
      }
    }
    Retry abandonReleaseRetry = clientProperties.getAbandonReleaseRetry();
    if (abandonReleaseRetry != null) {
      RetrySettings abandonReleaseRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.abandonReleaseSettings().getRetrySettings(),
              abandonReleaseRetry);
      clientSettingsBuilder.abandonReleaseSettings().setRetrySettings(abandonReleaseRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for abandonRelease from properties.");
      }
    }
    Retry listDeployPoliciesRetry = clientProperties.getListDeployPoliciesRetry();
    if (listDeployPoliciesRetry != null) {
      RetrySettings listDeployPoliciesRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listDeployPoliciesSettings().getRetrySettings(),
              listDeployPoliciesRetry);
      clientSettingsBuilder
          .listDeployPoliciesSettings()
          .setRetrySettings(listDeployPoliciesRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listDeployPolicies from properties.");
      }
    }
    Retry getDeployPolicyRetry = clientProperties.getGetDeployPolicyRetry();
    if (getDeployPolicyRetry != null) {
      RetrySettings getDeployPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getDeployPolicySettings().getRetrySettings(),
              getDeployPolicyRetry);
      clientSettingsBuilder
          .getDeployPolicySettings()
          .setRetrySettings(getDeployPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getDeployPolicy from properties.");
      }
    }
    Retry approveRolloutRetry = clientProperties.getApproveRolloutRetry();
    if (approveRolloutRetry != null) {
      RetrySettings approveRolloutRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.approveRolloutSettings().getRetrySettings(),
              approveRolloutRetry);
      clientSettingsBuilder.approveRolloutSettings().setRetrySettings(approveRolloutRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for approveRollout from properties.");
      }
    }
    Retry advanceRolloutRetry = clientProperties.getAdvanceRolloutRetry();
    if (advanceRolloutRetry != null) {
      RetrySettings advanceRolloutRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.advanceRolloutSettings().getRetrySettings(),
              advanceRolloutRetry);
      clientSettingsBuilder.advanceRolloutSettings().setRetrySettings(advanceRolloutRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for advanceRollout from properties.");
      }
    }
    Retry cancelRolloutRetry = clientProperties.getCancelRolloutRetry();
    if (cancelRolloutRetry != null) {
      RetrySettings cancelRolloutRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.cancelRolloutSettings().getRetrySettings(), cancelRolloutRetry);
      clientSettingsBuilder.cancelRolloutSettings().setRetrySettings(cancelRolloutRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for cancelRollout from properties.");
      }
    }
    Retry listRolloutsRetry = clientProperties.getListRolloutsRetry();
    if (listRolloutsRetry != null) {
      RetrySettings listRolloutsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listRolloutsSettings().getRetrySettings(), listRolloutsRetry);
      clientSettingsBuilder.listRolloutsSettings().setRetrySettings(listRolloutsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listRollouts from properties.");
      }
    }
    Retry getRolloutRetry = clientProperties.getGetRolloutRetry();
    if (getRolloutRetry != null) {
      RetrySettings getRolloutRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getRolloutSettings().getRetrySettings(), getRolloutRetry);
      clientSettingsBuilder.getRolloutSettings().setRetrySettings(getRolloutRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getRollout from properties.");
      }
    }
    Retry ignoreJobRetry = clientProperties.getIgnoreJobRetry();
    if (ignoreJobRetry != null) {
      RetrySettings ignoreJobRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.ignoreJobSettings().getRetrySettings(), ignoreJobRetry);
      clientSettingsBuilder.ignoreJobSettings().setRetrySettings(ignoreJobRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for ignoreJob from properties.");
      }
    }
    Retry retryJobRetry = clientProperties.getRetryJobRetry();
    if (retryJobRetry != null) {
      RetrySettings retryJobRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.retryJobSettings().getRetrySettings(), retryJobRetry);
      clientSettingsBuilder.retryJobSettings().setRetrySettings(retryJobRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for retryJob from properties.");
      }
    }
    Retry listJobRunsRetry = clientProperties.getListJobRunsRetry();
    if (listJobRunsRetry != null) {
      RetrySettings listJobRunsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listJobRunsSettings().getRetrySettings(), listJobRunsRetry);
      clientSettingsBuilder.listJobRunsSettings().setRetrySettings(listJobRunsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listJobRuns from properties.");
      }
    }
    Retry getJobRunRetry = clientProperties.getGetJobRunRetry();
    if (getJobRunRetry != null) {
      RetrySettings getJobRunRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getJobRunSettings().getRetrySettings(), getJobRunRetry);
      clientSettingsBuilder.getJobRunSettings().setRetrySettings(getJobRunRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getJobRun from properties.");
      }
    }
    Retry terminateJobRunRetry = clientProperties.getTerminateJobRunRetry();
    if (terminateJobRunRetry != null) {
      RetrySettings terminateJobRunRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.terminateJobRunSettings().getRetrySettings(),
              terminateJobRunRetry);
      clientSettingsBuilder
          .terminateJobRunSettings()
          .setRetrySettings(terminateJobRunRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for terminateJobRun from properties.");
      }
    }
    Retry getConfigRetry = clientProperties.getGetConfigRetry();
    if (getConfigRetry != null) {
      RetrySettings getConfigRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getConfigSettings().getRetrySettings(), getConfigRetry);
      clientSettingsBuilder.getConfigSettings().setRetrySettings(getConfigRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getConfig from properties.");
      }
    }
    Retry getAutomationRetry = clientProperties.getGetAutomationRetry();
    if (getAutomationRetry != null) {
      RetrySettings getAutomationRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getAutomationSettings().getRetrySettings(), getAutomationRetry);
      clientSettingsBuilder.getAutomationSettings().setRetrySettings(getAutomationRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getAutomation from properties.");
      }
    }
    Retry listAutomationsRetry = clientProperties.getListAutomationsRetry();
    if (listAutomationsRetry != null) {
      RetrySettings listAutomationsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listAutomationsSettings().getRetrySettings(),
              listAutomationsRetry);
      clientSettingsBuilder
          .listAutomationsSettings()
          .setRetrySettings(listAutomationsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listAutomations from properties.");
      }
    }
    Retry getAutomationRunRetry = clientProperties.getGetAutomationRunRetry();
    if (getAutomationRunRetry != null) {
      RetrySettings getAutomationRunRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getAutomationRunSettings().getRetrySettings(),
              getAutomationRunRetry);
      clientSettingsBuilder
          .getAutomationRunSettings()
          .setRetrySettings(getAutomationRunRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for getAutomationRun from properties.");
      }
    }
    Retry listAutomationRunsRetry = clientProperties.getListAutomationRunsRetry();
    if (listAutomationRunsRetry != null) {
      RetrySettings listAutomationRunsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listAutomationRunsSettings().getRetrySettings(),
              listAutomationRunsRetry);
      clientSettingsBuilder
          .listAutomationRunsSettings()
          .setRetrySettings(listAutomationRunsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for listAutomationRuns from properties.");
      }
    }
    Retry cancelAutomationRunRetry = clientProperties.getCancelAutomationRunRetry();
    if (cancelAutomationRunRetry != null) {
      RetrySettings cancelAutomationRunRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.cancelAutomationRunSettings().getRetrySettings(),
              cancelAutomationRunRetry);
      clientSettingsBuilder
          .cancelAutomationRunSettings()
          .setRetrySettings(cancelAutomationRunRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for cancelAutomationRun from properties.");
      }
    }
    Retry listLocationsRetry = clientProperties.getListLocationsRetry();
    if (listLocationsRetry != null) {
      RetrySettings listLocationsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listLocationsSettings().getRetrySettings(), listLocationsRetry);
      clientSettingsBuilder.listLocationsSettings().setRetrySettings(listLocationsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listLocations from properties.");
      }
    }
    Retry getLocationRetry = clientProperties.getGetLocationRetry();
    if (getLocationRetry != null) {
      RetrySettings getLocationRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getLocationSettings().getRetrySettings(), getLocationRetry);
      clientSettingsBuilder.getLocationSettings().setRetrySettings(getLocationRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getLocation from properties.");
      }
    }
    Retry setIamPolicyRetry = clientProperties.getSetIamPolicyRetry();
    if (setIamPolicyRetry != null) {
      RetrySettings setIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.setIamPolicySettings().getRetrySettings(), setIamPolicyRetry);
      clientSettingsBuilder.setIamPolicySettings().setRetrySettings(setIamPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for setIamPolicy from properties.");
      }
    }
    Retry getIamPolicyRetry = clientProperties.getGetIamPolicyRetry();
    if (getIamPolicyRetry != null) {
      RetrySettings getIamPolicyRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getIamPolicySettings().getRetrySettings(), getIamPolicyRetry);
      clientSettingsBuilder.getIamPolicySettings().setRetrySettings(getIamPolicyRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getIamPolicy from properties.");
      }
    }
    Retry testIamPermissionsRetry = clientProperties.getTestIamPermissionsRetry();
    if (testIamPermissionsRetry != null) {
      RetrySettings testIamPermissionsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.testIamPermissionsSettings().getRetrySettings(),
              testIamPermissionsRetry);
      clientSettingsBuilder
          .testIamPermissionsSettings()
          .setRetrySettings(testIamPermissionsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace(
            "Configured method-level retry settings for testIamPermissions from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a CloudDeployClient bean configured with CloudDeploySettings.
   *
   * @param cloudDeploySettings settings to configure an instance of client bean.
   * @return a {@link CloudDeployClient} bean configured with {@link CloudDeploySettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public CloudDeployClient cloudDeployClient(CloudDeploySettings cloudDeploySettings)
      throws IOException {
    return CloudDeployClient.create(cloudDeploySettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-cloud-deploy";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}
