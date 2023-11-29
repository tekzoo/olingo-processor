package com.tekzoo.odata.processor.core.modify;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Tuple;

import org.apache.olingo.server.api.ODataApplicationException;

import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAAssociationPath;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPACollectionAttribute;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAEntityType;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAPath;
import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;
import com.tekzoo.odata.processor.core.converter.JPACollectionResult;
import com.tekzoo.odata.processor.core.converter.JPATuple;
import com.tekzoo.odata.processor.core.converter.JPATupleChildConverter;
import com.tekzoo.odata.processor.core.exception.ODataJPAProcessorException;

class JPAMapCollectionResult extends JPAMapBaseResult implements JPACollectionResult {
  private Map<String, List<Object>> converted;
  private final JPAAssociationPath path;

  public JPAMapCollectionResult(final JPAEntityType et, final Collection<?> values,
      final Map<String, List<String>> requestHeaders, final JPACollectionAttribute attribute)
      throws ODataJPAModelException, ODataJPAProcessorException {
    super(et, requestHeaders);
    this.path = attribute.asAssociation();
    result = convertToTuple(et, values, attribute);
  }

  @SuppressWarnings("unchecked")
  private List<Tuple> convertToTuple(final JPAEntityType et, final Collection<?> values,
      final JPACollectionAttribute attribute) throws ODataJPAProcessorException, ODataJPAModelException {

    final List<Tuple> tupleList = new ArrayList<>();
    for (final Object value : values) {
      final JPATuple tuple = new JPATuple();
      if (attribute.isComplex()) {
        for (JPAPath p : attribute.getStructuredType().getPathList())
          convertPathToTuple(tuple, (Map<String, Object>) value, et.getPath(this.path.getAlias()
              + JPAPath.PATH_SEPARATOR + p.getAlias()), 1);
      } else {
        tuple.addElement(path.getAlias(), attribute.getType(), value);
      }
      tupleList.add(tuple);
    }
    return tupleList;
  }

  @Override
  public void convert(final JPATupleChildConverter converter) throws ODataApplicationException {
    converted = converter.getCollectionResult(this, Collections.emptySet());
  }

  @Override
  public Collection<Object> getPropertyCollection(String key) {
    return converted.get(ROOT_RESULT_KEY);
  }

  @Override
  public JPAAssociationPath getAssociation() {
    return path;
  }

}
