package view;

import javax.swing.*;
import java.awt.*;

public class UI_member_search_renewal extends JFrame{
    private JPanel main_panel;
    private JTextField tf_search;
    private JButton btn_search;
    private JButton btn_exit;
    private JLabel label_welcome;

    public UI_member_search_renewal(String title){
        super(title);
        setContentPane(main_panel);
        setSize(800,600);
        setVisible(true);
        setLocationRelativeTo(null); setDefaultCloseOperation(EXIT_ON_CLOSE);
        label_welcome.setFont(new Font("Roboto", Font.PLAIN,60));

        btn_search.addActionListener(e -> {
            new UI_renewal_member(tf_search.getText());
            dispose();
        });
        btn_exit.addActionListener(e -> {
            new UI_home_page(title);
            dispose();
        });
    }
}
