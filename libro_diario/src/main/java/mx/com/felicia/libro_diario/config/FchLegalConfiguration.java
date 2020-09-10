package mx.com.felicia.libro_diario.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.cxf.bus.spring.SpringBus;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
@ComponentScan(basePackages="mx.com.felicia.libro_diario")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="mx.com.felicia.libro_diario.dal.repositories")
//@Import({ SecurityConfig.class })
public class FchLegalConfiguration{
	
	/**
	 * @return El datasource con la conexion a la DB.
	 */
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/libro_diario?serverTimezone=UTC&useSSL=false");
		dataSource.setUsername("fch_legal");
		dataSource.setPassword("cloaiza");
		return dataSource;
	}

	@Bean
	public Properties hibernateProperties(){
		final Properties properties = new Properties();

		properties.put( "hibernate.dialect", "org.hibernate.dialect.MySQLDialect" );
		properties.put( "hibernate.show_sql", "true" );
		properties.put( "hibernate.id.new_generator_mappings", "true");
		properties.put( "hibernate.hbm2ddl.auto", "none");

		return properties;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory( DataSource dataSource, 
			Properties hibernateProperties ){
		final LocalContainerEntityManagerFactoryBean em = 
				new LocalContainerEntityManagerFactoryBean();
		em.setDataSource( dataSource );
		em.setPackagesToScan( "mx.com.felicia.libro_diario.dal" );
		em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
		em.setJpaProperties( hibernateProperties );
		em.setPersistenceUnitName( "fch_solutions_jpa" );
		em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		em.afterPropertiesSet();

		return em.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory 
			entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
		
	
	@Bean
	public SpringBus cxf() {        
	    return new SpringBus();
	}
	
	@Bean 
	JacksonJsonProvider jsonProvider(){
		return new JacksonJsonProvider();
	}
}
