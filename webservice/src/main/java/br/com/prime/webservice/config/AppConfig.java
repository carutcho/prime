package br.com.prime.webservice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"br.com.prime.services","br.com.prime.webservice","br.com.prime.commons"})
public class AppConfig {

}
