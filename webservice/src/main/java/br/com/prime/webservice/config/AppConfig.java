package br.com.prime.webservice.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceWebArgumentResolver;
import org.springframework.mobile.device.site.SitePreferenceHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;

@PropertySource(value = {"classpath:messages_webservice_pt_BR.properties","classpath:config.properties"})
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"br.com.prime.services", "br.com.prime.data","br.com.prime.webservice","br.com.prime.commons"})
public class AppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new ServletWebArgumentResolverAdapter(new DeviceWebArgumentResolver()));
		argumentResolvers.add(new SitePreferenceHandlerMethodArgumentResolver());
	}
	
	@Bean
	public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
	    return new DeviceHandlerMethodArgumentResolver();
	}
	
	 public void addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/**")
         .allowedOrigins("*")
         .allowedMethods("GET", "OPTIONS", "POST", "PUT", "DELETE")
         .allowedHeaders("*")
//         .exposedHeaders("header1", "header2")
         .allowCredentials(true)
         .maxAge(3600);
    }
}
