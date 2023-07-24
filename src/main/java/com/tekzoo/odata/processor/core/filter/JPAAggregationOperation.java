package com.tekzoo.odata.processor.core.filter;

import org.apache.olingo.server.api.ODataApplicationException;

public interface JPAAggregationOperation extends JPAOperator {
  @Override
  public Object get() throws ODataApplicationException;

  JPAFilterAggregationType getAggregation();

}