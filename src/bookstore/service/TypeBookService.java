/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.service;

import bookstore.connect.GetConnectDB;
import bookstore.model.TypeBook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class TypeBookService{

    
    public Vector<TypeBook> SelectAll() {
        String sql = "SELECT * FROM typebook";
        Vector<TypeBook> listTypeBook = null;
        try{
            listTypeBook = new Vector<TypeBook>();
            Connection conn = GetConnectDB.getConnectMSAccess("database/bookstore.accdb");
            Statement statement=conn.createStatement();
            ResultSet result=statement.executeQuery(sql);
            while(result.next()==true){
               TypeBook typeBook = new TypeBook();
               typeBook.setId(result.getInt("id"));
               typeBook.setName(result.getString("name"));
               listTypeBook.add(typeBook);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return listTypeBook;
    }

    
    public boolean Delete(int id) {
        try {
            String sql = "DELETE FROM typebook WHERE id=?;";
            Connection conn = GetConnectDB.getConnectMSAccess("database/bookstore.accdb");
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setInt(1, id);
            return preStatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TypeBookService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean Update(int id, String newName) {
        PreparedStatement preStatement = null;
        try {
            String sql = "UPDATE typebook SET name=? WHERE id=?;";
            Connection conn = GetConnectDB.getConnectMSAccess("database/bookstore.accdb");
            preStatement = conn.prepareStatement(sql);
            preStatement.setInt(1, id);
            preStatement.setString(2, newName);
            return preStatement.executeUpdate() >= 1;
        } catch (SQLException ex) {
            Logger.getLogger(TypeBookService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    public boolean Add(String name) {
        try {
            String sql = "INSERT INTO typebook VALUES(?,?);";
            Connection conn = GetConnectDB.getConnectMSAccess("database/bookstore.accdb");
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setInt(1, 1);
            preStatement.setString(2, name);
            return preStatement.executeUpdate() >= 1;
        } catch (SQLException ex) {
            Logger.getLogger(TypeBookService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static String codeToName(int id){
        String sql = "SELECT * FROM typebook WHERE id=?";
        String name="";
        try{
            PreparedStatement preStatement = GetConnectDB.getConnectMSAccess("database/bookstore.accdb").prepareStatement(sql);
            preStatement.setInt(1, id);
            ResultSet result=preStatement.executeQuery();
            while(result.next()){
                name = result.getString("name");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return name;
    }
    
    public static int nameToCode(String name){
        String sql = "SELECT * FROM typebook WHERE name=?";
        int id = 0;
        try{
            PreparedStatement preStatement = GetConnectDB.getConnectMSAccess("database/bookstore.accdb").prepareStatement(sql);
            preStatement.setString(1, name);
            ResultSet result=preStatement.executeQuery();
            while(result.next()){
                id = result.getInt("id");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return id;
    }
    
    public static int countBooks(int idType){
        String sql = "SELECT * FROM book WHERE idtype=?";
        int amount = 0;
        try{
            PreparedStatement preStatement = GetConnectDB.getConnectMSAccess("database/bookstore.accdb").prepareStatement(sql);
            preStatement.setInt(1, idType);
            ResultSet result = preStatement.executeQuery();
            while(result.next()){
                amount++;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return amount;
    }
}
