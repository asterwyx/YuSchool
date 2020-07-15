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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启全局方法权限验证
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserMapper userMapper;

    /**
     * 添加{@link BCryptPasswordEncoder}组件
     * @return {@link BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 在这里放行静态资源
     * @param web web安全验证链
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    /**
     * Override this method to configure the {@link HttpSecurity}. Typically subclasses
     * should not invoke this method by calling super as it may override their
     * configuration. The default configuration is:
     *
     * <pre>
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * </pre>
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll() // 登录界面和登录接口允许任何人访问
                .antMatchers(HttpMethod.POST, "/users").permitAll() // 允许所有人注册
                .antMatchers("/static/**").permitAll() // 静态资源允许任何人访问
                .anyRequest().hasRole(Authority.USER)
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    // 成功会返回登录用户的User对象
                    org.springframework.security.core.userdetails.User account = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
                    User user = userMapper.selectByUsername(account.getUsername());
                    ResUtil.writeObjectToResp(httpServletResponse, Result.builder().data(user).message("登录成功").build());
                })
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    ResUtil.writeObjectToResp(httpServletResponse, Result.withCode(RetCode.FAIL_OP).message("登录验证失败").build());
                })
                .permitAll()
                .and()
                .logout()
                .permitAll();
        http
                .csrf()
                .disable();
    }

    /**
     * 数据库中的信息存到User对象中
     * @return UserServiceImpl的组件
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new AccountServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }
}
