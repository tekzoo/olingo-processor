package com.tekzoo.odata.processor.core.converter;

import java.util.Collection;

import org.apache.olingo.server.api.ODataApplicationException;

import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAPath;

public interface JPAResultConverter {

  public Object getResult(final JPAExpandResult jpaResult, final Collection<JPAPath> requestedSelection) 
      throws ODataApplicationException;

}
