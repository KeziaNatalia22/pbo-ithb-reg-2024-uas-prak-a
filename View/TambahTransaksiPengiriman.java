package View;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import Controller.DBController;
import Model.FrameLogin;
import Model.SingletonManager;
import Model.Customer;

public class TambahTransaksiPengiriman {
    FrameLogin frame;
    JPanel panel;

    public TambahTransaksiPengiriman() {
        Add();
    }

    public void Add() {
        SingletonManager login = SingletonManager.getInstance();
        Customer user =  login.getUser();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 400;
        final int FRAME_HEIGHT = 720;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        frame = new FrameLogin("Tambah Transaksi Pengiriman");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT - 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255)); 

        JLabel usnL = new JLabel("Nama");
        usnL.setBounds(50, 60, 150, 20);
        panel.add(usnL);

        JTextField username = new JTextField();
        username.setBounds(50, 85, 300, 30);
        username.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(username);

        JLabel alamatL = new JLabel("Alamat");
        alamatL.setBounds(50, 125, 150, 20);
        panel.add(alamatL);

        JTextField alamat = new JTextField();
        alamat.setBounds(50, 150, 300, 30);
        alamat.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(alamat);

        JLabel telpL = new JLabel("No Telp");
        telpL.setBounds(50, 190, 150, 20);
        panel.add(telpL);

        JTextField telpf = new JTextField();
        telpf.setBounds(50, 215, 300, 30);
        telpf.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(telpf);

        JLabel beratl = new JLabel("Berat");
        beratl.setBounds(50, 255, 150, 20);
        panel.add(beratl);

        JTextField berat = new JTextField();
        berat.setBounds(50, 280, 300, 30);
        berat.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(berat);

        String [] kategori = DBController.kategori();
        
        JComboBox category = new JComboBox(kategori);
        category.setBounds(50, 320, 300, 30);
        category.setMaximumSize(new Dimension(150, 30));
        panel.add(category);

        JButton save = new JButton("Simpan");
        save.setBounds(50, 405, 150, 30);
        panel.add(save);

        JButton backButton = new JButton("Back");
        backButton.setBounds(210, 405, 150, 30);
        panel.add(backButton);
        
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username.getText().isEmpty() || alamat.getText().isEmpty() || telpf.getText().isEmpty() || berat.getText().isEmpty() || category.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(frame, "Isi semua field", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    int beratV = Integer.parseInt(berat.getText());
                    if (beratV != 0) {
                        DateTimeFormatter format = DateTimeFormatter.BASIC_ISO_DATE;
                        String date = LocalDate.now().format(format);
                        if(DBController.inputTransaksi(user.getId(), category.getSelectedItem().toString(), beratV, beratV, date, username.getText(), alamat.getText(), telpf.getText())){
                            JOptionPane.showMessageDialog(frame, "Berhasil input transaksi", "yeay", JOptionPane.INFORMATION_MESSAGE);
                            new DetailTransaction();
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Gagal", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        backButton.addActionListener(e -> {
            new MenuUtama();
            frame.dispose();
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    
}