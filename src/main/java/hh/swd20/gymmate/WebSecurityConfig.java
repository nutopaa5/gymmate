package hh.swd20.gymmate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import hh.swd20.gymmate.web.UserDetailServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailServiceImpl userDetailsService;	
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        auth.inMemoryAuthentication().withUser("user")
        .password("user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin")
        .password("admin").roles("ADMIN");
    }
    
    // Permitting and authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests().antMatchers("/workoutlist").permitAll()
        .and()
        .authorizeRequests().antMatchers("/thumbup/*").permitAll()
        .and()
        .authorizeRequests().antMatchers("/thumbdown/*").permitAll()
        .and()
        .authorizeRequests().antMatchers("/css/**").permitAll() // Enable css when logged out
        .and()
        .authorizeRequests().antMatchers("/delete/*").hasRole("ADMIN")
        .and()
        .authorizeRequests().antMatchers("/delete/*").hasAuthority("ADMIN")
        .and()
        .authorizeRequests().antMatchers("/edit/*").hasRole("ADMIN")
        .and()
        .authorizeRequests().antMatchers("/edit/*").hasAuthority("ADMIN")
        .and()
        .authorizeRequests().antMatchers("/addworkout").hasRole("ADMIN")
        .and()
        .authorizeRequests().antMatchers("/addworkout").hasAuthority("ADMIN")
		.and()
		.authorizeRequests().antMatchers("/signup", "/saveuser").permitAll()
        .and()
        .authorizeRequests()
          .anyRequest().authenticated()
          .and()
      .formLogin()
          .defaultSuccessUrl("/workoutlist")
          .permitAll()
          .loginPage("/login")
          .permitAll()
          .and()
          
      .logout()
      .logoutSuccessUrl("/workoutlist")
      .permitAll();
      
    }
    
}