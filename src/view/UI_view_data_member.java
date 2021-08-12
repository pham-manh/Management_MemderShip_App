package view;

import model.Member;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UI_view_data_member extends JFrame {

    private JPanel main_panel;
    private JButton btn_exit;
    private JTable tbl_data;
    private JScrollPane panel_scroll;

    String url = "jdbc:sqlserver://localhost;databaseName=member_DATA";
    String user_name = "sa";
    String password = "123";

    private DefaultTableModel model;


    public UI_view_data_member(String title) {
        super(title);

        setContentPane(main_panel);
        setSize(1280, 1024);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        tittle_Col_Table();
        show_result();

        btn_exit.addActionListener(e -> new UI_home_page(title));
    }

    private void tittle_Col_Table() {
        model = new DefaultTableModel();
        Object[] column = {"ID", "Name", "Date of Birth", "Phone Number", "Address", "Date Start", "Date end"};
        model.setColumnIdentifiers(column);
        tbl_data.setModel(model);
        panel_scroll.setViewportView(tbl_data);
    }

    public void show_result() {

        String sql = "SELECT * FROM data_table ";

        try (Connection connection = DriverManager.getConnection(url, user_name, password)) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Member m = new Member();
                    m.setName(rs.getString("name"));
                    m.setId(rs.getString("id"));
                    m.setDate_of_birth(rs.getDate("date_of_birth"));
                    m.setPhone_number(rs.getString("phone_number"));
                    m.setAddress(rs.getString("address"));
                    m.setDate_start(rs.getDate("date_start"));
                    m.setDate_end(rs.getDate("date_end"));
                    model.addRow(new Object[]{m.getId(), m.getName(), m.getDate_of_birth(), m.getAddress(), m.getPhone_number(), m.getDate_start(), m.getDate_end()}
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
