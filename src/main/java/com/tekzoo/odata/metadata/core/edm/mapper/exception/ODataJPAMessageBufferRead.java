package com.tekzoo.odata.metadata.core.edm.mapper.exception;

public interface ODataJPAMessageBufferRead {

  String getText(Object execution, String ID);

  String getText(Object execution, String ID, String... parameters);

}