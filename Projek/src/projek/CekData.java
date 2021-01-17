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
public class CekData extends JFrame{
    JLabel nama,nkk,judul;
    JTextField NAMA,NKK;
    JButton cek,kembali;
    
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    public CekData(){
        
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
        
        judul   = new JLabel("CEK");
        judul.setForeground(Color.black);
        judul.setFont(new Font("Arial Black", Font.PLAIN, 16));
        
        nama    = new JLabel("Nama");
        nama.setForeground(Color.black);
        nama.setFont(new Font("Arial Black", Font.PLAIN, 13));
        nkk    = new JLabel("NKK");
        nkk.setForeground(Color.black);
        nkk.setFont(new Font("Arial Black", Font.PLAIN, 13));
        NAMA    = new JTextField();
        NKK    = new JTextField();

        cek     = new JButton("CEK");
        cek.setFont(new Font("Arial Black", Font.PLAIN, 12));
        cek.setBackground(Color.BLACK);
        cek.setForeground(Color.WHITE);
        
        kembali     = new JButton("KEMBALI");
        kembali.setFont(new Font("Arial Black", Font.PLAIN, 12));
        kembali.setBackground(Color.BLACK);
        kembali.setForeground(Color.WHITE);
        
        cek.addActionListener(new ActionListener() {
            //@Override
            public void actionPerformed(ActionEvent e) {
             try {
                sql = "SELECT * FROM data WHERE nama='"+NAMA.getText()+"' AND nokk='"+NKK.getText()+"'";
                stat = con.createStatement();
                rs = stat.executeQuery(sql);
                int baris=0;
                while(rs.next()){
                    baris = rs.getRow();
                }
                if(baris==1)
                {
                    UIManager um = new UIManager();
                    um.put("OptionPane.messageForeground", Color.green);
                    JOptionPane.showMessageDialog(null,"Selamat, Anda Terdaftar Sebagai Penerima Bansos","Status",JOptionPane.INFORMATION_MESSAGE);
                   
                }else{
                    UIManager um = new UIManager();
                    um.put("OptionPane.messageForeground", Color.red);
                    JOptionPane.showMessageDialog(null,"Maaf, Anda Tidak Terdaftar Sebagai Penerima Bansos","Status",JOptionPane.INFORMATION_MESSAGE);
                }
                
            }catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
          
      });
        
        kembali.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
                  Indeks I = new Indeks();
                  I.setLocationRelativeTo(null);
                  setVisible(true);
                  dispose();
        }});
        
        
        
        
        
        
        
        
        
        
        
        
        
        add(judul);
        add(nama);
        add(nkk);
        add(NAMA);
        add(NKK);
        add(cek);
        add(kembali);
        
        judul.setBounds(180, 10, 200, 30);

        nama.setBounds(30, 55, 100, 30);
        NAMA.setBounds(80, 55, 270, 35);
        nkk.setBounds(30, 120, 100, 30);
        NKK.setBounds(80, 115, 270, 35);
        cek.setBounds(80, 170, 120, 30);
        kembali.setBounds(230, 170, 120, 30);
    }
}
