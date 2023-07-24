package com.tekzoo.odata.processor.core.processor;

import org.apache.olingo.commons.api.ex.ODataException;
import org.apache.olingo.server.api.OData;

import com.tekzoo.odata.processor.core.api.JPAODataRequestContextAccess;

abstract class JPAAbstractGetRequestProcessor extends JPAAbstractRequestProcessor implements JPARequestProcessor {

  JPAAbstractGetRequestProcessor(final OData odata, final JPAODataRequestContextAccess requestContext)
      throws ODataException {
    super(odata, requestContext);
  }
}
