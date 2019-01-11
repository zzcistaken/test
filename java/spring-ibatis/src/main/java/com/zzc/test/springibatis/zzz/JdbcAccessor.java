package com.zzc.test.springibatis.zzz;

import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

public abstract class JdbcAccessor implements InitializingBean {
	protected final Log logger = LogFactory.getLog(this.getClass());
	private DataSource dataSource;
	private SQLExceptionTranslator exceptionTranslator;
	private boolean lazyInit = true;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSource() {
		return this.dataSource;
	}

	public void setDatabaseProductName(String dbName) {
		this.exceptionTranslator = new SQLErrorCodeSQLExceptionTranslator(dbName);
	}

	public void setExceptionTranslator(SQLExceptionTranslator exceptionTranslator) {
		this.exceptionTranslator = exceptionTranslator;
	}

	public synchronized SQLExceptionTranslator getExceptionTranslator() {
		if (this.exceptionTranslator == null) {
			DataSource dataSource = this.getDataSource();
			if (dataSource != null) {
				this.exceptionTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
			} else {
				this.exceptionTranslator = new SQLStateSQLExceptionTranslator();
			}
		}

		return this.exceptionTranslator;
	}

	public void setLazyInit(boolean lazyInit) {
		this.lazyInit = lazyInit;
	}

	public boolean isLazyInit() {
		return this.lazyInit;
	}

	public void afterPropertiesSet() {
		if (this.getDataSource() == null) {
			throw new IllegalArgumentException("Property \'dataSource\' is required");
		} else {
			if (!this.isLazyInit()) {
				this.getExceptionTranslator();
			}

		}
	}
}
