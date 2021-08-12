package view;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UI_home_page extends JFrame{
    public static String title = "Membership Management";

    private JPanel main_panel;
    private JButton btn_membership_managementButton;
    private JButton btn_view_data;
    private JButton btn_exit;
    private JLabel label_welcome;

    public UI_home_page(String title) {
        super(title);
        setContentPane(main_panel);
        setSize(1280, 1024);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        label_welcome.setFont(new Font("Roboto", Font.PLAIN, 60));

        btn_exit.addActionListener(e -> System.exit(EXIT_ON_CLOSE));

        btn_membership_managementButton.addActionListener(e -> {
            new UI_menu_management(title);
            dispose();
        });
        btn_exit.addActionListener(e -> System.exit(3));
        btn_view_data.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UI_view_data_member(title);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        System.out.println(title);
    }
}
