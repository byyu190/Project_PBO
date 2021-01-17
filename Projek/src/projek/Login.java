/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author bayu
 */

public class Login extends JFrame{
    JLabel user,pass,judul,g1,g2;
    JTextField User;
    JPasswordField Pass;
    JButton llogin;
    
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    public Login(){
     
        //pemanggilan fungsi koneksi database yang sudah kita buat pada class konfig.java
        Konfig DB = new Konfig();
        DB.getKonfig();
        con = DB.koneksi;
        stat = DB.statement;
        
        setTitle("PENDATAAN BANSOS 2020");
        getContentPane().setBackground(Color.WHITE);//warna bg
        setSize(400,270);//ukuran
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        judul   = new JLabel("LOGIN");
        judul.setForeground(Color.black);
        judul.setFont(new Font("Arial Black", Font.PLAIN, 16));
        g1      = new JLabel(new ImageIcon(getClass().getResource("/image/bo.png")));//icon
        g2      = new JLabel(new ImageIcon(getClass().getResource("/image/pa.png")));//icon
        user    = new JLabel("Username  ");
        user.setForeground(Color.black);
        user.setFont(new Font("Arial Black", Font.PLAIN, 13));
        pass    = new JLabel("Password  ");
        pass.setForeground(Color.black);
        pass.setFont(new Font("Arial Black", Font.PLAIN, 13));
        User    = new JTextField();
        Pass    = new JPasswordField();

        llogin     = new JButton("Login");
        llogin.setFont(new Font("Arial Black", Font.PLAIN, 12));
        llogin.setBackground(Color.BLACK);
        llogin.setForeground(Color.WHITE);
     
       
            llogin.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
             try {
                sql = "SELECT * FROM login WHERE user='"+User.getText()+"' AND password='"+Pass.getText()+"'";
                stat = con.createStatement();
                rs = stat.executeQuery(sql);
                int baris=0;
                while(rs.next()){
                    baris = rs.getRow();
                }
                if(baris==1)
                {
                    DataBansos databansos = new DataBansos();
                    databansos.setLocationRelativeTo(null);
                    setVisible(true);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(null,"User / Pass Salah");
                }
                
            }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.out.println("Koneksi Gagal");
                } 
            }
          
      });
        //@Override
        //public void actionPerformed(ActionEvent ae) {
    //        LayoutBooking P  = new LayoutBooking();
      //      P.setLocationRelativeTo(null);
        //    setVisible(true);
          //  dispose();
        //}});
        
        add(judul);
        add(g1);
        add(g2);
        add(user);
        add(pass);
        add(User);
        add(Pass);
        add(llogin);
        
        judul.setBounds(180, 10, 200, 30);
        g1.setBounds(0, 45, 100, 50);
       // user.setBounds(80, 55, 100, 30);
        User.setBounds(80, 55, 270, 35);
        g2.setBounds(0, 110, 100, 50);
        //pass.setBounds(80, 120, 100, 30);
        Pass.setBounds(80, 115, 270, 35);
        llogin.setBounds(150, 170, 120, 30);

    }
}
