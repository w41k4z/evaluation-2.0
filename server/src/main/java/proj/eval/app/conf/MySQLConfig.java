package proj.eval.app.conf;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
  basePackages = "proj.eval.app.repository.mysql",
  entityManagerFactoryRef = "mysqlEntityManagerFactory",
  transactionManagerRef = "mysqlTransactionManager"
)
public class MySQLConfig {

  @Autowired
  private Environment env;

  @Bean(name = "mysqlDataSource")
  DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(
      env.getProperty("spring.datasource.mysql.driver-class-name")
    );
    dataSource.setUrl(env.getProperty("spring.datasource.mysql.url"));
    dataSource.setUsername(env.getProperty("spring.datasource.mysql.username"));
    dataSource.setPassword(env.getProperty("spring.datasource.mysql.password"));
    return dataSource;
  }

  @Bean(name = "mysqlEntityManagerFactory")
  LocalContainerEntityManagerFactoryBean entityManagerFactory(
    EntityManagerFactoryBuilder builder,
    @Qualifier("mysqlDataSource") DataSource dataSource
  ) {
    return builder
      .dataSource(dataSource)
      .packages("proj.eval.app.model.mysql")
      .persistenceUnit("mysql")
      .build();
  }

  @Bean(name = "mysqlTransactionManager")
  PlatformTransactionManager transactionManager(
    @Qualifier(
      "mysqlEntityManagerFactory"
    ) EntityManagerFactory entityManagerFactory
  ) {
    return new JpaTransactionManager(entityManagerFactory);
  }
}
