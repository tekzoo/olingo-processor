package com.tekzoo.odata.processor.core.converter;

import java.util.List;
import java.util.Map;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;
import jakarta.persistence.Tuple;

import org.apache.olingo.server.api.ODataApplicationException;

import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAAssociationPath;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAEntityType;

public interface JPAExpandResult { // NOSONAR

  String ROOT_RESULT_KEY = "root";

  @CheckForNull
  JPAExpandResult getChild(final JPAAssociationPath associationPath);

  Map<JPAAssociationPath, JPAExpandResult> getChildren();

  @CheckForNull
  Long getCount(final String string);

  @Nonnull
  JPAEntityType getEntityType();

  List<Tuple> getResult(final String key);

  Map<String, List<Tuple>> getResults();

  boolean hasCount();

  void convert(final JPATupleChildConverter converter) throws ODataApplicationException;

}