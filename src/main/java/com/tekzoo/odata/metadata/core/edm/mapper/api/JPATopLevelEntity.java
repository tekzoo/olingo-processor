package com.tekzoo.odata.metadata.core.edm.mapper.api;

import java.util.Optional;

import com.tekzoo.odata.metadata.core.edm.annotation.EdmQueryExtensionProvider;
import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;

public interface JPATopLevelEntity extends JPAElement {
  public Optional<JPAQueryExtension<EdmQueryExtensionProvider>> getQueryExtension() throws ODataJPAModelException;
}
