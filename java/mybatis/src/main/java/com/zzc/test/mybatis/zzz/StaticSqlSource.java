package com.zzc.test.mybatis.zzz;

import java.util.List;

/**
 * @author Clinton Begin
 */
public class StaticSqlSource implements SqlSource {

  private String sql;
  private List<ParameterMapping> parameterMappings;
  private Configuration configuration;

  public StaticSqlSource(Configuration configuration, String sql) {
    this(configuration, sql, null);
  }

  public StaticSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
    this.sql = sql;
    this.parameterMappings = parameterMappings;
    this.configuration = configuration;
  }

  public BoundSql getBoundSql(Object parameterObject) {
    return new BoundSql(configuration, sql, parameterMappings, parameterObject);
  }

}
