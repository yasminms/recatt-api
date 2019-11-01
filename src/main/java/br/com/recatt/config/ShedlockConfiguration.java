package br.com.recatt.config;

import java.time.Duration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.ScheduledLockConfiguration;
import net.javacrumbs.shedlock.spring.ScheduledLockConfigurationBuilder;

@EnableScheduling
@Configuration
public class ShedlockConfiguration {

    @Value("${shedlock.pool-size}")
    private Integer poolSize;

    @Value("${shedlock.lock-at-most-for}")
    private Integer lockAtMostFor;

    @Value("${spring.jpa.properties.hibernate.default_schema}")
    private String defaultSchema;

    @Bean
    public LockProvider lockProvider(final DataSource dataSource) {
        return new JdbcTemplateLockProvider(dataSource, String.format("%s.SHEDLOCK", defaultSchema));
    }

    @Bean
    public ScheduledLockConfiguration taskScheduler(final LockProvider lockProvider) {
        return ScheduledLockConfigurationBuilder
            .withLockProvider(lockProvider)
            .withPoolSize(poolSize)
            .withDefaultLockAtMostFor(Duration.ofMinutes(lockAtMostFor))
            .build();
    }
}
