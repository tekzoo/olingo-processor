package com.tekzoo.odata.metadata.core.edm.mapper.impl;

import jakarta.persistence.metamodel.Metamodel;

import com.tekzoo.odata.metadata.api.JPAEdmMetadataPostProcessor;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAEdmNameBuilder;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAServiceDocument;
import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;

public final class JPAServiceDocumentFactory {

  private final JPAEdmNameBuilder nameBuilder;
  private final Metamodel jpaMetamodel;
  private final JPAEdmMetadataPostProcessor postProcessor;
  private final String[] packageName;

  public JPAServiceDocumentFactory(final JPAEdmNameBuilder nameBuilder, final Metamodel jpaMetamodel,
      final JPAEdmMetadataPostProcessor postProcessor, final String[] packageName) {
    super();
    this.nameBuilder = nameBuilder;
    this.jpaMetamodel = jpaMetamodel;
    this.postProcessor = postProcessor;
    this.packageName = packageName;
  }

  /**
   * Late creation of the service document. A service document contains at least one schema and a container.
   * @return
   * @throws ODataJPAModelException
   */
  public JPAServiceDocument getServiceDocument() throws ODataJPAModelException {
    return new IntermediateServiceDocument(nameBuilder, jpaMetamodel, postProcessor, packageName);
  }
}
