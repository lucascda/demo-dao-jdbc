package model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

}
