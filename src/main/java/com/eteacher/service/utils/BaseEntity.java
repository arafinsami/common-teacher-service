package com.eteacher.service.utils;

import com.eteacher.service.entity.User;
import com.eteacher.service.enums.RecordStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Column(name = "RECORD_ID")
    private Long recordId;//auto generated unique id

    @Column(name = "RECORD_VERSION")
    private Integer recordVersion;//default one, auto increment for each operation like update

    @Enumerated
    @Column(name = "RECORD_STATUS")
    private RecordStatus recordStatus;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECORD_AREA_ID")
    private RecordArea recordArea;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR")
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT")
    protected Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UPDATOR")
    private User updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    protected Date updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
        this.recordId = Long.parseLong(StringUtils.randomString());
        this.recordVersion = 1;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
        this.recordId = Long.parseLong(StringUtils.randomString());
        this.recordVersion += this.recordVersion;
    }
}
