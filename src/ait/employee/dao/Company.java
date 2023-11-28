package ait.employee.dao;

import ait.employee.model.Employee;

public interface Company {
    boolean addEmployee(Employee employee);

    Employee removeEmployee(int id);

    Employee findEmployee(int id);

    double totalSalary();

    int quantity();

     default double avgSalary(){
        return totalSalary()/quantity() ;
     }

    double totalSales();

    void printEmployees();
    Employee[] findEmployeeHoursGreaterThan(int hours);

    Employee[] findEmployeeSalaryRange(int minSalary, int maxSalary);

    Employee[] findEmployeesHoursGreaterThan(int i);

    Employee[] findEmployeesSalaryRange(int i, int i1);
}