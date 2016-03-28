package com.techcrux.rest.messenger.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.techcrux.rest.messenger.util.MessengerAppConstants;

@Configuration
@EnableWebMvc
@Profile("production")
@EnableAsync()
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan(basePackages = { "com.techcrux.rest.messenger",
		"com.techcrux.rest.messenger.service.impl" }, excludeFilters = { @ComponentScan.Filter(Configuration.class) })
@PropertySource({ "classpath:messenger-app-config.properties" })
public class MessengerWebAppConfig extends WebMvcConfigurationSupport {

	@Value("${hibernate.dialect}")
	private String hibernateDialect;

	@Value("${db.driver.classname}")
	private String driverClass;

	@Value("${db.url}")
	private String url;

	@Value("${db.username}")
	private String username;

	@Value("${db.password}")
	private String password;

	@Bean(name = "appProperty")
	public static PropertySourcesPlaceholderConfigurer appProperty() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DataSource dataSource() {
		/*
		 * BasicDataSource datasource = new BasicDataSource();
		 * datasource.setDriverClassName(driverClass); datasource.setUrl(url);
		 * datasource.setUsername(username); datasource.setPassword(password);
		 * datasource.setDefaultAutoCommit(false);
		 */

		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();

		// return datasource;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() throws Exception {

		Properties properties = new Properties();
		properties.put(MessengerAppConstants.HIBERNATE_DIALECT_PROPERTY, hibernateDialect);
		properties.put(MessengerAppConstants.HIBERNATE_SHOW_SQL_PROPERTY, "true");
		properties.put(MessengerAppConstants.HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS_PROPERTY, "thread");
		properties.put("dynamic-update", "true");
		properties.put("shutdown", "false");
		properties.put("hibernate.hbm2ddl.auto", "create");
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setPackagesToScan(new String[] { MessengerAppConstants.ENTITIES_PACKAGE });
		factory.setDataSource(dataSource());
		factory.setHibernateProperties(properties);
		factory.setPackagesToScan("com.techcrux.rest.messenger.entities");
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager() throws Exception {
		return new HibernateTransactionManager(getSessionFactory());
	}

	@Override
	protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).favorParameter(true).parameterName("mediaType").ignoreAcceptHeader(true)
				.useJaf(false).defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML).mediaType("json", MediaType.APPLICATION_JSON);
	}

}
