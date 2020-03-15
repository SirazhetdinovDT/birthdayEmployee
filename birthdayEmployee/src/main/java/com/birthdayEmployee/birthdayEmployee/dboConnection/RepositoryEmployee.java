package com.birthdayEmployee.birthdayEmployee.dboConnection;

import com.birthdayEmployee.birthdayEmployee.dataClasses.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public interface RepositoryEmployee extends CrudRepository<Employee, Long> {

//    List<Employee> findAll();
//    List<Employee> findAllByBirthDayBetweenAndIdDepartment();

//    @Query("FROM Employee " +
//            " WHERE to_char(birthDay, 'MMDD') BETWEEN '0110' AND '1120'" +
//            " AND idDepartment = ?2")

    @Query("FROM Employee " +
            "WHERE EXTRACT(YEAR FROM age(now(),birthDay)) < EXTRACT(YEAR FROM age(:nextDate, birthDay)) " +
            "AND idDepartment IN (:ourDepartments)")
    Iterable<Employee> getEmployeesBirthday(@Param("nextDate") Date nextDate, @Param("ourDepartments") LinkedList ourDepartments);
}
