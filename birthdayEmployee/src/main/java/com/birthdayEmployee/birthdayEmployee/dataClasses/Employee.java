package com.birthdayEmployee.birthdayEmployee.dataClasses;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String fio;
    Date birthDay;
    //@ManyToOne
    int idDepartment;

    public Employee() {
    }

    public Employee(String fio, Date birthDay, int idDepartment) {
        this.fio = fio;
        this.birthDay = birthDay;
        this.idDepartment = idDepartment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public int getId_department() {
        return idDepartment;
    }

    public void setId_department(int id_department) {
        this.idDepartment = id_department;
    }

    @Override
    public String toString(){
        return "{" +
                "\"id\":" + id +
                ",\"fio\":\"" + fio + "\"" +
                ",\"birthDay\":" + birthDay +
                ",\"idDepartment\":" + idDepartment +
                "}";
    }
}
