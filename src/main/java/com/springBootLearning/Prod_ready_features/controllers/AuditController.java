package com.springBootLearning.Prod_ready_features.controllers;

import com.springBootLearning.Prod_ready_features.entities.PostEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/audit")
public class AuditController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/posts/{postId}")
    List<PostEntity> getAllPostRevisions(@PathVariable Long postId) {

        //we can write queries for the posts_aud table
        //but sometimes db admins are not able to write these queries so we use AuditReader
        //using this we can read all the revisions for the particular audit

        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager()) ;

        List<Number> revisions = reader.getRevisions(PostEntity.class , postId);

        return revisions
                .stream()
                .map(revisionNumber -> reader.find(PostEntity.class , postId , revisionNumber))
                .collect(Collectors.toList());

    }

}
