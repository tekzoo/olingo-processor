package com.tekzoo.odata.processor.core.database;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;

import org.apache.olingo.server.api.ODataApplicationException;

import com.tekzoo.odata.processor.core.filter.JPAAggregationOperation;
import com.tekzoo.odata.processor.core.filter.JPAArithmeticOperator;
import com.tekzoo.odata.processor.core.filter.JPABooleanOperator;
import com.tekzoo.odata.processor.core.filter.JPAComparisonOperator;
import com.tekzoo.odata.processor.core.filter.JPAMethodCall;
import com.tekzoo.odata.processor.core.filter.JPAUnaryBooleanOperator;

public interface JPAODataDatabaseOperations {

  public void setCriterialBuilder(final CriteriaBuilder cb);

  public <T extends Number> Expression<T> convert(final JPAArithmeticOperator jpaOperator)
      throws ODataApplicationException;

  public Expression<Boolean> convert(final JPABooleanOperator jpaOperator) throws ODataApplicationException;

  public <T extends Comparable<T>> Expression<Boolean> convert(final JPAComparisonOperator<T> jpaOperator)
      throws ODataApplicationException;

  public <T> Expression<T> convert(final JPAMethodCall jpaFunction) throws ODataApplicationException;

  public Expression<Boolean> convert(final JPAUnaryBooleanOperator jpaOperator) throws ODataApplicationException;

  public Expression<Long> convert(final JPAAggregationOperation jpaOperator) throws ODataApplicationException;
}
