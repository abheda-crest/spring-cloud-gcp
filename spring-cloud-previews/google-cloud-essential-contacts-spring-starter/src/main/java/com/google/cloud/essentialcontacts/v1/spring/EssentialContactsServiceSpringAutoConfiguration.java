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

package com.google.cloud.essentialcontacts.v1.spring;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.ExecutorProvider;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.rpc.HeaderProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.essentialcontacts.v1.EssentialContactsServiceClient;
import com.google.cloud.essentialcontacts.v1.EssentialContactsServiceSettings;
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
 * Auto-configuration for {@link EssentialContactsServiceClient}.
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
@ConditionalOnClass(EssentialContactsServiceClient.class)
@ConditionalOnProperty(
    value = "com.google.cloud.essentialcontacts.v1.essential-contacts-service.enabled",
    matchIfMissing = true)
@EnableConfigurationProperties(EssentialContactsServiceSpringProperties.class)
public class EssentialContactsServiceSpringAutoConfiguration {
  private final EssentialContactsServiceSpringProperties clientProperties;
  private final CredentialsProvider credentialsProvider;
  private static final Log LOGGER =
      LogFactory.getLog(EssentialContactsServiceSpringAutoConfiguration.class);

  protected EssentialContactsServiceSpringAutoConfiguration(
      EssentialContactsServiceSpringProperties clientProperties,
      CredentialsProvider credentialsProvider)
      throws IOException {
    this.clientProperties = clientProperties;
    if (this.clientProperties.getCredentials().hasKey()) {
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using credentials from EssentialContactsService-specific configuration");
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
  @ConditionalOnMissingBean(name = "defaultEssentialContactsServiceTransportChannelProvider")
  public TransportChannelProvider defaultEssentialContactsServiceTransportChannelProvider() {
    if (this.clientProperties.getUseRest()) {
      return EssentialContactsServiceSettings.defaultHttpJsonTransportProviderBuilder().build();
    }
    return EssentialContactsServiceSettings.defaultTransportChannelProvider();
  }

  /**
   * Provides a EssentialContactsServiceSettings bean configured to use a DefaultCredentialsProvider
   * and the client library's default transport channel provider
   * (defaultEssentialContactsServiceTransportChannelProvider()). It also configures the quota
   * project ID and executor thread count, if provided through properties.
   *
   * <p>Retry settings are also configured from service-level and method-level properties specified
   * in EssentialContactsServiceSpringProperties. Method-level properties will take precedence over
   * service-level properties if available, and client library defaults will be used if neither are
   * specified.
   *
   * @param defaultTransportChannelProvider TransportChannelProvider to use in the settings.
   * @return a {@link EssentialContactsServiceSettings} bean configured with {@link
   *     TransportChannelProvider} bean.
   */
  @Bean
  @ConditionalOnMissingBean
  public EssentialContactsServiceSettings essentialContactsServiceSettings(
      @Qualifier("defaultEssentialContactsServiceTransportChannelProvider")
          TransportChannelProvider defaultTransportChannelProvider)
      throws IOException {
    EssentialContactsServiceSettings.Builder clientSettingsBuilder;
    if (this.clientProperties.getUseRest()) {
      clientSettingsBuilder = EssentialContactsServiceSettings.newHttpJsonBuilder();
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Using REST (HTTP/JSON) transport.");
      }
    } else {
      clientSettingsBuilder = EssentialContactsServiceSettings.newBuilder();
    }
    clientSettingsBuilder
        .setCredentialsProvider(this.credentialsProvider)
        .setTransportChannelProvider(defaultTransportChannelProvider)
        .setEndpoint(EssentialContactsServiceSettings.getDefaultEndpoint())
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
          EssentialContactsServiceSettings.defaultExecutorProviderBuilder()
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
      RetrySettings createContactRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createContactSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.createContactSettings().setRetrySettings(createContactRetrySettings);

      RetrySettings updateContactRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateContactSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.updateContactSettings().setRetrySettings(updateContactRetrySettings);

      RetrySettings listContactsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listContactsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.listContactsSettings().setRetrySettings(listContactsRetrySettings);

      RetrySettings getContactRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getContactSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.getContactSettings().setRetrySettings(getContactRetrySettings);

      RetrySettings deleteContactRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteContactSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder.deleteContactSettings().setRetrySettings(deleteContactRetrySettings);

      RetrySettings computeContactsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.computeContactsSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .computeContactsSettings()
          .setRetrySettings(computeContactsRetrySettings);

      RetrySettings sendTestMessageRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.sendTestMessageSettings().getRetrySettings(), serviceRetry);
      clientSettingsBuilder
          .sendTestMessageSettings()
          .setRetrySettings(sendTestMessageRetrySettings);

      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured service-level retry settings from properties.");
      }
    }
    Retry createContactRetry = clientProperties.getCreateContactRetry();
    if (createContactRetry != null) {
      RetrySettings createContactRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.createContactSettings().getRetrySettings(), createContactRetry);
      clientSettingsBuilder.createContactSettings().setRetrySettings(createContactRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for createContact from properties.");
      }
    }
    Retry updateContactRetry = clientProperties.getUpdateContactRetry();
    if (updateContactRetry != null) {
      RetrySettings updateContactRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.updateContactSettings().getRetrySettings(), updateContactRetry);
      clientSettingsBuilder.updateContactSettings().setRetrySettings(updateContactRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for updateContact from properties.");
      }
    }
    Retry listContactsRetry = clientProperties.getListContactsRetry();
    if (listContactsRetry != null) {
      RetrySettings listContactsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.listContactsSettings().getRetrySettings(), listContactsRetry);
      clientSettingsBuilder.listContactsSettings().setRetrySettings(listContactsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for listContacts from properties.");
      }
    }
    Retry getContactRetry = clientProperties.getGetContactRetry();
    if (getContactRetry != null) {
      RetrySettings getContactRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.getContactSettings().getRetrySettings(), getContactRetry);
      clientSettingsBuilder.getContactSettings().setRetrySettings(getContactRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for getContact from properties.");
      }
    }
    Retry deleteContactRetry = clientProperties.getDeleteContactRetry();
    if (deleteContactRetry != null) {
      RetrySettings deleteContactRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.deleteContactSettings().getRetrySettings(), deleteContactRetry);
      clientSettingsBuilder.deleteContactSettings().setRetrySettings(deleteContactRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for deleteContact from properties.");
      }
    }
    Retry computeContactsRetry = clientProperties.getComputeContactsRetry();
    if (computeContactsRetry != null) {
      RetrySettings computeContactsRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.computeContactsSettings().getRetrySettings(),
              computeContactsRetry);
      clientSettingsBuilder
          .computeContactsSettings()
          .setRetrySettings(computeContactsRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for computeContacts from properties.");
      }
    }
    Retry sendTestMessageRetry = clientProperties.getSendTestMessageRetry();
    if (sendTestMessageRetry != null) {
      RetrySettings sendTestMessageRetrySettings =
          RetryUtil.updateRetrySettings(
              clientSettingsBuilder.sendTestMessageSettings().getRetrySettings(),
              sendTestMessageRetry);
      clientSettingsBuilder
          .sendTestMessageSettings()
          .setRetrySettings(sendTestMessageRetrySettings);
      if (LOGGER.isTraceEnabled()) {
        LOGGER.trace("Configured method-level retry settings for sendTestMessage from properties.");
      }
    }
    return clientSettingsBuilder.build();
  }

  /**
   * Provides a EssentialContactsServiceClient bean configured with
   * EssentialContactsServiceSettings.
   *
   * @param essentialContactsServiceSettings settings to configure an instance of client bean.
   * @return a {@link EssentialContactsServiceClient} bean configured with {@link
   *     EssentialContactsServiceSettings}
   */
  @Bean
  @ConditionalOnMissingBean
  public EssentialContactsServiceClient essentialContactsServiceClient(
      EssentialContactsServiceSettings essentialContactsServiceSettings) throws IOException {
    return EssentialContactsServiceClient.create(essentialContactsServiceSettings);
  }

  private HeaderProvider userAgentHeaderProvider() {
    String springLibrary = "spring-autogen-essential-contacts-service";
    String version = this.getClass().getPackage().getImplementationVersion();
    return () -> Collections.singletonMap("user-agent", springLibrary + "/" + version);
  }
}
