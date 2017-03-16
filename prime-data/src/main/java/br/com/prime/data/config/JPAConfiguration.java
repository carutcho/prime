package br.com.prime.data.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@PropertySource(value ="classpath:db-config.properties")
@Configuration
@EnableTransactionManagement
public class JPAConfiguration {
	
	@Autowired
	private Environment ev;
	
	@Bean(name = "genEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] {"br.com.prime.commons.entity" });

		em.setJpaVendorAdapter(jpaVendorAdapter());
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(ev.getProperty("db.driver"));
		dataSource.setUrl(ev.getProperty("db.url.connection"));
		dataSource.setUsername(ev.getProperty("db.user"));
		dataSource.setPassword(ev.getProperty("db.password"));
		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
	    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
	    jpaVendorAdapter.setShowSql(true);
	    jpaVendorAdapter.setGenerateDdl(false);
	    return jpaVendorAdapter;
	}

	private Properties additionalProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto",ev.getProperty("db.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect", ev.getProperty("db.dialect"));
		properties.setProperty("hibernate.show_sql", ev.getProperty("db.show.sql"));
		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
}

