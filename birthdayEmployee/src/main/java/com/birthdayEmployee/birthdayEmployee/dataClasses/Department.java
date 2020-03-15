package com.birthdayEmployee.birthdayEmployee.dataClasses;

import com.birthdayEmployee.birthdayEmployee.dboConnection.RepositoryDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int parent;
    String departmantName;

    public Department() {

    }

    public Department(String _departmantName) {
        this.parent = 0;
        this.departmantName = _departmantName;
    }

    public Department(int _parent, String _departmantName) {
        this.parent = _parent;
        this.departmantName = _departmantName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getDepartmantName() {
        return departmantName;
    }

    public void setDepartmantName(String departmantName) {
        this.departmantName = departmantName;
    }
}
