package com.zzc.test.mybatis.zzz;

import java.lang.reflect.Method;

/**
 * @author Eduardo Macarron
 */
public class MethodResolver {
  private final MapperAnnotationBuilder annotationBuilder;
  private Method method;

  public MethodResolver(MapperAnnotationBuilder annotationBuilder, Method method) {
    this.annotationBuilder = annotationBuilder;
    this.method = method;
  }

  public void resolve() {
    annotationBuilder.parseStatement(method);
  }

}
