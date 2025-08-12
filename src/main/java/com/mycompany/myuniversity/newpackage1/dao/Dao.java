/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myuniversity.newpackage1.dao;

import com.mycompany.myuniversity.newpackage.connection.DBConnection;
import com.mycompany.myuniversity.newpackage2.domain.Subject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Dao {
    private Connection con;
    private Statement stmt;
    private PreparedStatement pstmt;
    
    public Dao(){
        try{
            this.con = DBConnection.derbyConnection();
        }
        catch(Exception exception){
            JOptionPane.showMessageDialog(null,exception.getMessage());
        }
    }
    
    public void save(Subject subject){
        int ok;
        String sql = "INSERT INTO Subject values( "+ subject.getSubjectCode() + ", " + subject.getSubjectDescription() + ")";
        
        try{ stmt = this.con.createStatement();
        ok = stmt.executeUpdate(sql);
        }
        catch(SQLException sqlException) {
JOptionPane.showMessageDialog(null, "SQL Error: " +
sqlException.getMessage());
} finally {
try {
if (stmt != null)
stmt.close(); }
catch(Exception exception){
JOptionPane.showMessageDialog(null, exception.getMessage());
}
}
}
    public ArrayList<Subject> getAll()
{
ArrayList<Subject> subjectsList = new ArrayList<>();


try{
String getAllSql = "SELECT SUBJECTCODE FROM SUBJECT";
pstmt = this.con.prepareStatement(getAllSql);
ResultSet rs = pstmt.executeQuery();

        
    
if (rs != null){
while ( rs.next()){
    subjectsList.add(rs.getString("SUBJECTCODE"));
}


{
System.out.println("DB table Record: "
+ rs.getString(1) + " "+rs.getString(2));
subjectsList.add(new Subject(rs.getString(1), rs.getString(2)));
}
rs.close();
}
}
catch(Exception exception){
JOptionPane.showMessageDialog(null, exception.getMessage());
}

    finally {
try {
if (pstmt != null)
pstmt.close();
}
catch(Exception exception){

}
}
return subjectsList;
}
    
}
