package com.tekzoo.odata.metadata.core.edm.mapper.api;

import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;

public interface JPAAssociationAttribute extends JPAAttribute {

  public JPAStructuredType getTargetEntity() throws ODataJPAModelException;

  public JPAAssociationAttribute getPartner();

  public JPAAssociationPath getPath() throws ODataJPAModelException;

}
