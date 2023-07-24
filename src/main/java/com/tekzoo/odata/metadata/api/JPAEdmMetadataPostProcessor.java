package com.tekzoo.odata.metadata.api;

import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;
import com.tekzoo.odata.metadata.core.edm.mapper.extension.IntermediateEntityContainerAccess;
import com.tekzoo.odata.metadata.core.edm.mapper.extension.IntermediateEntitySetAccess;
import com.tekzoo.odata.metadata.core.edm.mapper.extension.IntermediateEntityTypeAccess;
import com.tekzoo.odata.metadata.core.edm.mapper.extension.IntermediateNavigationPropertyAccess;
import com.tekzoo.odata.metadata.core.edm.mapper.extension.IntermediatePropertyAccess;
import com.tekzoo.odata.metadata.core.edm.mapper.extension.IntermediateReferenceList;
import com.tekzoo.odata.metadata.core.edm.mapper.extension.IntermediateSingletonAccess;

public abstract class JPAEdmMetadataPostProcessor {

  public void processEntityContainer(final IntermediateEntityContainerAccess container) {}

  public abstract void processEntityType(final IntermediateEntityTypeAccess entityType);

  public void processEntitySet(final IntermediateEntitySetAccess entitySet) {}

  public void processSingleton(final IntermediateSingletonAccess singleton) {}

  public abstract void processNavigationProperty(final IntermediateNavigationPropertyAccess property,
      final String jpaManagedTypeClassName);

  /**
   *
   * @param property
   * @param jpaManagedTypeClassName
   * @return
   */
  public abstract void processProperty(final IntermediatePropertyAccess property, final String jpaManagedTypeClassName);

  public abstract void provideReferences(final IntermediateReferenceList references) throws ODataJPAModelException;
}
