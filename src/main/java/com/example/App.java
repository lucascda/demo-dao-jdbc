package com.example;

import model.dao.DaoFactory;
import model.dao.SellerDao;
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
        
       
    }
}
