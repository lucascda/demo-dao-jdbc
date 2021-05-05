package com.example;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class App2 {
    public static void main(String[] args) {
        DepartmentDao depDao = DaoFactory.createDepartmentDao();
        System.out.println("=== TEST 1: department findById ===");
        Department dep = depDao.findById(2);
        System.out.println(dep);
    }
    
}
