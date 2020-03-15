package com.birthdayEmployee.birthdayEmployee.controller;

import com.birthdayEmployee.birthdayEmployee.dataClasses.Department;
import com.birthdayEmployee.birthdayEmployee.dataClasses.Employee;
import com.birthdayEmployee.birthdayEmployee.dboConnection.RepositoryDepartment;
import com.birthdayEmployee.birthdayEmployee.dboConnection.RepositoryEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;

@Controller
public class ControllerList {

    @Autowired
    private RepositoryEmployee repositoryEmployee;

    @Autowired
    private RepositoryDepartment repositoryDepartment;

    @GetMapping("/getEmployee")
    public String getEmployee(
            @RequestParam(name = "day", required = false, defaultValue = "30") int day,
            @RequestParam(name = "department", required = false, defaultValue = "1") int department,
            Map<String, Object> model
    ){

        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, day);

        Date nextDate = new Date(calendar.getTimeInMillis());

        LinkedList<Integer> ourDepartments = getSubDivision(department);

        Iterable<Employee> employees = repositoryEmployee.getEmployeesBirthday(nextDate,ourDepartments);

        model.put("employees", employees);
        return "getEmployee";
    }



    public LinkedList<Integer> getSubDivision(int _department){

        LinkedList<Integer> ourDepartments = new LinkedList();
        ourDepartments.add(_department);

        List<Department> departments = repositoryDepartment.findAllByParent(_department);

        for (Department department : departments){

            if (department.getParent() != 0){
                ourDepartments.addAll(getSubDivision(department.getId()));
            }
        }

        return ourDepartments;
    }
}


