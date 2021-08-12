package view;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UI_remove_member extends JFrame {
    private final JDateChooser date_chooser_birth = new JDateChooser();
    private final JDateChooser date_chooser_start = new JDateChooser();
    private final JDateChooser date_chooser_end = new JDateChooser();
    String url = "jdbc:sqlserver://localhost;databaseName=member_DATA";
    String user_name = "sa";
    String password = "123";
    private JPanel main_panel;
    private JTextField tf_id;
    private JTextField tf_name;
    private JTextField tf_address;
    private JButton btn_remove;
    private JButton btn_exit;
    private JLabel label_welcome;
    private JPanel panel_chooser_birth;
    private JPanel panel_date_start;
    private JPanel panel_date_end;
    private JTextField tf_phone_number;

    public UI_remove_member(String id){

        setContentPane(main_panel);
        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        label_welcome.setFont(new Font("Roboto", Font.PLAIN, 60));

        panel_chooser_birth.add(date_chooser_birth);
        panel_date_start.add(date_chooser_start);
        panel_date_end.add(date_chooser_end);

        show_data_search(id);

        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UI_home_page("MemberShip Management");
                dispose();
            }
        });
        btn_remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(id);
                new UI_member_search_remove("MemberShip Management");
                dispose();
            }
        });
    }

    private void show_data_search(String id) {
        try {
            Connection connection = DriverManager.getConnection(url, user_name, password);
            System.out.println("show success");
            String sql = "Select * from data_table where id =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();


            if (rs.next()) {
                tf_name.setText(rs.getString("name"));
                tf_name.setEditable(false);
                tf_id.setText(rs.getString("id"));
                tf_id.setEditable(false);
                tf_address.setText(rs.getString("address"));
                tf_address.setEditable(false);
                tf_phone_number.setText(rs.getString("phone_number"));
                tf_phone_number.setEditable(false);
                date_chooser_birth.setDate(rs.getDate("date_of_birth"));
                date_chooser_birth.setEnabled(false);
                date_chooser_start.setDate(rs.getDate("date_start"));
                date_chooser_start.setEnabled(false);
                date_chooser_end.setDate(rs.getDate("date_end"));
                date_chooser_end.setEnabled(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void remove(String id){
        try {
            Connection connection = DriverManager.getConnection(url, user_name, password);
            System.out.println("delete success!");
            String sql = "DELETE from data_table where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
