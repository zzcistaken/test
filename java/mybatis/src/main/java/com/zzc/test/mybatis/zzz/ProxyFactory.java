package com.zzc.test.mybatis.zzz;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.reflection.factory.ObjectFactory;

/**
 * @author Eduardo Macarron
 */
public interface ProxyFactory {

  void setProperties(Properties properties);

  Object createProxy(Object target, ResultLoaderMap lazyLoader, Configuration configuration, ObjectFactory objectFactory, List<Class<?>> constructorArgTypes, List<Object> constructorArgs);
  
}
