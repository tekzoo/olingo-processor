package com.tekzoo.odata.processor.core.filter;

import javax.persistence.criteria.Expression;

import org.apache.olingo.server.api.ODataApplicationException;

public interface JPAExpression extends JPAOperator {

  @Override
  Expression<Boolean> get() throws ODataApplicationException;
}