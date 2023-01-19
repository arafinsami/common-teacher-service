package com.eteacher.service.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "IEIMS_USER_TYPE")
public class UserType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_TYPE_ID")
    private Long id;

    @Column(name = "USER_TYPE_NAME")
    private String userType;
}
