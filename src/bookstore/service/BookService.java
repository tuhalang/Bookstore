/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.service;

import bookstore.connect.GetConnectDB;
import bookstore.model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class BookService {

    public Vector<Book> SelectAll() {
        String sql = "SELECT * FROM book";
        Vector<Book> listBook = null;
        try {
            listBook = new Vector<Book>();
            Connection conn = GetConnectDB.getConnectMSAccess("database/bookstore.accdb");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next() == true) {
                Book book = new Book();
                book.setId(result.getInt("id"));
                book.setName(result.getString("name"));
                book.setPrice(result.getInt("price"));
                book.setAuthor(result.getString("author"));
                book.setIdType(result.getInt("idtype"));
                String dateInString = result.getString("publicationdate");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    book.setPublicationDate(sdf.parse(dateInString));
                } catch (ParseException ex) {
                    Logger.getLogger(BookService.class.getName()).log(Level.SEVERE, null, ex);
                }
                book.setAmount(result.getInt("amount"));
                listBook.add((Book) book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return listBook;
    }

    public void Delete() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean Add(String name, int price, int idtype, String author, String dateInString, int amount) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            String sql = "INSERT INTO book( id, name, price, idtype, author, publicationdate, amount ) VALUES(?,?,?,?,?,?,?);";
            Connection conn = GetConnectDB.getConnectMSAccess("database/bookstore.accdb");
            PreparedStatement preStatement = conn.prepareStatement(sql);
            preStatement.setInt(1, 1);
            preStatement.setString(2, name);
            preStatement.setInt(3, price);
            preStatement.setInt(4, idtype);
            preStatement.setString(5, author);
            preStatement.setString(6, dateInString);
            preStatement.setInt(7, amount);
            return preStatement.executeUpdate() >= 1; 

        } catch (SQLException ex) {
            //Logger.getLogger(AddBook.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void Update() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}