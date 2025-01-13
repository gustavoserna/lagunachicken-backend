package com.digitaul.lagunachicken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("https://flotilla.lagunachicken.org");
        config.addAllowedOriginPattern("http://flotilla.lagunachicken.org");
        config.addAllowedOriginPattern("http://192.168.2.35");
        config.addAllowedOriginPattern("https://192.168.2.35");
        config.addAllowedMethod("*");  // Allow all HTTP methods
        config.addAllowedHeader("*");  // Allow all headers
        config.setAllowCredentials(true);  // Enable credentials support
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
