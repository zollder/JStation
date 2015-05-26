/*
 * DatabaseConfig.java
 * Created by zollder.
 */
package org.station.configuration;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource("classpath:/org/station/db/database.properties")
public class DatabaseConfig
{
	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource()
	{
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL(env.getProperty("jdbc.url"));
		dataSource.setUser(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager transactionManager()
	{
		return new DataSourceTransactionManager(dataSource());
	}
}
