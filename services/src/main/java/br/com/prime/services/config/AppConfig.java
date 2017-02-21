package br.com.prime.services.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"br.com.prime.services","br.com.prime.commons","br.com.prime.data"})
public class AppConfig {

}
