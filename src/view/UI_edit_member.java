package view;

import com.toedter.calendar.JDateChooser;
import controler.data_access_object;
import model.Member;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UI_edit_member extends JFrame {
    private final JDateChooser date_chooser_birth = new JDateChooser();
    private final JDateChooser date_chooser_start = new JDateChooser();
    private final JDateChooser date_chooser_end = new JDateChooser();
    String url = "jdbc:sqlserver://localhost;databaseName=member_DATA";
    String user_name = "sa";
    String password = "123";
    private JTextField tf_id;
    private JTextField tf_phone_number;
    private JTextField tf_address;
    private JButton btn_edit;
    private JButton btn_exit;
    private JTextField tf_name;
    private JPanel panel_date_of_birth;
    private JPanel panel_date_start;
    private JPanel panel_date_end;
    private JLabel label_welcome;
    private JPanel main_panel;


    public UI_edit_member(String id) {


        setContentPane(main_panel);
        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        label_welcome.setFont(new Font("Roboto", Font.PLAIN, 60));

        panel_date_of_birth.add(date_chooser_birth);
        panel_date_start.add(date_chooser_start);
        panel_date_end.add(date_chooser_end);

        show_data_search(id);


        btn_edit.addActionListener(e -> update_data(id));
        btn_exit.addActionListener(e -> {
            new UI_home_page("MemberShip Management");
            dispose();
        });
    }

    private void update_data(String id) {
        try {
            new data_access_object();
            Connection connection = DriverManager.getConnection(url, user_name, password);
            String sql = "UPDATE data_table set name= '" + tf_name.getText() + "' ,address = '" + tf_address.getText() + "'," +
                    "phone_number='" + tf_phone_number.getText() + "', date_of_birth = ?," +
                    "date_start = ?, date_end=? where id = '" + id + "' ";
            try {
                Member s = new Member();
                s.setDate_of_birth(date_chooser_birth.getDate());
                s.setDate_start(date_chooser_start.getDate());
                s.setDate_end(date_chooser_end.getDate());
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setDate(1, new Date(s.getDate_of_birth().getTime()));
                ps.setDate(2, new Date(s.getDate_start().getTime()));
                ps.setDate(3, new Date(s.getDate_end().getTime()));


                ps.execute();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void show_data_search(String id) {
        try {
            Connection connection = DriverManager.getConnection(url, user_name, password);
            String sql = "Select * from data_table where id =?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            System.out.println("show success");


            if (rs.next()) {
                tf_name.setText(rs.getString("name"));
                tf_id.setText(rs.getString("id"));
                tf_id.setEditable(false);
                tf_address.setText(rs.getString("address"));
                tf_phone_number.setText(rs.getString("phone_number"));
                date_chooser_birth.setDate(rs.getDate("date_of_birth"));
                date_chooser_start.setDate(rs.getDate("date_start"));
                date_chooser_end.setDate(rs.getDate("date_end"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
