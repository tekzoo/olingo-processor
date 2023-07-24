package com.tekzoo.odata.processor.core.query;

class JPAQueryPair {
  private final JPAAbstractQuery inner;
  private final JPAAbstractQuery outer;

  JPAQueryPair(final JPAAbstractQuery inner, final JPAAbstractQuery outer) {
    this.inner = inner;
    this.outer = outer;
  }

  public JPAAbstractQuery getOuter() {
    return outer;
  }

  public JPAAbstractQuery getInner() {
    return inner;
  }

  @Override
  public String toString() {
    return "JPAQueryPair [outer=" + outer + ", inner=" + inner + "]";
  }

}
