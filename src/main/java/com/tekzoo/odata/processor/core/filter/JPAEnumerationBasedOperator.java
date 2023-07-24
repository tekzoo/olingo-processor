package com.tekzoo.odata.processor.core.filter;

import com.tekzoo.odata.processor.core.exception.ODataJPAFilterException;

/**
 * Main purpose of this interface is to increase testability of JPAEnumerationBasedOperator
 * @author Oliver Grande
 *
 */
public interface JPAEnumerationBasedOperator extends JPAPrimitiveTypeOperator {

  Number getValue() throws ODataJPAFilterException;

}