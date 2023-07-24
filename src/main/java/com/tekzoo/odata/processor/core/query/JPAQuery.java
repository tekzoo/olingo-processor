package com.tekzoo.odata.processor.core.query;

import org.apache.olingo.server.api.ODataApplicationException;

public interface JPAQuery {
  JPAConvertibleResult execute() throws ODataApplicationException;
}
