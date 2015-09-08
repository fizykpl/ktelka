package pl.sylwek.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@Import({WebConfig.class})
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = {"pl.*"})
public class MvcConfig extends WebMvcConfigurerAdapter{
	
	
	@Value("${jdbc.driverClass}")
	private String driverClass;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Value("${jdbc.user}")
	private String user;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigure(){
		
		return new PropertySourcesPlaceholderConfigurer();
	}
	@Bean
	public ViewResolver viewResolver(){
		System.out.println("#####"+url);
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setSuffix(".jsp");
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setViewClass(JstlView.class);
		resolver.setExposeContextBeansAsAttributes(true);
		return resolver;
	}
	
	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driverClass);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		return ds;
	}
	
	private static Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource ds) {
		LocalSessionFactoryBean sfb = new LocalSessionFactoryBean();
		sfb.setDataSource(ds);
		sfb.setPackagesToScan(new String[] { "pl.*" });
		sfb.setHibernateProperties(hibernateProperties());
		return sfb;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}	

}
