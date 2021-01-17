/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author bayu
 */
public class DataBansos extends JFrame {
  
    JLabel nama,alamat,nkk,in,out,gambar2,judul;
    JTextField Nama,ALAMAT,NKK,IN,OUT,CARI;
    
    JButton submit,back,pembatalan,update,cari;
    String[][] data2 = new String[450][5];
    String[][] data   = new String[450][5]; //menampilkan tabel baris 0 isi kolong 6
    String [] kolom = {"NKK","Nama","Alamat","Pemasukan","Pengeluaran"};//kolom
    JTable tabel = new JTable();
    JScrollPane s1;
    DefaultTableModel tableModel;
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    
    public DataBansos(){
        
        Konfig DB = new Konfig();
        DB.getKonfig();
        con = DB.koneksi;
        stat = DB.statement;
        
    setTitle("PENDATAAN BANSOS 2020");
    getContentPane().setBackground(Color.white);//warna bg
    setSize(700,700);//ukuran
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    gambar2 = new JLabel(new ImageIcon(getClass().getResource("/image/admin.png")));
    judul   = new JLabel("PENDATAAN BANSOS 2020");
    judul.setFont(new Font("Arial Black", Font.PLAIN, 26));
    
    nama    = new JLabel("Nama");
    Nama    = new JTextField();
   
    
    nkk    = new JLabel("NKK");
    NKK    = new JTextField();
    
    
    alamat = new JLabel("Alamat");
    ALAMAT = new JTextField();
    
    
    in= new JLabel("Penghasilan Bulanan");
    IN   = new JTextField();
    
    out = new JLabel("Pengeluaran Bulanan");
    OUT = new JTextField();
    
    tabel   = new JTable(data, kolom);
    s1      = new JScrollPane(tabel);//scroll dlm tabel 
    
    CARI = new JTextField();
    
    cari = new JButton("Cari");
    cari.setBackground(Color.DARK_GRAY);
    cari.setForeground(Color.WHITE);
    cari.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
    
    
    submit  = new JButton("Tambah");
    submit.setBackground(Color.DARK_GRAY);
    submit.setForeground(Color.WHITE);
    submit.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));

   
    back    = new JButton("Kembali");
    back.setBackground(Color.ORANGE);
    back.setForeground(Color.WHITE);
    back.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
    
    
    update     = new JButton("Update");
    update.setBackground(Color.LIGHT_GRAY);
    update.setForeground(Color.WHITE);
    update.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
    
    pembatalan = new JButton("Hapus");
    pembatalan.setBackground(Color.PINK);
    pembatalan.setForeground(Color.WHITE);
    pembatalan.setFont(new Font("Arial", Font.CENTER_BASELINE, 15));
    
    setLayout(null);
        //add(gambar);
        add(gambar2);
        add(judul);
        add(nama);
        add(Nama);
        add(in);
        add(IN);
        add(out);
        add(OUT);
        add(nkk);
        add(NKK);
        add(alamat);
        add(ALAMAT);
        add(submit);
        add(s1);
        add(back);
        add(pembatalan);
        add(update);
        add(cari);
        add(CARI);
        
        gambar2.setBounds(420, 60, 150, 150);
        judul.setBounds(60, 20, 500,23);
        nama.setBounds(20, 70, 150, 20);
        Nama.setBounds(170, 70, 170, 20);
        nkk.setBounds(20, 100, 150, 20);
        NKK.setBounds(170, 100, 170, 20);
        alamat.setBounds(20, 130, 150, 20);
        ALAMAT.setBounds(170, 130, 170, 20);
        in.setBounds(20, 160, 150, 20);
        IN.setBounds(170, 160, 170, 20);
        out.setBounds(20, 190, 150, 20);
        OUT.setBounds(170, 190, 170, 20);
        submit.setBounds(20, 230, 120, 30);
        s1.setBounds(20, 270, 640, 350);
        s1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        update.setBounds(190, 230, 120, 30);
        pembatalan.setBounds(360, 230, 120, 30);
        back.setBounds(540, 230, 120, 30);
        cari.setBounds(590,20,75,30);
        CARI.setBounds(490,20,100,30);
      
         try {
                sql = "SELECT * FROM data";
                stat = con.createStatement();
                rs = stat.executeQuery(sql);
                int p = 0;
                while (rs.next()) {
                    data[p][0] = rs.getString("nokk"); //nama nokk alamatnya harus urut sesuai database
                    data[p][1] = rs.getString("nama");
                    data[p][2] = rs.getString("alamat");
                    data[p][3] = rs.getString("penghasilan");
                    data[p][4] = rs.getString("pengeluaran");
                    p++; //biar kalo ada data tambah dia otomatis nambah dibawah data pertama
                    
                }
                  stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
                }
        
        
        back.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            DataBansos dtb = new DataBansos();
            dtb.setLocationRelativeTo(null);
            setVisible(true);
            dispose();
        }});
        //Database db = new Database();
        
        
        submit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try{
            sql = "INSERT INTO data (penghasilan,nama,alamat,nokk,pengeluaran) VALUES ('"+IN.getText()+"','"+Nama.getText()+"','"+ALAMAT.getText()+"','"+NKK.getText()+"','"+OUT.getText()+"')";
            stat = con.createStatement();
            java.sql.PreparedStatement pstm=con.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Proses Simpan Data Berhasil..");
            dispose();
            DataBansos dtb = new DataBansos();
            }catch (HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());  
            }
        }});
       

        update.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try{
            sql = "UPDATE data SET nama='"+Nama.getText()+"',alamat='"+ ALAMAT.getText()+"',penghasilan='"+IN.getText()+"',pengeluaran='"+OUT.getText()+"' WHERE nokk='"+NKK.getText()+"'";
            stat = con.createStatement();
            java.sql.PreparedStatement pstm=con.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Proses Update Data Berhasil..");
            dispose();
            DataBansos dtb = new DataBansos();
            }catch (HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());  
            }
        }});
        
        cari.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
             try {
                sql = "SELECT * FROM data where nokk like '"+CARI.getText()+"'";
                stat = con.createStatement();
                rs = stat.executeQuery(sql);
                int p = 0;
                while (rs.next()) {
                    data2[p][0] = rs.getString("nokk"); //nama nim alamatnya harus urut sesuai database
                    data2[p][1] = rs.getString("nama");
                    data2[p][2] = rs.getString("alamat");
                    data2[p][3] = rs.getString("penghasilan");
                    data2[p][4] = rs.getString("pengeluaran");
                    p++; //biar kalo ada data tambah dia otomatis nambah dibawah data pertama
                  
                }
                  tabel.setModel (new DefaultTableModel (data2,kolom));
                  stat.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan!", "Hasil", JOptionPane.ERROR_MESSAGE);
                }
        }});
      
        tabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
               try{
                int baris = tabel.rowAtPoint(e.getPoint());
                String nkk = tabel.getValueAt(baris, 0).toString();
                NKK.setText(nkk);
                String namaa = tabel.getValueAt(baris, 1).toString();
                Nama.setText(namaa);
                String alaamat = tabel.getValueAt(baris, 2).toString();
                ALAMAT.setText(alaamat);
                String inn = tabel.getValueAt(baris, 3).toString();
                IN.setText(inn);
                String outt = tabel.getValueAt(baris, 4).toString();
                OUT.setText(outt);
               
                }catch (Exception ea){
                    JOptionPane.showMessageDialog(null,"Tidak ada data" + ea.getMessage());

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
               /// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            
        });
        
        
        pembatalan.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            try{
            sql = "DELETE FROM data WHERE nokk='"+NKK.getText()+"'";
            stat = con.createStatement();
            java.sql.PreparedStatement pstm=con.prepareStatement(sql);
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Proses Hapus Data Berhasil..");
            dispose();
            DataBansos dtb = new DataBansos();
            }catch (HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());  
            }
        }});
      
        
       
           
                
            
    
        
        
    }
 
}


