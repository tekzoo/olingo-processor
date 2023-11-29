package com.tekzoo.odata.processor.core.api;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import jakarta.persistence.EntityManager;

import org.apache.olingo.server.api.debug.DebugSupport;

import com.tekzoo.odata.metadata.api.JPARequestParameterMap;
import com.tekzoo.odata.processor.core.api.JPAODataExternalRequestContext.Builder;

public interface JPAODataRequestContext {
  public static Builder with() {
    return new Builder();
  }

  public EntityManager getEntityManager();

  public Optional<JPAODataClaimProvider> getClaimsProvider();

  public Optional<JPAODataGroupProvider> getGroupsProvider();

  public JPACUDRequestHandler getCUDRequestHandler();

  public DebugSupport getDebuggerSupport();

  public JPAODataTransactionFactory getTransactionFactory();

  public JPARequestParameterMap getRequestParameter();

  public List<Locale> getLocales();
}
