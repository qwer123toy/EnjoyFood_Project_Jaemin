package config;

import java.sql.Connection;
import java.sql.SQLException;

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

import Cafeteria.CafeteriaMapper;
import user.model.UserMapper;

@WebListener
public class AppContextListener implements ServletContextListener {
	private static DataSource dataSource;
	private static SqlSessionFactory sessionFactory;

	private static final String driverClassName = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://192.168.0.3:3306/enjoyfood";
	private static final String username = "newuser";
	private static final String password = "roott";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		initDataSource();
		initSqlSessionFactory();
	}

	private void initSqlSessionFactory() {
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment("dev", transactionFactory, dataSource);

		Configuration configuration = new Configuration(environment);
		configuration.addMapper(UserMapper.class);
		configuration.addMapper(CafeteriaMapper.class);

		sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
	}

	private void initDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
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
}
