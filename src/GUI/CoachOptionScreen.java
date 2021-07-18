package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoachOptionScreen extends JFrame {
    private JButton BACKButton;
    private JPanel panel;
    private JButton addCoachButton;
    private JButton viewCoachButton;
    private JButton searchCoachButton;
    private JLabel label;

    CoachOptionScreen() {

        this.setTitle("CRICKET TEAMS MANAGEMENT SYSTEM : COACH OPTIONS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);

        setExtendedState(JFrame.MAXIMIZED_BOTH);;

        // setLayout(.getText().trim());
        BACKButton = new JButton("BACK");
        BACKButton.setBounds(5,5,70,25);

        this.add(BACKButton);
        this.add(panel);
        this.setVisible(true);


        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EntitySelectScreen();
            }
        });

        addCoachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AddCoachScreen();
            }
        });

        viewCoachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new viewBasicObjectScreen(1);
            }
        });

        searchCoachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SearchCoachScreen();
            }
        });

    }



}
