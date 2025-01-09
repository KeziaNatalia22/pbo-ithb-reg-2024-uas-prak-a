package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Controller.DBController;
import Model.FrameLogin;
import Model.SingletonManager;
import Model.Customer;

public class HistoryPaket {
    FrameLogin frame;
    JPanel panel;

    public HistoryPaket() {
        HistoryPaket();
    }

    public void HistoryPaket() {
        SingletonManager login = SingletonManager.getInstance();
        Customer user = login.getUser();
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 800;  
        final int FRAME_HEIGHT = 500;  // Menyesuaikan tinggi frame agar cukup untuk tabel dan tombol

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        frame = new FrameLogin("History Paket");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255)); 

        String[] columns = {"Transaction_id", "package_type", "package_weight", "total_cost", "created_at", "updated_at"};
        String [][] data = DBController.dataHistory(); 
        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, FRAME_WIDTH - 40, FRAME_HEIGHT - 120);  

        JButton butt = new JButton("Detail");
        butt.setBounds(20, FRAME_HEIGHT - 80, 150, 30); 
        butt.addActionListener(e -> {
            if (login.getTransaction() != null) {
                new DetailHistory(login.getTransaction().getId()); 
            } else {
                JOptionPane.showMessageDialog(frame, "gaada", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(scrollPane);  
        panel.add(butt); 
        frame.add(panel);      

        frame.setVisible(true);
    }
}
