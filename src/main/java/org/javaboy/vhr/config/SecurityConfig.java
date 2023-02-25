package org.javaboy.vhr.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.vhr.model.Hr;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    HrService hrService;
    @Autowired
    VerificationCodeFilter verificationCodeFilter;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/index.html", "/img/**", "/fonts/**", "/favicon.ico", "/verifyCode","/empLogin","/training/basic/empApp","/training/basic/empPlan","/pro/basic/empTask",
                "/employee/basic/empProfile","/employee/basic/empInterest","/employee/basic/empSkill",
                "/employee/basic/empWork","/employee/basic/empEducation","/employee/basic/","/employee/basic/profile/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(verificationCodeFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/doLogin")
                .loginPage("/login")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        Hr hr = (Hr) authentication.getPrincipal();
                        hr.setPassword(null);
                        RespBean login_successfully = RespBean.ok("login successfully", hr);
                        String s = new ObjectMapper().writeValueAsString(login_successfully);
                        writer.write(s);
                        writer.flush();
                        writer.close();
                    }
                }).failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        RespBean login_failure = RespBean.error("Login failure");
                        if(exception instanceof LockedException){
                            login_failure.setMsg("locked");
                        }else if(exception instanceof CredentialsExpiredException){
                            login_failure.setMsg("Credential expired");
                        }else if(exception instanceof AccountExpiredException){
                            login_failure.setMsg("Account expired");
                        }else if(exception instanceof DisabledException){
                            login_failure.setMsg("disabled");
                        }else if(exception instanceof BadCredentialsException){
                            login_failure.setMsg("wrong password");
                        }

                        writer.write(new ObjectMapper().writeValueAsString(login_failure));
                        writer.flush();
                        writer.close();
                    }
                }).permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = response.getWriter();
                        writer.write(new ObjectMapper().writeValueAsString(RespBean.ok("log out")));
                        writer.flush();
                        writer.close();
                    }
                }).permitAll()
                .and()
                .csrf().disable();
    }
}