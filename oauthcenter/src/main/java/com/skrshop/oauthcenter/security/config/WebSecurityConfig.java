package com.skrshop.oauthcenter.security.config;


import com.skrshop.oauthcenter.security.config.properties.SkrShopAuthorityCenterProperties;
import com.skrshop.oauthcenter.security.login.LoginManager;
import com.skrshop.oauthcenter.security.userdetail.UserDetailsRepository;
import com.skrshop.oauthcenter.security.validate.code.ValidateCodeFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


/**
 * Security 核心配置类
 * 开启控制权限至Controller
 *
 * @author yh
 */
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AuthenticationSuccessHandler successHandler;

    @Resource
    private AuthenticationFailureHandler failHandler;

    @Resource
    private SkrShopAuthorityCenterProperties skrShopAuthorityCenterProperties;

    @Resource
    private ValidateCodeFilter validateCodeFilter;


    @Bean
    public UserDetailsRepository userDetailsRepository(ObjectProvider<LoginManager> loginManagers) {
        return new UserDetailsRepository(loginManagers.getIfAvailable());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) //比登录Filter先执行 自定义登录
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(successHandler)
                .failureHandler(failHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",
                        skrShopAuthorityCenterProperties.getSecurity().getLoginpage(),
                        "/code/image")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                .disable()

        ;
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
//                .authorizeRequests();
//
//        // 除配置文件忽略路径其它所有请求都需经过认证和授权
////        for(String url:ignoredUrlsProperties.getUrls()){
////            registry.antMatchers(url).permitAll();
////        }
//
//        registry.and()
//                // 表单登录方式
//                .formLogin()
//                .loginPage("/authentication/require")//登录 页面而并不是接口，对于前后分离模式需要我们进行改造 默认为 /login。
//                // 登录请求url
//                .loginProcessingUrl("/authentication/form")//实际表单向后台提交用户信息的 Action，再由过滤器UsernamePasswordAuthenticationFilter 拦截处理，该 Action 其实不会处理任何逻辑。
//                .permitAll()//form 表单登录是否放开
////                .failureUrl()//登录失败后会重定向到此路径， 一般前后分离不会使用它。
////                .failureForwardUrl()//登录失败会转发到此， 一般前后分离用到它。 可定义一个 Controller （控制器）来处理返回值,但是要注意 RequestMethod。
//                // 成功处理类
//                .successHandler(successHandler)//自定义认证成功处理器，可替代上面所有的 success 方式
//                // 失败
//                .failureHandler(failHandler)
//                .and()
//                .authorizeRequests()
//                .antMatchers(skrShopAuthorityCenterProperties.getSecurity().getLoginpage())
//                .permitAll()
//                .and()
//                // 允许网页iframe
////                .headers().frameOptions().disable()
////                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .authorizeRequests()
//                // 任何请求
//                .anyRequest()
//                // 需要身份认证
//                .authenticated()
//                .and()
//                // 允许跨域
//                .cors().and()
//                // 关闭跨站请求防护
//                .csrf().disable()
//                // 前后端分离采用JWT 不需要session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
////        .addFilterBefore(preLoginFilter, UsernamePasswordAuthenticationFilter.class) //比登录Filter先执行 自定义登录
//        // 自定义权限拒绝处理类
////                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
//        // .authenticationEntryPoint()authenticationEntryPoint 处理未授权认证失败
////                .and()
//        // 添加自定义权限过滤器
////                .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
//        // 图形验证码过滤器
////                .addFilterBefore(imageValidateFilter, UsernamePasswordAuthenticationFilter.class)
//        // 添加JWT过滤器 除已配置的其它请求都需经过此过滤器
////                .addFilter(new JWTAuthenticationFilter(authenticationManager(), ignoredUrlsProperties, tokenProperties, redisTemplate, securityUtil))
//        ;
    }
}