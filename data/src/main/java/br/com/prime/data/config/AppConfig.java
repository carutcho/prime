package br.com.prime.data.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"br.com.prime.data","br.com.prime.commons"})
@Import({JPAConfiguration.class, AuthServerOAuth2Config.class})
public class AppConfig {

}
