package com.example;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("=== TEST 1: seller findbyId ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
        System.out.println("\n=== TEST 2: seller findbyDepartment ===");
        Department dep = new Department(2, null);
        List<Seller> sellerList = sellerDao.findByDepartment(dep);
        for(Seller obj: sellerList)
        {
            System.out.println(obj);
        }
        System.out.println("\n=== TEST 3: seller findAll ===");
        sellerList = sellerDao.findAll();
        for(Seller obj: sellerList)
        {
            System.out.println(obj);
        }
       
    }
}
