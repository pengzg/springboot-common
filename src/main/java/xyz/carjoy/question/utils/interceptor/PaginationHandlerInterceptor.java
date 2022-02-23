package xyz.carjoy.question.utils.interceptor;


import java.util.List;
import java.util.Properties;

import xyz.carjoy.question.utils.Pager;
import xyz.carjoy.question.utils.Query;
import xyz.carjoy.question.utils.sql.dialect.Dialect;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

@Intercepts({@org.apache.ibatis.plugin.Signature(type=Executor.class, method="query", args={MappedStatement.class, Object.class, org.apache.ibatis.session.RowBounds.class, org.apache.ibatis.session.ResultHandler.class})})
public class PaginationHandlerInterceptor extends AbstractStatementHandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(PaginationHandlerInterceptor.class);
    static int MAPPED_STATEMENT_INDEX = 0;
    static int PARAMETER_INDEX = 1;
    static int ROWBOUNDS_INDEX = 2;
    static int RESULT_HANDLER_INDEX = 3;
    private Properties properties;
    private Dialect dialect;

    public Object intercept(Invocation invocation)
            throws Throwable
    {
        invocation.getTarget();
        processIntercept(invocation.getArgs());
        return invocation.proceed();
    }

    public Object plugin(Object target)
    {
        if ((target instanceof Executor)) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    public void setProperties(Properties properties)
    {
        this.properties = properties;
    }

    private void processIntercept(Object[] queryArgs)
            throws Exception
    {
        MappedStatement ms = (MappedStatement)queryArgs[MAPPED_STATEMENT_INDEX];
        Object parameter = queryArgs[PARAMETER_INDEX];
        BoundSql boundSql = null;
        Query query = null;
        String mapperSQL = null;
        if ((parameter instanceof Query))
        {
            query = (Query)parameter;
            boundSql = ms.getBoundSql(query.getQueryParams());
            mapperSQL = boundSql.getSql();
            Pager pager = query.getPager();
            if (!StringUtils.isEmpty(pager.getSort())) {
                if (!mapperSQL.toLowerCase().contains("order by"))
                {
                    mapperSQL = mapperSQL + " ORDER BY " + pager.getSort() + " " + pager.getOrder();
                }
                else
                {
                    StringBuffer temporary = new StringBuffer(mapperSQL);
                    temporary.insert(mapperSQL.toLowerCase().indexOf(" ORDER BY ") + 8, " " + pager.getSort() + " " + pager.getOrder() + ",");

                    mapperSQL = temporary.toString();
                }
            }
            int startIndex = (pager.getPage() - 1) * pager.getRows();
            log.info(mapperSQL);
           // mapperSQL = this.dialect.getLimitString(mapperSQL, startIndex, pager.getRows());
            mapperSQL = mapperSQL + " LIMIT " +startIndex +","+pager.getRows();

            queryArgs[PARAMETER_INDEX] = query.getQueryParams();
            Configuration configuration = ms.getConfiguration();
            List parameterMappings = ms.getBoundSql(queryArgs[PARAMETER_INDEX]).getParameterMappings();
            SqlSource sqlSource = new StaticSqlSource(configuration, mapperSQL, parameterMappings);
            MappedStatement newMs = copyFromMappedStatement(ms, sqlSource);
            queryArgs[MAPPED_STATEMENT_INDEX] = newMs;

            this.log.info("sq:" + mapperSQL);
        }
    }

    private void processIntercept_ba(Object[] queryArgs)
            throws Exception
    {
        MappedStatement ms = (MappedStatement)queryArgs[MAPPED_STATEMENT_INDEX];
        Object parameter = queryArgs[PARAMETER_INDEX];
        BoundSql boundSql = null;
        Query query = null;
        String mapperSQL = null;
        if ((this.dialect.supportsLimit()) && ((parameter instanceof Query)))
        {
            query = (Query)parameter;
            boundSql = ms.getBoundSql(query.getQueryParams());
            mapperSQL = boundSql.getSql();
            Pager pager = query.getPager();
            if (!StringUtils.isEmpty(pager.getSort())) {
                if (!mapperSQL.toLowerCase().contains("order by"))
                {
                    mapperSQL = mapperSQL + " order by " + pager.getSort() + " " + pager.getOrder();
                }
                else
                {
                    StringBuffer temporary = new StringBuffer(mapperSQL);
                    temporary.insert(mapperSQL.toLowerCase().indexOf("order by") + 8, " " + pager.getSort() + " " + pager.getOrder() + ",");

                    mapperSQL = temporary.toString();
                }
            }
            int startIndex = (pager.getPage() - 1) * pager.getRows();
            mapperSQL = this.dialect.getLimitString(mapperSQL, startIndex, pager.getRows());
            queryArgs[PARAMETER_INDEX] = query.getQueryParams();
            Configuration configuration = ms.getConfiguration();
            List parameterMappings = ms.getBoundSql(queryArgs[PARAMETER_INDEX]).getParameterMappings();
            SqlSource sqlSource = new StaticSqlSource(configuration, mapperSQL, parameterMappings);
            MappedStatement newMs = copyFromMappedStatement(ms, sqlSource);
            queryArgs[MAPPED_STATEMENT_INDEX] = newMs;
        }
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource)
    {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.keyProperty(StringUtils.arrayToDelimitedString(ms.getKeyProperties(), ","));
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        MappedStatement newMs = builder.build();
        return newMs;
    }
}
