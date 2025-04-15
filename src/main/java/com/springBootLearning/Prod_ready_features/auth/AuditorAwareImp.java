package com.springBootLearning.Prod_ready_features.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImp implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        //get security context
        //get authentication
        //get the principle user
        //get the username

        return Optional.of("Lakshay Bansal");
    }
}
