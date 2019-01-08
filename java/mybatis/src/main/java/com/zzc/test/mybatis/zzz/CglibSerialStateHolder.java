package com.zzc.test.mybatis.zzz;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.reflection.factory.ObjectFactory;

/**
 * @author Eduardo Macarron
 */
class CglibSerialStateHolder extends AbstractSerialStateHolder {

  private static final long serialVersionUID = 8940388717901644661L;

  public CglibSerialStateHolder() {
  }

  public CglibSerialStateHolder(
          final Object userBean,
          final Map<String, ResultLoaderMap.LoadPair> unloadedProperties,
          final ObjectFactory objectFactory,
          List<Class<?>> constructorArgTypes,
          List<Object> constructorArgs) {
    super(userBean, unloadedProperties, objectFactory, constructorArgTypes, constructorArgs);
  }

  @Override
  protected Object createDeserializationProxy(Object target, Map<String, ResultLoaderMap.LoadPair> unloadedProperties, ObjectFactory objectFactory,
          List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
    return new CglibProxyFactory().createDeserializationProxy(target, unloadedProperties, objectFactory, constructorArgTypes, constructorArgs);
  }
}
