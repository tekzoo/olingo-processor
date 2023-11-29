package com.tekzoo.odata.processor.core.filter;

import jakarta.persistence.criteria.From;

import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.uri.queryoption.expression.ExpressionVisitor;

public interface JPAExpressionVisitor extends ExpressionVisitor<JPAOperator> {

  public OData getOdata();

  public From<?, ?> getRoot();

}
