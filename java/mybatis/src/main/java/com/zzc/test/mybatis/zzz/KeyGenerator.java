package com.zzc.test.mybatis.zzz;

import java.sql.Statement;

/**
 * @author Clinton Begin
 */
public interface KeyGenerator {

  void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter);

  void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter);

}
