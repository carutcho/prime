package br.com.prime.services.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value ="classpath:messages_service_pt_BR.properties")
@Configuration
@ComponentScan(basePackages = {"br.com.prime.services","br.com.prime.commons","br.com.prime.data"})
public class AppConfig {

}
