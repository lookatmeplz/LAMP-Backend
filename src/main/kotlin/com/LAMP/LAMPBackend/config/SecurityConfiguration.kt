package com.LAMP.LAMPBackend.config

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class SecurityConfiguration(private val jwtTokenProvider: JwtTokenProvider) : WebSecurityConfigurerAdapter() {
    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity) {
        http.httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/*/signin", "/*/signup").permitAll()
//                .antMatchers(HttpMethod.GET, "/Wanna permit URL/**").permitAll()
                .anyRequest().hasRole("USER")
                .and()
                .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
    }

//    override fun configure(web: WebSecurity) {
//        web.ignoring().antMatchers("/Wanna ignoring URL/", "/Ex: swagger/")
//    }

}
