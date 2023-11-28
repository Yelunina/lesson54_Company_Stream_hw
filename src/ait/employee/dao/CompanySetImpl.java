package ait.employee.dao;

import ait.employee.model.Employee;
import ait.employee.model.SalesManager;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CompanySetImpl implements Company {
    private Set<Employee> employees;
    private int capacity;

    public CompanySetImpl(int capacity) {
        this.capacity = capacity;
        employees = new HashSet<>();
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
        return employees.add(employee);
    }

    // O(n)
    @Override
    public Employee removeEmployee(int id) {
        Employee employee = findEmployee(id);
        employees.remove(employee);
        return employee;
    }

    // O(n)
    @Override
    public Employee findEmployee(int id) {
//        for (Employee employee : employees) {
//            if (employee.getId() == id) {
//                return employee;
//            }
//        }
//        return null;
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // O(n)
    @Override
    public double totalSalary() {
//        double res = 0;
//        for (Employee employee : employees) {
//            res += employee.calcSalary();
//        }
//        return res;


//        return employees.stream()
//                .map(Employee::calcSalary)
//                .reduce(0., (a, b) -> a + b);

        return employees.stream()
                .mapToDouble(e -> e.calcSalary())
//                .map(Employee::calcSalary)
                .sum();
    }

    // O(1)
    @Override
    public int quantity() {
        return employees.size();
    }

    // O(n)
    @Override
    public double totalSales() {
//        double res = 0;
//        for (Employee employee : employees) {
//            if (employee instanceof SalesManager) {
//                SalesManager salesManager = (SalesManager) employee;
//                res += salesManager.getSalesValue();
//            }
//        }
//        return res;


//        return employees.stream()
//                .filter(e -> e instanceof SalesManager)
//                .map(e -> (SalesManager) e)
//                .map(SalesManager::getSalesValue)
//                .reduce(0., (a, b) -> a + b);

        return employees.stream()
                .filter(e -> e instanceof SalesManager)
                .map(e -> (SalesManager) e)
                .mapToDouble(s -> s.getSalesValue())
                .summaryStatistics().getSum();
    }

    // O(n)
    @Override
    public void printEmployees() {
        employees.forEach(e -> System.out.println(e));
    }

    // O(n)
    @Override
    public Employee[] findEmployeeHoursGreaterThan(int hours) {
        return findEmployeesByPredicate(e -> e.getHours() >= hours);
    }

    // O(n)
    @Override
    public Employee[] findEmployeeSalaryRange(int minSalary, int maxSalary) {
        return findEmployeesByPredicate(e -> e.calcSalary() >= minSalary && e.calcSalary() < maxSalary);
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
//        List<Employee> res = new ArrayList<>();
//        for (Employee employee : employees) {
//            if (predicate.test(employee)) {
//                res.add(employee);
//            }
//        }
//        return res.toArray(new Employee[0]);
        return employees.stream()
                .filter(predicate)
                .toArray(Employee[]::new);
    }
}