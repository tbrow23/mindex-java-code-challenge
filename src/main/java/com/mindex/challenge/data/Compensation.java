/**
 * Data class for a Compensation.
 */
package com.mindex.challenge.data;

public class Compensation {
    private String employee;
    private int salary;
    private String effectiveDate;
    
    public Compensation(){
    }

    public String getEmployee() {
        return employee;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
