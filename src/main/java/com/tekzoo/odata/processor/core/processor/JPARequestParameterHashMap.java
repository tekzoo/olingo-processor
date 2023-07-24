package com.tekzoo.odata.processor.core.processor;

import java.util.HashMap;

import com.tekzoo.odata.metadata.api.JPARequestParameterMap;

public class JPARequestParameterHashMap extends HashMap<String, Object> implements JPARequestParameterMap {

  private static final long serialVersionUID = 1869470620805799005L;

  public JPARequestParameterHashMap(final JPARequestParameterMap requestParameter) {
    super(requestParameter);
  }

  public JPARequestParameterHashMap() {
    super();
  }

}
