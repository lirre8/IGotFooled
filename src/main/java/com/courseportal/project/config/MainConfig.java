/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.courseportal.project.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Main configuration class for the application.
 * Turns on @Component scanning, loads externalized application.properties, and sets up the database.
 * @author Craig Walls
 */
@Configuration
@ComponentScan(basePackages = "com.courseportal.project", excludeFilters = { @Filter(Configuration.class) })
@PropertySource("classpath:com/courseportal/project/config/application.properties")
@EnableTransactionManagement
public class MainConfig {
    
    /**
     * Mysql datasource that works with live server
     * @throws SQLException
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:cloudbees://course_portal");
        dataSource.setUsername("course_portal");
        dataSource.setPassword("kaffekopp1987");
        return dataSource; 
    }
    
        @Bean(destroyMethod = "shutdown")
    public DataSource dataSource() {
        EmbeddedDatabaseFactory factory = new EmbeddedDatabaseFactory();
        factory.setDatabaseName("course_portal");
        factory.setDatabaseType(EmbeddedDatabaseType.H2);
        return factory.getDatabase();
    }
*/

	
    /**
     * Mysql datasource that works with live server
     * @throws SQLException*/
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUrl("jdbc:oracle:thin:@192.168.100.9:1521:XE");
        dataSource.setUsername("MATTE");
        dataSource.setPassword("tongji");
        return dataSource; 
    }
	

	
    @Bean
    public EntityManagerFactory entityManagerFactory() throws SQLException {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setPackagesToScan(new String[]{"com.courseportal.project.model", "com.courseportal.project.account"});
        entityManagerFactory.setMappingResources("Account.class");
        entityManagerFactory.setPersistenceProvider(new HibernatePersistence());

        Properties props = new Properties();

        //Autogenerating schemas in the database
        props.setProperty("hibernate.hbm2ddl.auto", "update");

        props.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        
        //props.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        props.put("hibernate.format_sql", "true");
        props.put("hibernate.show_sql", "true");

        entityManagerFactory.setJpaProperties(props);
        
        entityManagerFactory.setDataSource(dataSource());

        entityManagerFactory.afterPropertiesSet();
        
        
        //DatabasePopulatorUtils.execute(databasePopulator(), dataSource());
        
        return entityManagerFactory.getObject();
    }
	
    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory());
        transactionManager.setDataSource(dataSource());
        //transactionManager.setJpaDialect(new HibernateJpaDialect());
        return transactionManager;
    }
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}

	@Bean
	public PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
/*
	private DatabasePopulator databasePopulator() {
		ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(new ClassPathResource("JdbcUsersConnectionRepository.sql", JdbcUsersConnectionRepository.class));
		return populator;
	}*/
}
