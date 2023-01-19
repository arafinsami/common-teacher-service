package com.eteacher.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Data
@Table(name = "IEIMS_USER")
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @JsonIgnore
    @Column(name = "PASSWORD")
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "USER_IMAGE")
    private String image;

    @OneToOne
    @JoinColumn(name = "USER_TYPE_ID", nullable = false)
    private UserType userType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "IEIMS_USER_MODULE",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "MODULE_ID")
    )
    private Set<Module> modules;
}
