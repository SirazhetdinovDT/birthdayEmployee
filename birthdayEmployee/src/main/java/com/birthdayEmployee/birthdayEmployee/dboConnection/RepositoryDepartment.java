package com.birthdayEmployee.birthdayEmployee.dboConnection;

import com.birthdayEmployee.birthdayEmployee.dataClasses.Department;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepositoryDepartment extends CrudRepository<Department, Long> {

    List<Department> findAllByParent(int parent);
}
