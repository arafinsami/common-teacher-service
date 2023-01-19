package com.eteacher.service.entity;


import com.eteacher.service.enums.RecordStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final Long serialVersionUID = 1L;

    @JsonIgnore
    @Column(name = "RECORD_ID")
    private Long recordId;//auto generated unique id

    @Version
    @JsonIgnore
    @Column(name = "RECORD_VERSION")
    public Integer recordVersion;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "RECORD_STATUS")
    private RecordStatus recordStatus;

    @Type(type = "uuid-char")
    @JoinColumn(name = "CREATOR", updatable = false)
    private UUID createdBy;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    protected Date createdAt;

    @Type(type = "uuid-char")
    @JoinColumn(name = "UPDATOR")
    private UUID updatedBy;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    protected Date updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }
}