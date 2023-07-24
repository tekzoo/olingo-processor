package com.tekzoo.odata.processor.core.filter;

import org.apache.olingo.server.api.uri.UriInfoResource;
import org.apache.olingo.server.api.uri.queryoption.expression.VisitableExpression;

public interface JPAVisitableExpression extends VisitableExpression {

  public UriInfoResource getMember();
}
