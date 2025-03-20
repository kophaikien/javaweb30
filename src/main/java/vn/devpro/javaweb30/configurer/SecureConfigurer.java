package vn.devpro.javaweb30.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import vn.devpro.javaweb30.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecureConfigurer extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure (final HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()//Batcac request tu browser
		//cho phep cacs request, static resources khong bi rang buoc login
		.antMatchers("/frontend/**", "/backend/**", "/FileUploads/**", "/login", "/logout")
		.permitAll()
		//Cac request kieu "/admin/** phai login (xac thuc)
//		.antMatchers("/admin/**").authenticated() // step 1 + 2
		// cac request kieu /admin/** phai co role la ADMIN // step 3
		.antMatchers("/admin/**").hasAuthority("ADMIN")
		.and()
		
		// Neu chua login (xac thuc ) thi redirect den trang login
		// voi "/login" la 1 request (Trong loginController)
		.formLogin().loginPage("/login").loginProcessingUrl("/login_processing_url")
//		.defaultSuccessUrl("/admin/home/view", true)// login thanh cong thi vao trang home
		
		// (backend) step 1 + 2
		// login thanh cong : Chuyen den request phu hop voi role step 3
		 .successHandler(new UrlAuthenticationSuccessHandler())
		// login khong thanh cong
		.failureUrl("/login?login_error=true")
		
		.and()
		
		// cau hinh phan logout
		.logout().logoutUrl("/logout").logoutSuccessUrl("/index").invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		
		.and().rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400);
	}
	@Autowired
	private UserDetailsServiceImpl userDetailsService; 
	
	@Autowired
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder(4)); 
	}
//	public static void main(String[] args) {
//		System.out.println(new BCryptPasswordEncoder(4).encode("123"));
//	}
	
}


