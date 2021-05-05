package com.example;


import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class App2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartmentDao depDao = DaoFactory.createDepartmentDao();
        System.out.println("=== TEST 1: department findById ===");
        Department dep = depDao.findById(2);
        System.out.println(dep);
        System.out.println("=== TEST 2: department findAll ===");
        List<Department> depList = depDao.findAll();
        for(Department item : depList){
            System.out.println(item);
        }
        System.out.println("=== TEST 3: department Update ===");
        dep = depDao.findById(3);
        dep.setName("Furniture");
        depDao.update(dep);
        System.out.println("Update Completed!");
        dep = depDao.findById(3);
        System.out.println(dep);
        System.out.println("\n=== TEST 4: department insert ===");
        Department newDep = new Department(null, "Babies");
        depDao.insert(newDep);
        System.out.println(newDep);
        System.out.println("\n=== TEST 5: department delete ===");
        System.out.println("Enter id for delete test: ");
        int id = sc.nextInt();
        depDao.deleteById(id);
        System.out.println("Delete completed");
        sc.close();
    }
    
}
