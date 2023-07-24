package com.tekzoo.odata.processor.core.filter;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.From;

import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.uri.UriResource;

import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAAssociationPath;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAEntityType;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAServiceDocument;
import com.tekzoo.odata.processor.core.api.JPAODataClaimProvider;
import com.tekzoo.odata.processor.core.api.JPAServiceDebugger;
import com.tekzoo.odata.processor.core.query.JPAAbstractQuery;

interface JPAFilterComplierAccess {

  JPAAbstractQuery getParent();

  List<UriResource> getUriResourceParts();

  JPAServiceDocument getSd();

  OData getOdata();

  EntityManager getEntityManager();

  JPAEntityType getJpaEntityType();

  JPAOperationConverter getConverter();

  From<?, ?> getRoot();

  JPAServiceDebugger getDebugger();

  JPAAssociationPath getAssociation();

  Optional<JPAODataClaimProvider> getClaimsProvider();

  List<String> getGroups();

}
