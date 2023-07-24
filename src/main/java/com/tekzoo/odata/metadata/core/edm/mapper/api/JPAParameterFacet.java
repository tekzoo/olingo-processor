package com.tekzoo.odata.metadata.core.edm.mapper.api;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.geo.SRID;

import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;

public interface JPAParameterFacet {

  Integer getMaxLength();

  Integer getPrecision();

  Integer getScale();

  SRID getSrid();

  Class<?> getType();

  FullQualifiedName getTypeFQN() throws ODataJPAModelException;
}