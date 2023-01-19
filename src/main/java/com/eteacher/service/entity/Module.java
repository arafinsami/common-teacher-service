package com.eteacher.service.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "IEIMS_MODULE")
public class Module implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MODULE_ID")
    private Long id;

    @Column(name = "MODULE_NAME")
    private String moduleName;

    private String moduleTitle;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "IEIMS_MODULE_GROUP",
            joinColumns = @JoinColumn(name = "MODULE_ID"),
            inverseJoinColumns = @JoinColumn(name = "GROUP_ID")
    )
    private Set<Group> groups;
}
