package com.tekzoo.odata.processor.core.api;

import javax.annotation.Nonnull;

public interface JPAODataCRUDRequestContext extends JPAODataRequestContext {
  public void setCUDRequestHandler(@Nonnull final JPACUDRequestHandler jpaCUDRequestHandler);
}
