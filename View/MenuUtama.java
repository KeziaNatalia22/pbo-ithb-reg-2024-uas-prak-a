package View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import Model.Customer;
import Model.FrameLogin;
import Model.SingletonManager;

public class MenuUtama {
    FrameLogin frame;
    public MenuUtama() {
        mainMenu();
    }

    public void mainMenu() {
        SingletonManager log = SingletonManager.getInstance();
        Customer customer = log.getUser();
        frame = new FrameLogin("Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());
        JButton login = new JButton("Login");
        login.setEnabled(customer == null); 
        mainPanel.add(login);

        JButton regist = new JButton("Register");
        regist.setEnabled(customer == null); 
        mainPanel.add(regist);

        JButton transaction = new JButton("Tambah Transaksi Pengiriman");
        transaction.setEnabled(customer != null); 
        mainPanel.add(transaction);

        JButton history = new JButton("History Paket");
        history.setEnabled(customer != null); 
        mainPanel.add(history);


        login.addActionListener(e -> {
            new Login();
            frame.dispose();
        });

        regist.addActionListener(e -> {
            new Registrasi();
            frame.dispose();
        });

        transaction.addActionListener(e -> {
            new TambahTransaksiPengiriman();
            frame.dispose();
        });

        history.addActionListener(e -> {
            new HistoryPaket();
            frame.dispose();
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}
