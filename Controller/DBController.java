package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import Model.Transaction;

import javax.swing.JOptionPane;

import Model.Customer;
import Model.SingletonManager;

public class DBController {
    public static void Login(String phone, String password){
        SingletonManager login = SingletonManager.getInstance();
        String query = "select * from customer where phone = ? and password = ?";
        
        try {
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            st.setString(1, phone);
            st.setString(2, password);
            
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                    Customer user = new Customer(
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getString("phone"));
                user.setId(rs.getInt("id"));
                JOptionPane.showMessageDialog(null, "Login Berhasil", "Login", JOptionPane.DEFAULT_OPTION);
                login.setUser(user);
            }
        
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public static boolean inputDatatoDB(String username, String phoneNum, String address, String password, String email){
        try{
            String query = "INSERT INTO customer (name, password, address, phone, email)" + 
                    "VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement st = DatabaseHandler.connect().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    st.setString(1, username);
                    st.setString(2, password);
                    st.setString(3, address);
                    st.setString(4, phoneNum);
                    st.setString(5, email);

                    st.execute();
        
            JOptionPane.showMessageDialog(null, "Registrasi Berhasil", "Registrasi", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Input Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public static int checkUniqueTelp(String telp){
        String query = "select phone from customer where phone = ?";
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);

            st.setString(1, telp);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return 0;
            }
            else{
                return 1;
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Checking Unique Username", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public static int checkUniqueEmail(String email){
        String query = "select email from customer where email = ?";
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);

            st.setString(1, email);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return 0;
            }
            else{
                return 1;
            }  
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Checking Unique Email", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public static String[] kategori(){
        String query = "select name from category_package";
        String[] kategori = new String[3];
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);

            ResultSet rs = st.executeQuery();

            int i = 0;
            while (rs.next() && i < 3) {
                kategori[i] = rs.getString("name");
                i++;
            }
            return kategori;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Checking Unique Email", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static String[][] dataHistory(){ // asumsi hanya 1 data saja...
        String query = "select * from transaction";
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                String[][] kategori = new String[1][9];
                String id = String.valueOf(rs.getInt("id"));
                kategori[0][0] = id;         
                kategori[0][1] = String.valueOf(rs.getInt("customer_id")); 
                kategori[0][2] = rs.getString("package_type"); 
                kategori[0][3] = String.valueOf(rs.getInt("package_weight"));
                kategori[0][4] = String.valueOf(rs.getInt("total_cost"));  
                kategori[0][5] = rs.getString("created_at");   
                kategori[0][6] = rs.getString("receipt_name"); 
                kategori[0][7] = rs.getString("receipt_address");
                kategori[0][8] = rs.getString("receipt_phone");   
                return kategori;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    public static String[][] dataHistoryDetail(int ids){ // asumsi hanya 1 data saja...
        String query = "select * from shipment_details";
        try{
            PreparedStatement st = DatabaseHandler.connect().prepareStatement(query);
            
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                String[][] kategori = new String[1][5];
                String id = String.valueOf(rs.getInt("transaction_id"));
                kategori[0][0] = id;         
                kategori[0][1] = String.valueOf(rs.getInt("status")); 
                kategori[0][2] = rs.getString("current_position"); 
                kategori[0][3] = String.valueOf(rs.getInt("evidence"));
                kategori[0][4] = String.valueOf(rs.getInt("date"));  
                kategori[0][5] = rs.getString("updated_by");
                return kategori;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    public static boolean inputTransaksi(int custId, String package_type, int package_weight, int total_cost, String created_at, String username, String address, String phone){
        DateTimeFormatter format = DateTimeFormatter.BASIC_ISO_DATE;
        String date = LocalDate.now().format(format);        
        try{

            String query = "INSERT INTO transaction (customer_id, package_type, package_weight, total_cost, created_at, receipt_name, receipt_address, receipt_phone)" + 
                    "VALUES (?, ?, ?, ?, ?,?, ?, ?)";
                    PreparedStatement st = DatabaseHandler.connect().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    st.setInt(1, custId);
                    st.setString(2, package_type);
                    st.setInt(3, package_weight);
                    st.setInt(4, total_cost);
                    st.setString(5, created_at);
                    st.setString(6, username);
                    st.setString(7, address);
                    st.setString(8, phone);

                    int affectedRows = st.executeUpdate();
        
                    if (affectedRows > 0) {
                        ResultSet rs = st.getGeneratedKeys();
                        if (rs.next()) {
                            int idTransaksi = rs.getInt(1);

                            Transaction transaction = new Transaction(idTransaksi, custId, package_weight, package_type, query, address, phone, total_cost, date);
                            SingletonManager.getInstance().setTransaction(transaction);
                            
                            JOptionPane.showMessageDialog(null, "Registrasi Berhasil", "Registrasi", JOptionPane.INFORMATION_MESSAGE);
                            return true;
                        }
                    }

        
            JOptionPane.showMessageDialog(null, "Registrasi Berhasil", "Registrasi", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Input Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    public static boolean inputDetail(int transactionId, String status, String curr, String evidence, String update){
        DateTimeFormatter format = DateTimeFormatter.BASIC_ISO_DATE;
        String date = LocalDate.now().format(format);        
        try{

            String query = "INSERT INTO shipment_details (transaction_id, status, current_position, evidence, date, updated_by)" + 
                    "VALUES (?, ?, ?, ?, ?,?)";
                    PreparedStatement st = DatabaseHandler.connect().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                    st.setInt(1, transactionId);
                    st.setString(2, status);
                    st.setString(3, curr);
                    st.setString(4, evidence);
                    st.setString(5, date);
                    st.setString(6, update);
                
                    st.execute();
        
            JOptionPane.showMessageDialog(null, "Registrasi Berhasil", "Registrasi", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in Input Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
