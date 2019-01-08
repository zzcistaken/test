package com.zzc.test.mybatis.zzz;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.session.ResultHandler;

/**
 * @author Clinton Begin
 */
public interface StatementHandler {

  Statement prepare(Connection connection)
      throws SQLException;

  void parameterize(Statement statement)
      throws SQLException;

  void batch(Statement statement)
      throws SQLException;

  int update(Statement statement)
      throws SQLException;

  <E> List<E> query(Statement statement, ResultHandler resultHandler)
      throws SQLException;

  BoundSql getBoundSql();

  ParameterHandler getParameterHandler();

}