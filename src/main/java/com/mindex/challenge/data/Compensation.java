package com.mindex.challenge.data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Compensation {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date effectiveDate;
    private String employeeId;
    private int salary;
    public Compensation() {
    }
    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = new Date();
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }


}
