package com.fladerkod.krubb

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.PrintWriter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@EnableWebSecurity
@Configuration
class WebSecurityConfig(val authenticationEntryPoint: MyBasicAuthenticationEntryPoint) : WebSecurityConfigurerAdapter() {


    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication().withUser("jens").password(passwordEncoder().encode("jensjens")).authorities("ROLE_USER")
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)
        http.cors().and().csrf().disable();
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}


@Component
class MyBasicAuthenticationEntryPoint : BasicAuthenticationEntryPoint() {

    override fun afterPropertiesSet() {
        realmName = "jens"
        super.afterPropertiesSet()
    }

    override fun commence(request: HttpServletRequest, response: HttpServletResponse, authEx: AuthenticationException) {
        response.addHeader("WWW-Authenticate", "Basic realm=\"$realmName\"")
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val writer: PrintWriter = response.writer
        writer.println("HTTP Status 401 - " + authEx.message)
    }
}