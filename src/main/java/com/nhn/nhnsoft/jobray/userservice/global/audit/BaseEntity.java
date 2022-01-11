package com.nhn.nhnsoft.jobray.userservice.global.audit;

import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity extends BaseTimeEntity {

    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    protected void initBaseAudit(String id){
        this.createdBy = id;
        this.updatedBy = id;
    }

    protected void updateBaseAudit(String id){
        this.updatedBy = id;
    }
}
