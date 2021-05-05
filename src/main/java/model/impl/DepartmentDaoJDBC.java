package model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "INSERT INTO department "
                + "(Name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, obj.getName());
            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                Db.closeResultSet(rs);
            }
            else{
                throw new DbException("Unexpected error! No rows affected!");
            }
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            Db.closeStatement(st);
        }
        
        
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement(
                "UPDATE department "
                + "SET Name = ? WHERE Id = ?"
            );
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            st.executeUpdate();
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            Db.closeStatement(st);
        }
        
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
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Department> depList = new ArrayList<>();
        try{
            st = conn.prepareStatement(
                "SELECT department.* from department "
                + "ORDER BY Name");
            rs = st.executeQuery();
            while(rs.next()){
                Department dep = instantiateDepartment(rs);
                depList.add(dep);
            }
            return depList;            
            
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally{
            Db.closeStatement(st);
            Db.closeResultSet(rs);
        }
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException{
        Department dep = new Department();
        dep.setId(rs.getInt("Id"));
        dep.setName(rs.getString("Name"));
        return dep;
    }
    
}
