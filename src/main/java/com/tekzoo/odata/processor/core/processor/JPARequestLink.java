package com.tekzoo.odata.processor.core.processor;

import java.util.Map;

import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAEntityType;
import com.tekzoo.odata.processor.core.exception.ODataJPAProcessorException;

public interface JPARequestLink {
  /**
   * Provides an instance of the target entity metadata
   * @return
   */
  public JPAEntityType getEntityType();

  /**
   * Map of related keys
   * @return
   * @throws ODataJPAProcessorException
   */
  public Map<String, Object> getRelatedKeys() throws ODataJPAProcessorException;

  public Map<String, Object> getValues() throws ODataJPAProcessorException;
}
