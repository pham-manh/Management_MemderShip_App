package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI_edit_member_search extends JFrame{
    private JPanel main_panel;
    private JTextField tf_search;
    private JButton btn_search;
    private JButton btn_exit;
    private JLabel label_welcome;
    private JTable tbl_data_out;
    private JScrollPane scroll_pane;



    public UI_edit_member_search(String title){
        super(title);
        setContentPane(main_panel);
        setSize(800,600);
        setVisible(true);
        setLocationRelativeTo(null); setDefaultCloseOperation(EXIT_ON_CLOSE);
        label_welcome.setFont(new Font("Roboto", Font.PLAIN,60));

        btn_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                new UI_edit_member(tf_search.getText());
                dispose();
            }
        });

    }


}
