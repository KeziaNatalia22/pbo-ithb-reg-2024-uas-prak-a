package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import Controller.DBController;
import Model.FrameLogin;
import Model.SingletonManager;
import Model.Customer;

public class DetailHistory {
    FrameLogin frame;
    JPanel panel;

    public DetailHistory(int id){
        DetailHistory(id);
    }

    public void DetailHistory(int id) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        final int FRAME_WIDTH = 800;  
        final int FRAME_HEIGHT = 500;

        int start_x = screenWidth / 2 - (FRAME_WIDTH / 2);
        int start_y = screenHeight / 2 - (FRAME_HEIGHT / 2);

        frame = new FrameLogin("History Paket");
        frame.setBounds(start_x, start_y, FRAME_WIDTH, FRAME_HEIGHT - 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255)); 

        String[] columns = {"Transaction_id", "status", "evidence", "date", "updated_by"};
        String [][] data = DBController.dataHistoryDetail(id);  
        DefaultTableModel tableModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, FRAME_WIDTH - 40, FRAME_HEIGHT - 120);  

        JButton back = new JButton("BALIK");
        back.setBounds(20, FRAME_HEIGHT - 80, 150, 30); 
        back.addActionListener(e -> {
            new HistoryPaket();
        });
        panel.add(table);
        panel.add(back);
        frame.add(panel);
        frame.setVisible(true);
    }
}
