/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author bayu
 */
public class Indeks extends JFrame{
    JLabel gambar1,gambar2,judul;
    JButton menu1,menu2;
    
    public Indeks(){
    setTitle("PENDATAAN BANSOS 2020");
    getContentPane().setBackground(Color.white);
       setSize(450,300);//ukuran
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLayout(null);
       
       judul     = new JLabel("MENU");
       judul.setFont(new Font("Arial Black", Font.PLAIN, 16));
       add(judul);
       judul.setBounds(190, 20, 100, 30);
       
       gambar1   = new JLabel(new ImageIcon(getClass().getResource("/image/cekPNG.jpg")));//icon
       add(gambar1);
      
       gambar1.setBounds(70, 70, 100, 100);
       
       gambar2   = new JLabel(new ImageIcon(getClass().getResource("/image/orgPNG.jpg")));//icon
       add(gambar2);
    
       gambar2.setBounds(260, 70, 100, 100);
       
       menu1     = new JButton("CEK");
       menu1.setFont(new Font("Arial Black", Font.PLAIN, 12));
       menu1.setBackground(Color.BLACK);
       menu1.setForeground(Color.WHITE);
       add(menu1);
       menu1.setBounds(60, 180, 120, 30);
       menu1.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent a) {
                    CekData cd  = new CekData();
                    cd.setLocationRelativeTo(null);
                    setVisible(true);
                    dispose();
       }});
       
       menu2     = new JButton("ADMIN");
       menu2.setFont(new Font("Arial Black", Font.PLAIN, 12));
       menu2.setBackground(Color.BLACK);
       menu2.setForeground(Color.WHITE);
       add(menu2); 
       menu2.setBounds(250, 180, 120, 30);
       menu2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
                  Login L = new Login();
                  L.setLocationRelativeTo(null);
                  setVisible(true);
                  dispose();
        }});
        
    }
}
