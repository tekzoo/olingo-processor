package com.tekzoo.odata.processor.core.filter;

public interface JPAExpressionOperator extends JPAExpression {

  public <E extends Enum<E>> Enum<E> getOperator();
}
