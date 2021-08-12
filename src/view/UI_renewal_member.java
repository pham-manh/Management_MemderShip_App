package view;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UI_renewal_member extends JFrame {
    private JPanel main_panel;
    private JTextField tf_id;
    private JTextField tf_name;
    private JButton btn_submit;
    private JButton btn_exit;
    private JPanel panel_date_start;
    private JPanel panel_date_end;
    private JLabel label_welcome;

    private final JDateChooser date_chooser_start = new JDateChooser();
    private final JDateChooser date_chooser_end = new JDateChooser();


    String url = "jdbc:sqlserver://localhost;databaseName=member_DATA";
    String user_name = "sa";
    String password = "123";

    public UI_renewal_member(String id){
        setTitle("Membership Management");
        setContentPane(main_panel);
        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        label_welcome.setFont(new Font("Roboto", Font.PLAIN, 60));

        panel_date_start.add(date_chooser_start);
        panel_date_end.add(date_chooser_end);

        show_data_search(id);


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
                date_chooser_start.setDate(rs.getDate("date_start"));
                date_chooser_start.setEnabled(false);
                date_chooser_end.setDate(rs.getDate("date_end"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
