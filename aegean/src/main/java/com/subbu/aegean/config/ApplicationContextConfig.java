package com.subbu.aegean.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.subbu.aegean.dao.CategoryDAO;
import com.subbu.aegean.dao.CategoryDAOImpl;
import com.subbu.aegean.dao.ProductDAO;
import com.subbu.aegean.dao.ProductDAOImpl;
import com.subbu.aegean.dao.SupplierDAO;
import com.subbu.aegean.dao.SupplierDAOImpl;
import com.subbu.aegean.dao.UserDAO;
import com.subbu.aegean.dao.UserDAOImpl;
import com.subbu.aegean.model.Category;
import com.subbu.aegean.model.Product;
import com.subbu.aegean.model.Supplier;
import com.subbu.aegean.model.User;

@Configuration
@ComponentScan("com.subbu.aegean")
@EnableTransactionManagement

public class ApplicationContextConfig {

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/test16");

		dataSource.setUsername("sa");
		dataSource.setPassword("sa");
		return dataSource;
	}

	private Properties getHibernateProperties() {

		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.hbm2ddl.auto", "update");

		return properties;

	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(Category.class);
		sessionBuilder.addAnnotatedClass(Supplier.class);
		sessionBuilder.addAnnotatedClass(Product.class);
		sessionBuilder.addAnnotatedClass(User.class);

		return sessionBuilder.buildSessionFactory();

	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;

	}

	@Autowired

	@Bean(name = "categoryDao")
	public CategoryDAO getCategoryDao(SessionFactory sessionFactory) {
		return new CategoryDAOImpl(sessionFactory);

	}

	@Autowired

	@Bean(name = "supplierDao")
	public SupplierDAO getSupplierDao(SessionFactory sessionFactory) {
		return new SupplierDAOImpl(sessionFactory);

	}

	@Autowired

	@Bean(name = "productDao")
	public ProductDAO getProductDao(SessionFactory sessionFactory) {
		return new ProductDAOImpl(sessionFactory);
	}

	@Autowired

	@Bean(name = "userDao")
	public UserDAO getUserDao(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}

}
