package model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.Db;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao{
    private Connection conn;

    public DepartmentDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Department obj) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "SELECT department.* FROM department "
                + "WHERE Id = ?"

            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()){
                Department dep = new Department();
                dep.setId(id);
                dep.setName(rs.getString("Name"));

                return dep;
            }
            
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            Db.closeStatement(st);
            Db.closeResultSet(rs);
        }
        return null;
        
    }

    @Override
    public List<Department> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
