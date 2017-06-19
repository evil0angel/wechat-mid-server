package com.jary.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Druid的DataResource配置类
 */
@Configuration
public class DruidConfiguration {
    @Bean
    public ServletRegistrationBean druidServlet() {
        return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    }

    @Bean(name = "dataSource", destroyMethod = "close", initMethod = "init")
    public DataSource druidDataSource(@Value("${spring.datasource.driver-class-name:}") String driver,
                                      @Value("${spring.datasource.url}") String url,
                                      @Value("${spring.datasource.username}") String username,
                                      @Value("${spring.datasource.password}") String password,
                                      @Value("${spring.datasource.druid.initial-size}") String initialSize,
                                      @Value("${spring.datasource.druid.min-idle}") String minIdle,
                                      @Value("${spring.datasource.druid.max-wait}") String maxWait,
                                      @Value("${spring.datasource.druid.max-active}") String maxActive,
                                      @Value("${spring.datasource.druid.time-between-eviction-runs-millis}") String timeBetweenEvictionRunsMillis,
                                      @Value("${spring.datasource.druid.min-evictable-idle-time-millis}") String minEvictableIdleTimeMillis) {
        DruidDataSource druidDataSource = new DruidDataSource();
        //数据库类型
        druidDataSource.setDriverClassName(driver);
        //数据库地址
        druidDataSource.setUrl(url);
        //数据库用户名
        druidDataSource.setUsername(username);
        //数据库密码
        druidDataSource.setPassword(password);
        //配置初始化大小、最小、最大
        druidDataSource.setInitialSize(Integer.valueOf(initialSize));
        druidDataSource.setMinIdle(Integer.valueOf(minIdle));
        druidDataSource.setMaxActive(Integer.valueOf(maxActive));
        //配置获取连接等待超时的时间
        druidDataSource.setMaxWait(Long.valueOf(maxWait));
        //配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
        druidDataSource.setTimeBetweenEvictionRunsMillis(Long.valueOf(timeBetweenEvictionRunsMillis));
        //配置一个连接在池中最小生存的时间，单位是毫秒
        druidDataSource.setMinEvictableIdleTimeMillis(Long.valueOf(minEvictableIdleTimeMillis));
        try {
            druidDataSource.setFilters("stat, wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return druidDataSource;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
