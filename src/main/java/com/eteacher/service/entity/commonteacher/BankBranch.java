package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "BANK_BRANCH")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class BankBranch extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANK_BRANCH_ID")
    private Long id;

    @Column(name = "BANK_BRANCH_NAME")
    private String bankBranchName;

    @Column(name = "BANK_BRANCH_NAME_BN")
    private String bankBranchNameBn;

    @Column(name = "BANK_BRANCH_ROUTING_NUMBER")
    private Integer bankBranchRoutingNumber;

    @Column(name = "ADDRESS_LINE_1")
    private String addressLine1;

    @Column(name = "ADDRESS_LINE_2")
    private String addressLine2;

    @Column(name = "ADDRESS_LINE_1_BN")
    private String addressLine1Bn;

    @Column(name = "ADDRESS_LINE_2_BN")
    private String addressLine2Bn;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "URL")
    private String url;

    @Column(name = "COUNTRY_ID")
    private Long countryId;

    @Column(name = "DISTRICT_ID")
    private Long districtId;

    @Column(name = "DIVISION_ID")
    private Long divisionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BANK_ID")
    private Bank bank;
}