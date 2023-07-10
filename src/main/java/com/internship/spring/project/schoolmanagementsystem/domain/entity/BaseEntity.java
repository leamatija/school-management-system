package com.internship.spring.project.schoolmanagementsystem.domain.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<U> {

    @CreatedBy
    public Integer createdBy;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    public Date createdDate;

    @LastModifiedBy
    public Integer lastModifiedBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    public Date lastModifiedDate;
}
