package com.tekzoo.odata.processor.core.api;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import jakarta.persistence.EntityManager;

import org.apache.olingo.server.api.uri.UriInfoResource;

import com.tekzoo.odata.metadata.api.JPAEdmProvider;
import com.tekzoo.odata.metadata.api.JPAHttpHeaderMap;
import com.tekzoo.odata.metadata.api.JPARequestParameterMap;
import com.tekzoo.odata.metadata.core.edm.annotation.EdmQueryExtensionProvider;
import com.tekzoo.odata.metadata.core.edm.annotation.EdmTransientPropertyCalculator;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAAttribute;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAEntityType;
import com.tekzoo.odata.processor.core.database.JPAODataDatabaseOperations;
import com.tekzoo.odata.processor.core.exception.ODataJPAProcessorException;
import com.tekzoo.odata.processor.core.serializer.JPASerializer;

public interface JPAODataRequestContextAccess {

  public @Nonnull EntityManager getEntityManager();

  public UriInfoResource getUriInfo();

  public JPASerializer getSerializer();

  public JPAODataPage getPage();

  public Optional<JPAODataClaimProvider> getClaimsProvider();

  public Optional<JPAODataGroupProvider> getGroupsProvider();

  public JPACUDRequestHandler getCUDRequestHandler();

  public JPAServiceDebugger getDebugger();

  public JPAODataTransactionFactory getTransactionFactory();

  public Optional<EdmTransientPropertyCalculator<?>> getCalculator(@Nonnull final JPAAttribute transientProperty)
      throws ODataJPAProcessorException;

  public Optional<EdmQueryExtensionProvider> getQueryEnhancement(@Nonnull final JPAEntityType et)
      throws ODataJPAProcessorException;

  public @Nonnull JPAHttpHeaderMap getHeader();

  public @Nonnull JPARequestParameterMap getRequestParameter();

  public JPAODataDatabaseProcessor getDatabaseProcessor();

  public JPAEdmProvider getEdmProvider() throws ODataJPAProcessorException;

  public JPAODataDatabaseOperations getOperationConverter();

  /**
   *
   * @return most significant locale. Used e.g. for description properties
   */
  public @CheckForNull Locale getLocale();

  /**
   *
   * @return list of locale provided for this request
   */
  public List<Locale> getProvidedLocale();
}
