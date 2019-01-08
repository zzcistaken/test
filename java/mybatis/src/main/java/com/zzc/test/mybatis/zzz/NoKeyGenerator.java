package com.zzc.test.mybatis.zzz;

import java.sql.Statement;

/**
 * @author Clinton Begin
 */
public class NoKeyGenerator implements KeyGenerator {

  public void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
  }

  public void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
  }

}