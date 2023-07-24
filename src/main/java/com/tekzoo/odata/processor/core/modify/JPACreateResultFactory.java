package com.tekzoo.odata.processor.core.modify;

import java.util.List;
import java.util.Map;

import org.apache.olingo.server.api.ODataApplicationException;

import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAEntityType;
import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;
import com.tekzoo.odata.processor.core.converter.JPAExpandResult;
import com.tekzoo.odata.processor.core.converter.JPATupleChildConverter;

public final class JPACreateResultFactory {
  private final JPATupleChildConverter converter;

  public JPACreateResultFactory(JPATupleChildConverter converter) {
    this.converter = converter;
  }

  @SuppressWarnings("unchecked")
  public JPAExpandResult getJPACreateResult(JPAEntityType et, Object result, Map<String, List<String>> requestHeaders)
      throws ODataJPAModelException, ODataApplicationException {

    if (result instanceof Map<?, ?>)
      return new JPAMapResult(et, (Map<String, Object>) result, requestHeaders, converter);
    else
      return new JPAEntityResult(et, result, requestHeaders, converter);
  }
}
