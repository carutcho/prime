package br.com.prime.commons.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value ={"classpath:messages_commons_pt_BR.properties"})
@Configuration
@ComponentScan(basePackages = {"br.com.prime.commons"})
public class AppConfig {

}
