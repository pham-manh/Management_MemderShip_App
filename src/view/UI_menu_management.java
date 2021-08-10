package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI_menu_management extends JFrame {
    private JPanel main_panel;
    private JLabel lable_menu;
    private JButton btn_add;
    private JButton btn_edit;
    private JButton btn_renewal;
    private JButton btn_remove;
    private JButton btn_exit;
    String title = "Membership Management";

    public UI_menu_management(String title) {
        super(title);
        setContentPane(main_panel);
        setSize(1280, 1024);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        lable_menu.setFont(new Font("Roboto", Font.PLAIN, 60));


        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UI_home_page(title);
                dispose();
            }
        });
        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UI_add_new_member(title);
                dispose();
            }
        });
        btn_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UI_edit_member_search(title);
                dispose();
            }
        });
    }
}