package com.springBootLearning.Prod_ready_features.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//@MappedSuperclass is a JPA annotation used on superclasses that are not entities themselves, but whose fields should be inherited by entity subclasses and mapped to the database.
//It allows code reuse of common fields like createdDate, updatedDate, id, etc., across multiple entity classes, without duplicating them.

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Audited //advance auditing using this we can keep track of who audited what and when and updation
public class AuditableEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @CreatedBy
    @Column(nullable = false, updatable = false)  //also added by CreatedBy but for safety
    private String createdBy;

    @LastModifiedBy
    private String modifiedBy;

}
