package com.eteacher.service.entity.commonteacher;

import com.eteacher.service.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "BANK")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Bank extends BaseEntity {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BANK_ID")
    private Long id;

    @Column(name = "BANK_NAME")
    private String bankName;

    @Column(name = "BANK_NAME_BN")
    private String bankNameBn;

    @Column(name = "SWIFTCODE")
    private String swiftCode;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "bank"
    )
    private List<BankBranch> bankBranches;

    public void addBranchs(List<BankBranch> branches) {
        if (bankBranches == null) {
            bankBranches = new ArrayList<>();
        }
        branches.forEach(b -> {
            b.setBank(this);
        });
        bankBranches.addAll(branches);
    }
}
