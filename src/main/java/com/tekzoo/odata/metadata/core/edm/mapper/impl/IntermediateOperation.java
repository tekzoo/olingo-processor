package com.tekzoo.odata.metadata.core.edm.mapper.impl;

import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAEdmNameBuilder;
import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;

abstract class IntermediateOperation extends IntermediateModelElement {

  IntermediateOperation(final JPAEdmNameBuilder nameBuilder, final String internalName) {
    super(nameBuilder, internalName);
  }

  abstract boolean hasImport();

  abstract boolean isBound() throws ODataJPAModelException;

  protected Integer nullIfNotSet(final Integer number) {
    if (number != null && number > -1)
      return number;
    return null;
  }

}