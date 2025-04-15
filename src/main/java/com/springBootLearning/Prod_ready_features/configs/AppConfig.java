package com.springBootLearning.Prod_ready_features.configs;

import com.springBootLearning.Prod_ready_features.auth.AuditorAwareImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration

@EnableJpaAuditing(auditorAwareRef = "getAuditorAwareImp" ) //add in any one configuration class for auditing
public class AppConfig {

    @Bean
    AuditorAware<String> getAuditorAwareImp() {
        return new AuditorAwareImp();
    }

}
