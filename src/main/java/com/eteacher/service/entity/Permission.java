package com.eteacher.service.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "IEIMS_PERMISSION")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "PERMISSION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUTHORITY")
    private String authority;

    @Column(name = "AUTHORITY_NAME")
    private String authorityName;

    @Column(name = "BASE_MENU_NAME")
    private String baseMenu;

    @Column(name = "CHILD_MENU_NAME")
    private String childMenu;

    @Column(name = "CHILD_MENU_ORDER")
    private String childOrder;

    @Column(name = "MENU_ROUTE")
    private String menuRoute;
}
