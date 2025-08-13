package com.semsoko.webstartbackend.shared.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    /**
     * Konfiguriert eine RedisTemplate-Bean mit String-Schlüsseln und -Werten.
     * Ideal für Anwendungen, die JSON serialisierte Daten als Strings speichern (z. B. RefreshTokenMetadata).
     *
     * @param factory Verbindungs-Factory für Redis (von Spring Boot bereitgestellt)
     * @return Konfiguriertes RedisTemplate<String, String>
     */
    @Bean
    public RedisTemplate<String, String>redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        /**
         * Wichtig fuer lesbare Speicherung
         */
        template.setDefaultSerializer(new StringRedisSerializer());

        return template;
    }
}
