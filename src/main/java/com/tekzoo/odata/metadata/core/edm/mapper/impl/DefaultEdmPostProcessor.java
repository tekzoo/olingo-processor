package com.tekzoo.odata.metadata.core.edm.mapper.impl;

import com.tekzoo.odata.metadata.api.JPAEdmMetadataPostProcessor;
import com.tekzoo.odata.metadata.core.edm.mapper.extension.IntermediateEntityTypeAccess;
import com.tekzoo.odata.metadata.core.edm.mapper.extension.IntermediateNavigationPropertyAccess;
import com.tekzoo.odata.metadata.core.edm.mapper.extension.IntermediatePropertyAccess;
import com.tekzoo.odata.metadata.core.edm.mapper.extension.IntermediateReferenceList;

final class DefaultEdmPostProcessor extends JPAEdmMetadataPostProcessor {

  @Override
  public void processNavigationProperty(final IntermediateNavigationPropertyAccess property,
      final String jpaManagedTypeClassName) {
    // Default shall do nothing
  }

  @Override
  public void processProperty(final IntermediatePropertyAccess property, final String jpaManagedTypeClassName) {
    // Default shall do nothing
  }

  @Override
  public void provideReferences(IntermediateReferenceList references) {
    // Default shall do nothing
  }

  @Override
  public void processEntityType(IntermediateEntityTypeAccess entity) {
    // Default shall do nothing
  }
}
