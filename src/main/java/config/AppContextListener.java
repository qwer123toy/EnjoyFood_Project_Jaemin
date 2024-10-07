package config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import user.model.UserMapper;

@WebListener
public class AppContextListener implements ServletContextListener {
	private static DataSource dataSource;
	private static SqlSessionFactory sessionFactory;

	private static String driverClassName;
	private static String url;
	private static String schema;
	private static String username;
	private static String password;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
//		loadProperties("localDB.properties"); // 로컬 DB
		loadProperties("publicDB.properties"); // 외부 DB
		initDataSource();
		initSqlSessionFactory();
	}

	private void initSqlSessionFactory() {
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("dev", transactionFactory, dataSource);

		Configuration configuration = new Configuration(environment);
		configuration.addMapper(UserMapper.class);

		sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
	}

	private void initDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url + schema);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		// object pool 크기
//		dataSource.setMaxTotal(10); // 최대 개수
//		dataSource.setInitialSize(5); // 기본 개수
//		dataSource.setMinIdle(0); // 최소로 남겨놓을 개수
//		dataSource.setMaxIdle(10); // 최대 개수

		AppContextListener.dataSource = dataSource;
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static SqlSession getSqlSession() {
		return sessionFactory.openSession();
	}

	private static void loadProperties(String filename) {
		try (InputStream resourceStream = AppContextListener.class.getResourceAsStream(filename);) {
			Properties properties = new Properties();
			properties.load(resourceStream);

			driverClassName = properties.getProperty("driver");
			url = properties.getProperty("url");
			schema = properties.getProperty("schema");
			username = properties.getProperty("user");
			password = properties.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
