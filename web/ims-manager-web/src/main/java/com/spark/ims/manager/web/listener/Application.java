
package com.spark.ims.manager.web.listener;

import com.mangofactory.swagger.plugin.EnableSwagger;
import com.spark.ims.web.config.WebMvcConfig;
import org.jodconverter.boot.autoconfigure.JodConverterAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by liyuan on 2018/4/29.
 */

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class, JodConverterAutoConfiguration.class})
@ImportResource({"classpath:/spring/app-config.xml"})
@Import({WebMvcConfig.class})
@EnableSwagger
@PropertySource(value="file:${IMS_CONFIG_PATH}/application.properties", ignoreResourceNotFound = true)
public class Application extends SpringBootServletInitializer {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .sources(WebMvcConfig.class)
                .run(args);
    }

    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        application.sources(WebMvcConfig.class);
        return application;
    }

    @Bean
    public ErrorPageFilter errorPageFilter() {
        return new ErrorPageFilter();
    }

    @Bean
    public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

}

