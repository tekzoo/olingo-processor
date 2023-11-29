package com.tekzoo.odata.processor.core.query;

import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;

import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAPath;

class JPAQueryCreationResult {
  private final TypedQuery<Tuple> query;
  private final SelectionPathInfo<JPAPath> selection;

  JPAQueryCreationResult(final TypedQuery<Tuple> query, final SelectionPathInfo<JPAPath> selection) {
    this.query = query;
    this.selection = selection;
  }

  TypedQuery<Tuple> getQuery() {
    return query;
  }

  SelectionPathInfo<JPAPath> getSelection() {
    return selection;
  }

  @Override
  public String toString() {
    return "JPAQueryCreationResult [query=" + query + ", selection=" + selection + "]";
  }

}
