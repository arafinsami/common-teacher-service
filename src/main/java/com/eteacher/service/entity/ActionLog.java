package com.eteacher.service.entity;

import com.eteacher.service.enums.Action;
import com.eteacher.service.enums.ModuleName;
import com.eteacher.service.utils.DateUtils;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ACTION_LOG")
public class ActionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTION_LOG_ID")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED")
    private Date created;

    @Enumerated(EnumType.STRING)
    @Column(name = "MODULE_NAME", length = 30)
    private ModuleName moduleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "ACTION", length = 30)
    private Action action;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "DOCUMENT_ID")
    private Long documentId;

    @Column(name = "COMMENTS")
    private String comments;

    @Lob
    @Column(name = "DATA")
    private byte[] backupData;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    @Column(name = "BROWSER_INFO")
    private String browserInfo;

    @Column(name = "RECORD_AREA_ID")
    private Integer recordAreaId;

    public String getCreatedStr() {
        return DateUtils.format(this.created, DateUtils.DATE_TIME_FORMAT);
    }


}

