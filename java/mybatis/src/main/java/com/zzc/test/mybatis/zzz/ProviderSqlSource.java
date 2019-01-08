package com.zzc.test.mybatis.zzz;

import java.lang.reflect.Method;
import java.util.HashMap;

import org.apache.ibatis.builder.BuilderException;

/**
 * @author Clinton Begin
 */
public class ProviderSqlSource implements SqlSource {

  private SqlSourceBuilder sqlSourceParser;
  private Class<?> providerType;
  private Method providerMethod;
  private boolean providerTakesParameterObject;

  public ProviderSqlSource(Configuration config, Object provider) {
    String providerMethodName = null;
    try {
      this.sqlSourceParser = new SqlSourceBuilder(config);
      this.providerType = (Class<?>) provider.getClass().getMethod("type").invoke(provider);
      providerMethodName = (String) provider.getClass().getMethod("method").invoke(provider);

      for (Method m : this.providerType.getMethods()) {
        if (providerMethodName.equals(m.getName())) {
          if (m.getParameterTypes().length < 2
              && m.getReturnType() == String.class) {
            this.providerMethod = m;
            this.providerTakesParameterObject = m.getParameterTypes().length == 1;
          }
        }
      }
    } catch (Exception e) {
      throw new BuilderException("Error creating SqlSource for SqlProvider.  Cause: " + e, e);
    }
    if (this.providerMethod == null) {
      throw new BuilderException("Error creating SqlSource for SqlProvider. Method '"
          + providerMethodName + "' not found in SqlProvider '" + this.providerType.getName() + "'.");
    }
  }

  public BoundSql getBoundSql(Object parameterObject) {
    SqlSource sqlSource = createSqlSource(parameterObject);
    return sqlSource.getBoundSql(parameterObject);
  }

  private SqlSource createSqlSource(Object parameterObject) {
    try {
      String sql;
      if (providerTakesParameterObject) {
        sql = (String) providerMethod.invoke(providerType.newInstance(), parameterObject);
      } else {
        sql = (String) providerMethod.invoke(providerType.newInstance());
      }
      Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
      return sqlSourceParser.parse(sql, parameterType, new HashMap<String, Object>());
    } catch (Exception e) {
      throw new BuilderException("Error invoking SqlProvider method ("
          + providerType.getName() + "." + providerMethod.getName()
          + ").  Cause: " + e, e);
    }
  }

}
