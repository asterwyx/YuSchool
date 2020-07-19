package com.yuschool.config;

import com.yuschool.bean.Authority;
import com.yuschool.bean.User;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.mapper.UserMapper;
import com.yuschool.service.impl.AccountServiceImpl;
import com.yuschool.utils.ResUtil;
import com.yuschool.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启全局方法权限验证
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserMapper userMapper;
    @Autowired
    DataSource dataSource;

    /**
     * 添加{@link BCryptPasswordEncoder}组件
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 在这里放行静态资源
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll() // 登录界面和登录接口允许任何人访问
                .antMatchers("/login.html").permitAll()
                .antMatchers("/register.html").permitAll()
                .antMatchers("/mainPage.html").permitAll()
                .antMatchers("/open/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll() // 允许所有人注册
                .antMatchers("/static/**").permitAll() // 静态资源允许任何人访问
                .anyRequest().hasRole(Authority.USER)
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(authenticationSuccessHandler())
                .failureHandler((httpServletRequest, httpServletResponse, e) -> ResUtil.writeObjectToResp(httpServletResponse, Result.withRetCode(RetCode.FAIL_OP).message("登录验证失败").build()))
                .permitAll();
        http
                .csrf()
                .disable();
        http
                .logout()
                .clearAuthentication(true)
                .permitAll();
        http
                .rememberMe()
                .alwaysRemember(true)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(36000)
                .authenticationSuccessHandler(authenticationSuccessHandler());
    }

    /**
     * 数据库中的信息存到User对象中
     * @return UserServiceImpl的组件
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new AccountServiceImpl();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // 成功会返回登录用户的User对象
            org.springframework.security.core.userdetails.User account = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
            User user = userMapper.selectByUsername(account.getUsername());
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            ResUtil.writeObjectToResp(response, Result.builder().message("登录成功").build());
        };
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(this.dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
}
