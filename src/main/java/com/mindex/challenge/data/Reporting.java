package com.mindex.challenge.data;

public class Reporting {

    private int numberOfReports;
    private Employee employee;

    public Reporting(Employee employee) {
        this.numberOfReports = numberOfReports;
        this.employee = employee;
    }


    public Reporting() {
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
