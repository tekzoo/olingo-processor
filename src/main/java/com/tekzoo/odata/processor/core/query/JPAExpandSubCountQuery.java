package com.tekzoo.odata.processor.core.query;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.singletonList;
import static org.apache.olingo.commons.api.http.HttpStatusCode.INTERNAL_SERVER_ERROR;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Selection;
import jakarta.persistence.criteria.Subquery;

import org.apache.olingo.commons.api.ex.ODataException;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataApplicationException;
import org.apache.olingo.server.api.uri.queryoption.expression.ExpressionVisitException;

import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAAssociationPath;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAEntityType;
import com.tekzoo.odata.metadata.core.edm.mapper.api.JPAPath;
import com.tekzoo.odata.metadata.core.edm.mapper.exception.ODataJPAModelException;
import com.tekzoo.odata.processor.cb.ProcessorCriteriaQuery;
import com.tekzoo.odata.processor.core.api.JPAODataRequestContextAccess;
import com.tekzoo.odata.processor.core.exception.ODataJPAQueryException;

/**
 * Requires Processor Query
 *
 * @author Oliver Grande
 * @since 1.0.1
 * 25.11.2020
 */
public final class JPAExpandSubCountQuery extends JPAAbstractExpandQuery {

  public JPAExpandSubCountQuery(final OData odata, final JPAODataRequestContextAccess requestContext,
      final JPAEntityType et, final JPAAssociationPath association, final List<JPANavigationPropertyInfo> hops)
      throws ODataException {

    super(odata, requestContext, et, association, copyHops(hops));
  }

  private static List<JPANavigationPropertyInfo> copyHops(final List<JPANavigationPropertyInfo> hops) {
    return hops.stream()
        .map(JPANavigationPropertyInfo::new)
        .collect(Collectors.toList());
  }

  @Override
  public JPAExpandQueryResult execute() throws ODataApplicationException {
    final int handle = debugger.startRuntimeMeasurement(this, "execute");
    try {
      //
      return null;
    } finally {
      debugger.stopRuntimeMeasurement(handle);
    }
  }

  @Override
  protected Map<String, From<?, ?>> createFromClause(final List<JPAAssociationPath> orderByTarget,
      final Collection<JPAPath> selectionPath, final CriteriaQuery<?> query, final JPANavigationPropertyInfo lastInfo)
      throws ODataApplicationException, JPANoSelectionException {

    final HashMap<String, From<?, ?>> joinTables = new HashMap<>();

    createFromClauseRoot(query, joinTables, lastInfo);
    target = root;
    createFromClauseDescriptionFields(selectionPath, joinTables, target, singletonList(lastInfo));
    return joinTables;
  }

  @Override
  protected JPAAssociationPath getAssociation(final JPAInlineItemInfo item) {
    return item.hops.get(item.hops.size() - 2).getAssociationPath();
  }

  private Expression<Boolean> createExpandWhere(final JPANavigationPropertyInfo naviInfo)
      throws ODataApplicationException {

    try {
      return naviInfo.getFilterCompiler().compile();
    } catch (final ExpressionVisitException e) {
      throw new ODataJPAQueryException(ODataJPAQueryException.MessageKeys.QUERY_PREPARATION_FILTER_ERROR,
          HttpStatusCode.BAD_REQUEST, e);
    }
  }

  private void createFromClauseRoot(final CriteriaQuery<?> query, final HashMap<String, From<?, ?>> joinTables,
      final JPANavigationPropertyInfo lastInfo) throws ODataJPAQueryException {
    try {
      final JPAEntityType sourceEt = lastInfo.getEntityType();
      this.root = query.from(sourceEt.getTypeClass());
      this.lastInfo.setFromClause(root);
      joinTables.put(sourceEt.getExternalFQN().getFullQualifiedNameAsString(), root);
    } catch (final ODataJPAModelException e) {
      throw new ODataJPAQueryException(e, INTERNAL_SERVER_ERROR);
    }
  }

  @Override
  final Map<String, Long> count() throws ODataApplicationException {
    final int handle = debugger.startRuntimeMeasurement(this, "count");
    try {
      if (countRequested(lastInfo)) {
        final ProcessorCriteriaQuery<Tuple> tq = (ProcessorCriteriaQuery<Tuple>) cb.createTupleQuery();
        final LinkedList<JPAAbstractQuery> hops = new LinkedList<>();
        addFilterCompiler(lastInfo);
        hops.push(this);
        for (int i = navigationInfo.size() - 2; i >= 0; i--) {
          final JPANavigationPropertyInfo hop = navigationInfo.get(i);
          if (hop.getUriInfo() != null) {
            final JPAAbstractQuery parent = hops.getLast();
            final JPAAssociationPath childAssociation = i > 0 ? navigationInfo.get(i - 1).getAssociationPath() : null;
            hops.push(new JPAExpandFilterQuery(odata, requestContext, hop, parent, childAssociation));
          }
        }
        Subquery<Object> sq = null;
        while (!hops.isEmpty() && hops.getFirst() instanceof JPAAbstractSubQuery) {
          final JPAAbstractSubQuery hop = (JPAAbstractSubQuery) hops.pop();
          sq = hop.getSubQuery(sq);
        }
        createFromClause(emptyList(), emptyList(), tq, lastInfo);
        final List<Selection<?>> selectionPath = buildExpandJoinPath(root);
        tq.multiselect(addCount(selectionPath));
        tq.where(createWhere(sq, lastInfo));
        tq.groupBy(buildExpandCountGroupBy(root));
        final TypedQuery<Tuple> query = em.createQuery(tq);
        final List<Tuple> intermediateResult = query.getResultList();
        return convertCountResult(intermediateResult);
      }
      return emptyMap();
    } catch (final ODataException | JPANoSelectionException e) {
      throw new ODataJPAQueryException(e, HttpStatusCode.INTERNAL_SERVER_ERROR);
    } finally {
      debugger.stopRuntimeMeasurement(handle);
    }
  }

  private Expression<Boolean> createWhere(final Subquery<?> sq, final JPANavigationPropertyInfo naviInfo)
      throws ODataApplicationException {
    final int handle = debugger.startRuntimeMeasurement(this, "createWhere");

    try {
      Expression<Boolean> whereCondition = null;
      // Given keys: Organizations('1')/Roles(...)
      whereCondition = createWhereByKey(naviInfo.getFromClause(), naviInfo.getKeyPredicates(), naviInfo
          .getEntityType());
      whereCondition = addWhereClause(whereCondition, createWhereKeyIn(this.association, target, sq));
      whereCondition = addWhereClause(whereCondition, createExpandWhere(naviInfo));
      whereCondition = addWhereClause(whereCondition, createProtectionWhere(claimsProvider));
      return whereCondition;
    } catch (final ODataJPAModelException e) {
      throw new ODataJPAQueryException(ODataJPAQueryException.MessageKeys.QUERY_PREPARATION_ERROR,
          HttpStatusCode.INTERNAL_SERVER_ERROR, e);
    } finally {
      debugger.stopRuntimeMeasurement(handle);
    }
  }
}
