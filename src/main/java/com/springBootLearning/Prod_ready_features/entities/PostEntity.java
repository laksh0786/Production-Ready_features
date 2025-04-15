package com.springBootLearning.Prod_ready_features.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "posts")
//Hibernate envers is not used by the companies as it creates the new tables in db which fill very fast if there are very large no of users who are updating the data but we have learned it for our purpose
@Audited //audit all the values inside and to exclude any item use NotAudited
//@EntityListeners(AuditingEntityListener.class)  //applied in auditable base entity
public class PostEntity extends AuditableEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

//    @NotAudited //this will not audit the changes in description
    private String description;

    //instead of this we use auditable base class that is extended by this class
//    @CreatedDate
//    private LocalDateTime createdDate;
//
//    @LastModifiedDate
//    private LocalDateTime modifiedDate;
//
//    @CreatedBy
//    @Column(nullable = false, updatable = false)  //also added by CreatedBy but for safety
//    private String createdBy;
//
//    @LastModifiedBy
//    private String modifiedBy;


    //Using the below methods we can create our own auditable and also can call perform different events in different entities according to the below events

//    @PrePersist  //this method will trigger before saving the data in the table
//    public void onCreate() {
//
//    }
//
//    @PreUpdate  //this method will trigger before updating the data in the table
//    public void onUpdate() {
//
//    }
//
//    @PreRemove ////this method will trigger before removing the data in the table
//    public void onRemove() {
//
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostEntity that = (PostEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }
}
