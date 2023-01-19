package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.Bank;
import com.eteacher.service.entity.commonteacher.BankBranch;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

  Optional<Bank> findTopByOrderByIdDesc();

  Optional<Bank> findByIdAndRecordStatusNot(Long id, RecordStatus status);

  @Query("select branches from Bank b join b.bankBranches branches where branches.bankBranchName = :bankBranchName and branches.bank= :b")
  Optional<BankBranch> findByBankBranchName(String bankBranchName, Bank b);

  @Query("select branches from Bank b join b.bankBranches branches where branches.id = :bankBranchId")
  Optional<BankBranch> findByBankBranchId(Long bankBranchId);

  Optional<Bank> findByBankName(String name);
}
