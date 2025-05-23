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

package com.google.cloud.documentai.v1.spring;

import com.google.api.core.BetaApi;
import com.google.cloud.spring.core.Credentials;
import com.google.cloud.spring.core.CredentialsSupplier;
import com.google.cloud.spring.core.Retry;
import javax.annotation.Generated;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

// AUTO-GENERATED DOCUMENTATION AND CLASS.
/** Provides default property values for DocumentProcessorService client bean */
@Generated("by google-cloud-spring-generator")
@BetaApi("Autogenerated Spring autoconfiguration is not yet stable")
@ConfigurationProperties("com.google.cloud.documentai.v1.document-processor-service")
public class DocumentProcessorServiceSpringProperties implements CredentialsSupplier {
  /** OAuth2 credentials to authenticate and authorize calls to Google Cloud Client Libraries. */
  @NestedConfigurationProperty
  private final Credentials credentials =
      new Credentials("https://www.googleapis.com/auth/cloud-platform");
  /** Quota project to use for billing. */
  private String quotaProjectId;
  /** Number of threads used for executors. */
  private Integer executorThreadCount;
  /** Allow override of default transport channel provider to use REST instead of gRPC. */
  private boolean useRest = false;
  /** Allow override of retry settings at service level, applying to all of its RPC methods. */
  @NestedConfigurationProperty private Retry retry;
  /**
   * Allow override of retry settings at method-level for processDocument. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry processDocumentRetry;
  /**
   * Allow override of retry settings at method-level for fetchProcessorTypes. If defined, this
   * takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry fetchProcessorTypesRetry;
  /**
   * Allow override of retry settings at method-level for listProcessorTypes. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listProcessorTypesRetry;
  /**
   * Allow override of retry settings at method-level for getProcessorType. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getProcessorTypeRetry;
  /**
   * Allow override of retry settings at method-level for listProcessors. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listProcessorsRetry;
  /**
   * Allow override of retry settings at method-level for getProcessor. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getProcessorRetry;
  /**
   * Allow override of retry settings at method-level for getProcessorVersion. If defined, this
   * takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getProcessorVersionRetry;
  /**
   * Allow override of retry settings at method-level for listProcessorVersions. If defined, this
   * takes precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listProcessorVersionsRetry;
  /**
   * Allow override of retry settings at method-level for createProcessor. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry createProcessorRetry;
  /**
   * Allow override of retry settings at method-level for getEvaluation. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getEvaluationRetry;
  /**
   * Allow override of retry settings at method-level for listEvaluations. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listEvaluationsRetry;
  /**
   * Allow override of retry settings at method-level for listLocations. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry listLocationsRetry;
  /**
   * Allow override of retry settings at method-level for getLocation. If defined, this takes
   * precedence over service-level retry configurations for that RPC method.
   */
  @NestedConfigurationProperty private Retry getLocationRetry;

  @Override
  public Credentials getCredentials() {
    return this.credentials;
  }

  public String getQuotaProjectId() {
    return this.quotaProjectId;
  }

  public void setQuotaProjectId(String quotaProjectId) {
    this.quotaProjectId = quotaProjectId;
  }

  public boolean getUseRest() {
    return this.useRest;
  }

  public void setUseRest(boolean useRest) {
    this.useRest = useRest;
  }

  public Integer getExecutorThreadCount() {
    return this.executorThreadCount;
  }

  public void setExecutorThreadCount(Integer executorThreadCount) {
    this.executorThreadCount = executorThreadCount;
  }

  public Retry getRetry() {
    return this.retry;
  }

  public void setRetry(Retry retry) {
    this.retry = retry;
  }

  public Retry getProcessDocumentRetry() {
    return this.processDocumentRetry;
  }

  public void setProcessDocumentRetry(Retry processDocumentRetry) {
    this.processDocumentRetry = processDocumentRetry;
  }

  public Retry getFetchProcessorTypesRetry() {
    return this.fetchProcessorTypesRetry;
  }

  public void setFetchProcessorTypesRetry(Retry fetchProcessorTypesRetry) {
    this.fetchProcessorTypesRetry = fetchProcessorTypesRetry;
  }

  public Retry getListProcessorTypesRetry() {
    return this.listProcessorTypesRetry;
  }

  public void setListProcessorTypesRetry(Retry listProcessorTypesRetry) {
    this.listProcessorTypesRetry = listProcessorTypesRetry;
  }

  public Retry getGetProcessorTypeRetry() {
    return this.getProcessorTypeRetry;
  }

  public void setGetProcessorTypeRetry(Retry getProcessorTypeRetry) {
    this.getProcessorTypeRetry = getProcessorTypeRetry;
  }

  public Retry getListProcessorsRetry() {
    return this.listProcessorsRetry;
  }

  public void setListProcessorsRetry(Retry listProcessorsRetry) {
    this.listProcessorsRetry = listProcessorsRetry;
  }

  public Retry getGetProcessorRetry() {
    return this.getProcessorRetry;
  }

  public void setGetProcessorRetry(Retry getProcessorRetry) {
    this.getProcessorRetry = getProcessorRetry;
  }

  public Retry getGetProcessorVersionRetry() {
    return this.getProcessorVersionRetry;
  }

  public void setGetProcessorVersionRetry(Retry getProcessorVersionRetry) {
    this.getProcessorVersionRetry = getProcessorVersionRetry;
  }

  public Retry getListProcessorVersionsRetry() {
    return this.listProcessorVersionsRetry;
  }

  public void setListProcessorVersionsRetry(Retry listProcessorVersionsRetry) {
    this.listProcessorVersionsRetry = listProcessorVersionsRetry;
  }

  public Retry getCreateProcessorRetry() {
    return this.createProcessorRetry;
  }

  public void setCreateProcessorRetry(Retry createProcessorRetry) {
    this.createProcessorRetry = createProcessorRetry;
  }

  public Retry getGetEvaluationRetry() {
    return this.getEvaluationRetry;
  }

  public void setGetEvaluationRetry(Retry getEvaluationRetry) {
    this.getEvaluationRetry = getEvaluationRetry;
  }

  public Retry getListEvaluationsRetry() {
    return this.listEvaluationsRetry;
  }

  public void setListEvaluationsRetry(Retry listEvaluationsRetry) {
    this.listEvaluationsRetry = listEvaluationsRetry;
  }

  public Retry getListLocationsRetry() {
    return this.listLocationsRetry;
  }

  public void setListLocationsRetry(Retry listLocationsRetry) {
    this.listLocationsRetry = listLocationsRetry;
  }

  public Retry getGetLocationRetry() {
    return this.getLocationRetry;
  }

  public void setGetLocationRetry(Retry getLocationRetry) {
    this.getLocationRetry = getLocationRetry;
  }
}
