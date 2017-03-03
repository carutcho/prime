package br.com.prime.webservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired	
	private UserDetailsService usuarios;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/login").permitAll()
		.antMatchers("/encriptar").permitAll()		
		.antMatchers("/produto/incluir").hasRole("ADMIN")// não se usa ROLE_ na frente
		.anyRequest().authenticated()
		.and()
		.formLogin().permitAll();

		/* Autenticação por usuario e senha em tela de login, gerar um modulo de exemplo contendo um container de web e remover essa config daqui!
		.loginPage("/login").permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher( "/logout"));
		*/
		
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
  	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		auth.userDetailsService(usuarios).passwordEncoder(new BCryptPasswordEncoder());
	}

	
}
