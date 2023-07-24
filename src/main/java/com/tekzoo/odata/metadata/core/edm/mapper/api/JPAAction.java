package com.tekzoo.odata.metadata.core.edm.mapper.api;

import java.lang.reflect.Parameter;

import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;

public interface JPAAction extends JPAOperation, JPAJavaOperation {

  JPAParameter getParameter(final Parameter declaredParameter) throws ODataJPAModelException;
}
