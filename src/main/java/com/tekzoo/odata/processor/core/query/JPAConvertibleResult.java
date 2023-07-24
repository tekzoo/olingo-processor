package com.tekzoo.odata.processor.core.query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.server.api.ODataApplicationException;

import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAAssociationPath;
import com.tekzoo.odata.processor.core.api.JPAODataPage;
import com.tekzoo.odata.processor.core.api.JPAODataRequestContextAccess;
import com.tekzoo.odata.processor.core.converter.JPAExpandResult;
import com.tekzoo.odata.processor.core.converter.JPATupleChildConverter;
import com.tekzoo.odata.processor.core.exception.ODataJPAProcessException;
import com.tekzoo.odata.processor.core.exception.ODataJPAQueryException;

public interface JPAConvertibleResult {
  /**
   * 
   * @param converter
   * @return
   * @throws ODataApplicationException
   */
  Map<String, EntityCollection> asEntityCollection(final JPATupleChildConverter converter)
      throws ODataApplicationException;

  void putChildren(final Map<JPAAssociationPath, JPAExpandResult> childResults) throws ODataApplicationException;

  /**
   * Returns the entity collection of a given key. This method may internally perform <code>asEntityCollection</code>
   * @param key
   * @return
   * @throws ODataApplicationException
   */
  EntityCollection getEntityCollection(final String key) throws ODataApplicationException;

  /**
   * Returns a key pair if the query had $top and/or $skip and the key of the entity implements {@link Comparable}.
   * @param <T>
   * @param requestContext
   * @param hops
   * @return
   * @throws ODataJPAQueryException
   */
  default Optional<JPAKeyBoundary> getKeyBoundary(final JPAODataRequestContextAccess requestContext,
      final List<JPANavigationPropertyInfo> hops, final JPAODataPage page) throws ODataJPAProcessException {
    return Optional.empty();
  }
}
