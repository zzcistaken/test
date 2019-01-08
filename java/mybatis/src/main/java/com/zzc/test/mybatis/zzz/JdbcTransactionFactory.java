package com.zzc.test.mybatis.zzz;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

/**
 * Creates {@link JdbcTransaction} instances.
 *
 * @see JdbcTransaction
 */
/**
 * @author Clinton Begin
 */
public class JdbcTransactionFactory implements TransactionFactory {

  public void setProperties(Properties props) {
  }

  public Transaction newTransaction(Connection conn) {
	  System.out.println("JdbcTransactionFactory -- newTransaction(1)");
	  return new JdbcTransaction(conn);
  }

  public Transaction newTransaction(DataSource ds, TransactionIsolationLevel level, boolean autoCommit) {
	  System.out.println("JdbcTransactionFactory -- newTransaction(2)");
	  return new JdbcTransaction(ds, level, autoCommit);
  }
}
