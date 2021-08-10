package view;

import com.toedter.calendar.JDateChooser;
import controler.data_access_object;
import model.Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class UI_add_new_member extends JFrame {
    static int count =1;

    private JPanel main_panel;
    private JButton btn_add;
    private JButton btn_exit;
    private JTextField tf_name;
    private JTextField tf_id;
    private JTextField tf_phone_number;
    private JTextField tf_address;
    private JPanel date_of_birth_panel;
    private JPanel date_start_panel;
    private JPanel date_end_panel;
    private JLabel label_menu;

    private  JDateChooser data_chooser_birth = new JDateChooser();
    private  JDateChooser data_chooser_star = new JDateChooser();
    private  JDateChooser data_chooser_end = new JDateChooser();

    private final ArrayList<Member> list_data = new ArrayList<>();

    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    Calendar calendar = Calendar.getInstance();



    public UI_add_new_member(String title) {
        super(title);
        setContentPane(main_panel);
        setSize(1280, 1024);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        label_menu.setFont(new Font("Roboto", Font.PLAIN, 60));


        date_of_birth_panel.add(data_chooser_birth);
        date_start_panel.add(data_chooser_star);
        data_chooser_star.setDate(calendar.getTime());
        date_end_panel.add(data_chooser_end);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");


        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UI_home_page("Membership Management");
                dispose();
            }
        });


        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Member a = new Member();
                a.setName(tf_name.getText());
                a.setId(tf_id.getText());
                a.setDate_of_birth(data_chooser_birth.getDate());
                a.setPhone_number(tf_phone_number.getText());
                a.setAddress(tf_address.getText());
                a.setDate_start(data_chooser_star.getDate());
                a.setDate_end(data_chooser_end.getDate());
//                list_data.add(a);
                if (new data_access_object().add_member(a)){
                    JOptionPane.showMessageDialog(rootPane,"Add Success!");
                    System.out.println(++count);
                    tf_name.setText("");
                    tf_id.setText("");
                    data_chooser_birth.setDate(null);
                    tf_phone_number.setText("");
                    tf_address.setText("");
                    data_chooser_star.setDate(calendar.getTime());

                }else {
                    JOptionPane.showMessageDialog(rootPane,"Add failed!!");
                }
            }
        });
    }
}
