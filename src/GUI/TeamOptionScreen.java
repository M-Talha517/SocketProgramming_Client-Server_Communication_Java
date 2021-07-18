package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TeamOptionScreen extends JFrame {
    private JPanel panel;
    private JButton searchTeamButton;
    private JLabel label;
    private JButton addTeamButton;
    private JButton viewTeamButton;
    private JButton BACKButton;

    TeamOptionScreen( ) {

        this.setTitle("CRICKET TEAMS MANAGEMENT SYSTEM : TEAM OPTIONS ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);

        setExtendedState(JFrame.MAXIMIZED_BOTH);;

        // setLayout(null);
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


        addTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                try {
                    new AddTeamScreen();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        viewTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                disposeFrame();
                new viewBasicObjectScreen(2);
            }
        });

        searchTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SearchTeamScreen();
            }
        });

    }

}
