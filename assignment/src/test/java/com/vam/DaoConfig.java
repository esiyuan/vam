package com.vam;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.vam.paging.MysqlStaticPaging;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author guanjie
 * @create 2017/12/15
 */
@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySource(value = "classpath:dao.properties")
public class DaoConfig implements EnvironmentAware, ApplicationContextAware {
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    private Environment environment;
    private ApplicationContext applicationContext;


    @Bean(destroyMethod = "close", initMethod = "init")
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(environment.getProperty("jdbc.driverClass"));
        druidDataSource.setUrl(environment.getProperty("jdbc.url"));
        druidDataSource.setUsername(environment.getProperty("jdbc.user"));
        druidDataSource.setPassword(environment.getProperty("jdbc.password"));
        druidDataSource.setInitialSize(1);
        druidDataSource.setMinIdle(1);
        druidDataSource.setMaxActive(20);
        druidDataSource.setMaxWait(6000);
        druidDataSource.setTransactionQueryTimeout(60000);
        druidDataSource.setMinEvictableIdleTimeMillis(300000);
        return druidDataSource;
    }


    @Bean
    DataSourceTransactionManager dataSourceTransactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    TransactionTemplate transactionTemplate() throws PropertyVetoException {
        return new TransactionTemplate(dataSourceTransactionManager());
    }

    @Bean(name = "sqlSessionFactoryBean")
    SqlSessionFactoryBean sqlSessionFactoryBean() throws PropertyVetoException, IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
//        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new MysqlStaticPaging()});
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:mybatis/*.xml"));
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper()});
        return sqlSessionFactoryBean;
    }

    @Bean
    PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.put("dialect", "mysql");
        properties.put("reasonable", true);
        properties.put("offsetAsPageNum", true);
        properties.put("pageSizeZero", true);
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    @Bean
    MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.vam.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return mapperScannerConfigurer;
    }
}
