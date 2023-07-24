package com.tekzoo.odata.metadata.core.edm.mapper.extension;

import java.util.List;

import org.apache.olingo.commons.api.edm.provider.CsdlAnnotation;

public interface IntermediateEntityContainerAccess {
  /**
   * Enables to add annotations to the entity container
   */
  public void addAnnotations(final List<CsdlAnnotation> annotations);
}
