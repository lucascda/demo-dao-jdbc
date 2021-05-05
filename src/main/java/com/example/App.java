package com.example;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);

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
        System.out.println("\n=== TEST 4: seller insert ===");
        Seller newSeller = new Seller(null, "Greg", "Greg@gmail.com", new Date(), 4000.0, dep);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());
        System.out.println("\n=== TEST 5: seller update ===");
        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("Update Completed!");
        System.out.println("\n=== TEST 6: seller delete ===");
        System.out.println("Enter id for delete test: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete completed");
        sc.close();
    }
}
