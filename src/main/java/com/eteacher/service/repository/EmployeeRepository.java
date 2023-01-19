package com.eteacher.service.repository;

import com.eteacher.service.entity.commonteacher.*;
import com.eteacher.service.entity.mpo.EmployeeSalaryIncrement;
import com.eteacher.service.enums.RecordStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByIdAndRecordStatusNot(Long id, RecordStatus recordStatus);

    Optional<Employee> findByEmployeeCodeAndRecordStatusNot(String code, RecordStatus recordStatus);

    Optional<Employee> findByEmployeeLegacyIdAndRecordStatusNot(String employeeLegacyId, RecordStatus recordStatus);

    Optional<Employee> findByNidAndRecordStatusNot(String nid, RecordStatus recordStatus);

    Optional<Employee> findByBirthRegNoAndRecordStatusNot(String birthRegNo, RecordStatus recordStatus);

    Optional<Employee> findByPassportNoAndRecordStatusNot(String passportNo, RecordStatus recordStatus);

    @Query("SELECT DISTINCT e FROM Employee e LEFT JOIN e.contacts c WHERE " +
            "(:institute IS NULL OR e.institute = :institute) AND" +
            "(:search IS NULL OR e.employeeName LIKE %:search% OR e.nid LIKE %:search% OR c.phoneMobile LIKE %:search%)"
    )
    Page<Employee> searchEmployee(String search, Long institute, Pageable pageable);

    @Query("SELECT DISTINCT e FROM Employee e LEFT JOIN e.contacts c WHERE " +
            "( e.institute = :institute) AND" +
            "( e.department.id = :department) AND" +
            "(:search IS NULL OR e.employeeName LIKE %:search% OR e.nid LIKE %:search% OR c.phoneMobile LIKE %:search%)"
    )
    Page<Employee> searchEmployee(String search, Long institute, Long department, Pageable pageable);

    @Query("select skills from Employee e join e.languageSkills skills where skills.language = :languageId and skills.employee= :e")
    Optional<EmployeeLanguageSkill> findByLanguageSkill(Long languageId, Employee e);

    @Query("select skills from Employee e join e.languageSkills skills where skills.id = :languageSkillId")
    Optional<EmployeeLanguageSkill> findByLanguageSkillId(Long languageSkillId);

    @Query("select joining from Employee e join e.employeeJoinings joining where joining.fromDesignation = :fromDesignationId and joining.employee= :e")
    Optional<EmployeeJoining> findByJoiningFromDesignation(Long fromDesignationId, Employee e);

    @Query("select joining from Employee e join e.employeeJoinings joining where joining.toDesignation = :toDesignationId and joining.employee= :e")
    Optional<EmployeeJoining> findByJoiningToDesignation(Long toDesignationId, Employee e);

    @Query("select joining from Employee e join e.employeeJoinings joining where joining.fromInstitute = :fromInstituteId and joining.employee= :e")
    Optional<EmployeeJoining> findByJoiningFromInstitute(Long fromInstituteId, Employee e);

    @Query("select joining from Employee e join e.employeeJoinings joining where joining.toInstitute = :toInstituteId and joining.employee= :e")
    Optional<EmployeeJoining> findByJoiningToInstitute(Long toInstituteId, Employee e);

    @Query("select joining from Employee e join e.employeeJoinings joining where joining.id = :joiningId")
    Optional<EmployeeJoining> findByJoiningId(Long joiningId);

    @Query("select case when count(c)> 0 then true else false end from Employee c where c = :emp")
    boolean existsEmployee(Employee emp);

    @Query("select s from Employee e join e.languageSkills s where s.id = :id")
    Optional<EmployeeLanguageSkill> findLanguageSkill(Long id);

    @Query("select l from Employee e join e.leaveRecords l where l.id = :id")
    Optional<EmployeeLeaveRecord> findLeaveRecord(Long id);

    @Query("select j from Employee e join e.employeeJoinings j where j.id = :id")
    Optional<EmployeeJoining> findJoining(Long id);

    @Query("select a from Employee e join e.attendances a where a.id = :id")
    Optional<EmployeeAttendance> findAttendance(Long id);

    @Query("select i from Employee e join e.salaryIncrements i where i.id = :id")
    Optional<EmployeeSalaryIncrement> findSalaryIncrement(Long id);

    @Query("select b from Employee e join e.bankAccounts b where b.id = :id")
    Optional<EmployeeBankAccount> findBankAccount(Long id);

}

