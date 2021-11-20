package pe.edu.upc.finanzasapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;





@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	//Instanciar la clase
	@Autowired
	private LoggingAccessDeniedHandler loggingAccessDeniedHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
			.authorizeRequests()
				.antMatchers("/").permitAll()
			.antMatchers("/registros").hasRole("ADMIN")	
			.antMatchers("/registros/**").hasRole("ADMIN")		
			.antMatchers("/descuentos").hasRole("ADMIN")				
			.antMatchers("/descuentos/**").hasRole("ADMIN")
			.and()
			.formLogin()
			.loginProcessingUrl("/signin")
			.loginPage("/login").permitAll()
			.usernameParameter("username")
			.passwordParameter("password")
		 	.and()
		 	.logout()
		 		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/")
			.and()
			.exceptionHandling()
			.accessDeniedHandler(loggingAccessDeniedHandler);
		
			;
			//Registrar el acces Denied
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Aqui se crea el vinculo entre el Spring security y el PasswordEncoder y UserDetailsService
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.myUserDetailsService);
		return daoAuthenticationProvider;
	}
}
