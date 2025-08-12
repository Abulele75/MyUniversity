/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myuniversity.newpackage3.gui;


import com.mycompany.myuniversity.newpackage.connection.DBConnection;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */public class AddSubjectGui extends JFrame  implements ActionListener{
    
    private final JPanel panelWest, panelWest1, panelWest2,panelEast, panelSouth, panelCenter;
    private final JLabel lblsubCode, lblSubDescription, lblDelete;
    private final JTextField txtSubCode, txtSubDescription;
    private final JButton btnSave, btnCancel,btnRead, btnDelete;
    private final JComboBox cbDelete;
    private JTable tbl;
    private DefaultTableModel model;
     private JScrollPane scrollPane;
    
    public AddSubjectGui(){
        super("Subject Management");
        panelWest = new JPanel();
        panelWest1 = new JPanel();
        panelWest2 = new JPanel();
        panelEast = new JPanel();
        panelSouth = new JPanel();
        panelCenter = new JPanel();
        lblsubCode = new JLabel("Subject Code");
        lblSubDescription = new  JLabel("Subject Description");
        lblDelete = new JLabel("Select Code to Delete");
        txtSubCode = new JTextField();
        txtSubDescription = new JTextField();
       
        
        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");
        btnRead = new JButton("Read");
        btnDelete = new JButton("Delete");
        
      

        
        
           String[] columnNames = {"Subject Code", "Subject Description"};
        Object[][] data = {
            {"CS101", "Introduction to Computer Science"},
            {"MTH102", "Calculus I"},
            {"PHY103", "Physics Fundamentals"}
        };  

        model = new DefaultTableModel(data, columnNames);
        tbl = new JTable(model);

         scrollPane = new JScrollPane(tbl);
         
         cbDelete
         
           btnSave.addActionListener(new ActionListener(){
               @Override
               public void actionPerformed(ActionEvent e){
                
                if(txtSubCode.getText().isEmpty()|| txtSubDescription.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill in all fields and select Employment Type.");
            
            }else{
                    try(Connection conn = DBConnection.derbyConnection()){
                        String sql = "INSERT INTO Subject(SubjectCode, SubjectDescription) VALUES (?,?)";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        
                        ps.setString(1,txtSubCode.getText());
                        ps.setString(2,txtSubDescription.getText());
                        
                        ps.executeUpdate();
                        
                        JOptionPane.showMessageDialog(null, "Saved Successfully");
                        
                        txtSubCode.setText("");
                        txtSubDescription.setText("");
                    } catch(Exception ex){
                        JOptionPane.showInputDialog(null,"Error saving to database:" + ex. getMessage());
                    }
                    
                    
                        }
                
                
            }
           });
           
           btnRead.addActionListener( new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  try(Connection conn = DBConnection.derbyConnection()){
                      String sql = "SELECT *FROM Subject";
                      PreparedStatement pstmt = conn.prepareStatement(sql);
                      ResultSet rs = pstmt.executeQuery();
                      
                      DefaultTableModel model = (DefaultTableModel)tbl.getModel();
                      model.setRowCount(0);
                      
                      while(rs.next()){
                          String code = rs.getString("subjectCode");
                          String description = rs.getString("subjectDescription");
                          model.addRow(new Object[]{code, description});
                             
                      }
                      
                       
                   }catch(SQLException ex){
                       JOptionPane.showMessageDialog(null, "Error reading from database :" + ex.getMessage());
                   }
                   
               }
           });
                   }
        
    
    
    

        
        public void setGUI(){
            panelWest.setLayout(new GridLayout(2,1));
            panelEast.setLayout(new GridLayout(1,1));
            panelSouth.setLayout(new FlowLayout());
            
            panelWest.add(lblsubCode);
            panelWest.add(txtSubCode);
            panelWest.add(lblSubDescription);
            panelWest.add(txtSubDescription);
            
            panelEast.add(scrollPane);
            panelSouth.add(btnSave);
            panelSouth.add(btnCancel);
            panelSouth.add(btnRead);
            panelSouth.add(btnDelete);
            
            this.setLayout(new BorderLayout());
            this.add(panelWest, BorderLayout.WEST);
             this.add(panelEast, BorderLayout.EAST);
              this.add(panelSouth, BorderLayout.SOUTH);
              
              this.pack();
              this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              this.setVisible(true);
            
            
       

      
        
    }
        
        
    @Override
    public void actionPerformed(ActionEvent e) {
      
    }
    
/*


    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    } */

    
    

public static void main(String[] args) {
    new AddSubjectGui().setGUI();

 }

 }
