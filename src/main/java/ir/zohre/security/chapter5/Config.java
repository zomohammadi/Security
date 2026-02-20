package ir.zohre.security.chapter5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class Config {
    private final StaticKeyAuthenticationFilter filter;

    public Config(StaticKeyAuthenticationFilter filter) {
        this.filter = filter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.addFilterBefore(
                        new RequestValidationFilter(),
                        BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(filter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(c -> c.anyRequest().permitAll());

        return http.build();
    }

}
