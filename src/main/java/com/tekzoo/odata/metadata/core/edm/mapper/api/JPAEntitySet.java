package com.tekzoo.odata.metadata.core.edm.mapper.api;

import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;

public interface JPAEntitySet extends JPATopLevelEntity {

  JPAEntityType getODataEntityType() throws ODataJPAModelException;

  JPAEntityType getEntityType();

}
