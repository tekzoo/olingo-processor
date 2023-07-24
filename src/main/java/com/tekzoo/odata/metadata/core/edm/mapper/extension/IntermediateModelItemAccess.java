package com.tekzoo.odata.metadata.core.edm.mapper.extension;

import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAElement;

/**
 * @author Oliver Grande
 *
 */
public interface IntermediateModelItemAccess extends JPAElement {
  /**
   * Element shall be ignored for metadata generation.
   * @return
   */
  boolean ignore();

  /**
   * Enables to switch if a model item shall be ignored during runtime.
   * @param ignore
   */
  void setIgnore(boolean ignore);

}