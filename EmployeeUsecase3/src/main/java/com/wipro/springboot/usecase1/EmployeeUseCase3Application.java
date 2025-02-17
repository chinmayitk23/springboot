package com.wipro.springboot.usecase1;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashMap;
import java.util.List;

public class EmployeeUseCase3Application {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Employee.class).buildSessionFactory();
        Session session = factory.openSession();

        // HashMap to assign designations based on role
        HashMap<String, String> roleToDesignation = new HashMap<>();
        roleToDesignation.put("Developer", "Software Engineer");
        roleToDesignation.put("Tester", "QA Engineer");
        roleToDesignation.put("Architect", "Solution Architect");

        // CREATE operation
        Transaction tx = session.beginTransaction();
        Employee emp1 = new Employee();
        emp1.setName("John Doe");
        emp1.setRole("Developer");
        emp1.setDesignation(roleToDesignation.get(emp1.getRole())); // Assign Designation
        emp1.setSalary(75000);
        session.save(emp1);
        tx.commit();
        System.out.println("Employee added: " + emp1);

        // READ operation
        Employee fetchedEmp = session.get(Employee.class, emp1.getId());
        System.out.println("Fetched Employee: " + fetchedEmp);

        // UPDATE operation
        tx = session.beginTransaction();
        fetchedEmp.setSalary(80000);
        session.update(fetchedEmp);
        tx.commit();
        System.out.println("Updated Employee Salary: " + fetchedEmp);

//        // DELETE operation
//        tx = session.beginTransaction();
//        session.delete(fetchedEmp);
//        tx.commit();
//        System.out.println("Deleted Employee with ID: " + fetchedEmp.getId());

        // FETCH ALL Employees
        List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
        for (Employee e : employees) {
            System.out.println("Employee: " + e);
        }

        session.close();
        factory.close();
    }
}

