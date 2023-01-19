package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.BankBranch;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankBranchRepository extends JpaRepository<BankBranch, Long> {

    Optional<BankBranch> findByIdAndRecordStatusNot(Long id, RecordStatus status);

    Optional<BankBranch> findByBankBranchNameAndRecordStatusNot(String bankBranchName, RecordStatus status);

    Optional<BankBranch> findByBankBranchNameBnAndRecordStatusNot(String bankBranchNameBn, RecordStatus status);
}
