package qrizzly.project.spring;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import qrizzly.project.service.AllService;
import qrizzly.project.service.ReportsServiceImpl;
import qrizzly.project.store.ReportsDao;
import qrizzly.project.store.ReportsDaoImpl;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "qrizzly.project" })
@PropertySource(value = { "classpath:application.properties" })
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "qrizzly.project.model" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
    @Bean(name = "reportsS")
    @Autowired
    @Qualifier("reportsDaoImpl")
    public ReportsServiceImpl reportsService(ReportsDao reportsDao){
        ReportsServiceImpl reportsService = new ReportsServiceImpl();
        reportsService.setDao(reportsDao);
        return reportsService;
    }

    @Bean
    public ReportsDaoImpl reportsDaoImpl(){
        return new ReportsDaoImpl();
    }
    @Bean(name = "allService1")
    @Autowired
    @Qualifier("reportsDaoImpl")
    public AllService allService(ReportsDao reportsDao){
        AllService service = new AllService();
        service.setReportsDao(reportsDao);
        return service;
    }
}


