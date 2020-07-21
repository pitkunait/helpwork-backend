package craft.beer.core.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(
        private val jwtTokenProvider: JwtTokenProvider
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {

        // Disable CSRF (cross site request forgery)
        http.csrf().disable()
        http.cors()
        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        // Entry points
        http.authorizeRequests()
                .antMatchers("/auth/signin").permitAll()
                .antMatchers("/auth/signup").permitAll()
                .antMatchers("/auth/refresh-token").permitAll()
                .anyRequest().authenticated()

        // Apply JWT
        http.apply(JwtTokenFilterConfigurer(jwtTokenProvider))

        // Optional, if you want to test the API from a browser
        http.httpBasic()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(12)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}