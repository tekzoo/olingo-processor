package com.tekzoo.odata.metadata.core.edm.mapper.api;

public interface JPADataBaseFunction extends JPAFunction {
  /**
   * 
   * @return Name of the function on the database
   */
  public String getDBName();
}
