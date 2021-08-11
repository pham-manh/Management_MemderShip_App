package controler;

import model.Member;

import java.sql.*;
import java.util.ArrayList;

public class data_access_object {
    String url = "jdbc:sqlserver://localhost;databaseName=member_DATA";
    String user_name = "sa";
    String password = "123";


    public data_access_object() {
        try (Connection ignored = DriverManager.getConnection(url, user_name, password)) {

            System.out.println("link success! ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean add_member(Member s) {

        try (Connection connection = DriverManager.getConnection(url, user_name, password)) {
            String sql = "INSERT INTO data_table(name,id,date_of_birth,phone_number,address,date_start,date_end) VALUES (?,?,?,?,?,?,?);";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, s.getName());
                ps.setString(2, s.getId());
                ps.setDate(3, new Date(s.getDate_of_birth().getTime()));
                ps.setString(4, s.getPhone_number());
                ps.setString(5, s.getAddress());
                ps.setDate(6, new Date(s.getDate_start().getTime()));
                ps.setDate(7, new Date(s.getDate_end().getTime()));
                ps.execute();

                return ps.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Member> get_data_list() {
        ArrayList<Member> ls = new ArrayList<>();
        String sql = "SELECT * data_member";

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
                    ls.add(m);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ;

        return ls;
    }


    public static void main(String[] args) {
        new data_access_object();
    }

}
