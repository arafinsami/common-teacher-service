package com.eteacher.service.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Table(name = "IEIMS_GROUP")
@Entity
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Long id;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "IEIMS_GROUP_ROLE",
            joinColumns = @JoinColumn(name = "GROUP_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;
}
