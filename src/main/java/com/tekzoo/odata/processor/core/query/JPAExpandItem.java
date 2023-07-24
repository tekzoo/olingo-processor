package com.tekzoo.odata.processor.core.query;

import org.apache.olingo.server.api.uri.UriInfoResource;

import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAEntityType;

public interface JPAExpandItem extends UriInfoResource {

  JPAEntityType getEntityType();

}