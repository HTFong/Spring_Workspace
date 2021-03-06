package com.edu.hutech.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass //để class này trở thành entity cha
@EntityListeners(AuditingEntityListener.class) //dùng auditing để setter tất cả các field
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "createdby")
    @CreatedBy
    private String createdBy;

    @Column(name = "createddate")
    @CreatedDate
    private Date createdDate;

    @Column(name = "modifiedby")
    @LastModifiedBy
    private String modifiedBy;

    @Column(name = "modifieddate")
    @LastModifiedDate
    private Date modifiedDate;


    public Long getId() {
        return id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }
}
