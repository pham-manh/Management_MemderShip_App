package view;

import com.toedter.calendar.JDateChooser;
import controler.data_access_object;
import model.Member;

import javax.swing.*;
import java.awt.*;

import java.sql.*;

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


        btn_exit.addActionListener(e -> {
            new UI_home_page("Membership Management");
            dispose();
        });
        btn_submit.addActionListener(e -> update_data(id));
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

    private void update_data(String id) {
        try {
            new data_access_object();
            Connection connection = DriverManager.getConnection(url, user_name, password);
            String sql = "UPDATE data_table set name= '" + tf_name.getText() + "' ,date_start = ?, date_end=? where id = '" + id + "' ";
            try {
                Member s = new Member();
                s.setDate_start(date_chooser_start.getDate());
                s.setDate_end(date_chooser_end.getDate());

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setDate(1, new Date(s.getDate_start().getTime()));
                ps.setDate(2, new Date(s.getDate_end().getTime()));
                ps.execute();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
