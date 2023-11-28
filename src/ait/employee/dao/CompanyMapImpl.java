package ait.employee.dao;

import ait.employee.model.Employee;
import ait.employee.model.SalesManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class CompanyMapImpl implements Company {
    private Map<Integer, Employee> employees;
    private int capacity;

    public CompanyMapImpl(int capacity) {
        this.capacity = capacity;
        employees = new HashMap<>();
    }

    // O(1)
    @Override
    public boolean addEmployee(Employee employee) {
        if (employee == null) {
            throw new RuntimeException("null");
        }
        if (capacity == employees.size()) {
            return false;
        }
        return employees.putIfAbsent(employee.getId(), employee) == null;
    }

    // O(1)
    @Override
    public Employee removeEmployee(int id) {
        return employees.remove(id);
    }

    // O(1)
    @Override
    public Employee findEmployee(int id) {
        return employees.get(id);
    }

    // O(n)
    @Override
    public double totalSalary() {
        double res = 0;
        for (Employee employee : employees.values()) {
            res += employee.calcSalary();
        }
        return res;
    }

    // O(1)
    @Override
    public int quantity() {
        return employees.size();
    }

    // O(n)
    @Override
    public double totalSales() {
        double res = 0;
        for (Employee employee : employees.values()) {
            if (employee instanceof SalesManager) {
                SalesManager salesManager = (SalesManager) employee;
                res += salesManager.getSalesValue();
            }
        }
        return res;
    }

    // O(n)
    @Override
    public void printEmployees() {
        employees.values().forEach(e -> System.out.println(e));
    }

    @Override
    public Employee[] findEmployeeHoursGreaterThan(int hours) {
        return new Employee[0];
    }

    @Override
    public Employee[] findEmployeeSalaryRange(int minSalary, int maxSalary) {
        return new Employee[0];
    }

    // O(n)
    @Override
    public Employee[] findEmployeesHoursGreaterThan(int hours) {
        return findEmployeesByPredicate(e -> e.getHours() >= hours);
    }

    // O(n)
    @Override
    public Employee[] findEmployeesSalaryRange(int minSalary, int maxSalary) {
        return findEmployeesByPredicate(e -> e.calcSalary() >= minSalary && e.calcSalary() < maxSalary);
    }

    private Employee[] findEmployeesByPredicate(Predicate<Employee> predicate) {
        List<Employee> res = new ArrayList<>();
        for (Employee employee : employees.values()) {
            if (predicate.test(employee)) {
                res.add(employee);
            }
        }
        return res.toArray(new Employee[0]);
    }
}