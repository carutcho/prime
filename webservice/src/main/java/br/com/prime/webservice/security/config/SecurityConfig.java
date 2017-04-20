package br.com.prime.webservice.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.prime.webservice.security.AutenticadorTokenFilter;
import br.com.prime.webservice.security.NaoAutorizadoResponse;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private NaoAutorizadoResponse unauthorizedHandler;
	  
	@Autowired	
	private UserDetailsService usuarios;
	
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(usuarios) .passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		 return new BCryptPasswordEncoder();
	}
	 
	@Bean
    public AutenticadorTokenFilter authenticationTokenFilterBean() throws Exception {
        return new AutenticadorTokenFilter();
    }
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().antMatchers("/auth/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//n sera neessario por conta do token
		http
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
		// nao cria sessao
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
        .authorizeRequests()
        .antMatchers("/auth/**").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/produto/encriptar").permitAll()		
		.antMatchers("/produto/incluir").hasRole("ADMIN")// não se usa ROLE_ na frente
		.anyRequest().authenticated();
		
		 // Usando JWT autenticacao
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        // desabilitando cache de pagina
        http.headers().cacheControl();
		
		
		/* Autenticação por usuario e senha em tela de login, gerar um modulo de exemplo contendo um container de web e remover essa config daqui!
		http.authorizeRequests()
		...
		.formLogin().permitAll();

		.loginPage("/login").permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher( "/logout"));
		*/
		
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	/* Autenticação por usuario e senha em tela de login, gerar um modulo de exemplo contendo um container de web e remover essa config daqui!
	*/
}
