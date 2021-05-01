package model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.Db;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{

    private Connection conn;
    

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    

    @Override
    public void insert(Seller obj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Seller obj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                "SELECT seller.*, department.Name as DepName FROM seller "
                + "INNER JOIN department ON seller.DepartmentId = department.Id "
                + "WHERE seller.Id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Department dep = instantiateDepartment(rs);
                Seller seller = instantiateSeller(rs, dep);
                return seller;
            }
            return null; 
         
        }
        catch (SQLException e) {
           throw new DbException(e.getMessage());
        }
        finally {
            Db.closeStatement(st);
            Db.closeResultSet(rs);
                        
        }
        
        
        
    }

    @Override
    public List<Seller> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) {
        
        Seller seller = new Seller();
        try {
            
            seller.setId(rs.getInt("Id"));
            seller.setName(rs.getString("Name"));
            seller.setEmail(rs.getString("Email"));
            seller.setBaseSalary(rs.getDouble("BaseSalary"));
            seller.setBirthDate(rs.getDate("BirthDate"));
            seller.setDepartment(dep);
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return seller;
    }
    
    private Department instantiateDepartment(ResultSet rs){
        Department dep = new Department();
        try {
            dep.setId(rs.getInt("DepartmentId"));
            dep.setName(rs.getString("DepName"));
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return dep;
    }



    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
		ResultSet rs = null;
		List<Seller> sellerList = new ArrayList<>();
		try {
			st = conn.prepareStatement(
				"SELECT seller.*, department.Name as DepName "
				+ "FROM seller JOIN department ON seller.DepartmentId = department.Id "
				+ "WHERE seller.DepartmentId = ? "
				+ "ORDER by Name"
			);
			st.setInt(1, department.getId());
			rs = st.executeQuery();

			
			Map<Integer, Department> depMap = new HashMap<>();
			while (rs.next()){
				Department dep = depMap.get(rs.getInt("DepartmentId"));
				if(dep == null){
					dep = instantiateDepartment(rs);
					depMap.put(rs.getInt("DepartmentId"), dep);
				}

			    Seller seller = instantiateSeller(rs, dep);
				sellerList.add(seller);
			}
			return sellerList;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closeStatement(st);
            Db.closeResultSet(rs);
			
		}
    }

}
