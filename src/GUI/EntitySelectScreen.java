package GUI;

import Backend.Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntitySelectScreen extends JFrame {

    private JPanel panel;
    private JButton playerButton;
    private JButton coachButton;
    private JButton teamButton;
    private JButton EXITButton;
    private JPanel p1;
    private JLabel label1;

    public EntitySelectScreen( ) {
        this.setTitle("CRICKET TEAMS MANAGEMENT SYSTEM : SELECT ENTITY");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
this.setSize(1000,1000);
setExtendedState(JFrame.MAXIMIZED_BOTH);;
        this.add(panel);


        this.setVisible(true);



        playerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PlayerOptionScreen();
            }
        });
        teamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
               new TeamOptionScreen();
            }
        });
        coachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CoachOptionScreen();
            }
        });

        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client.exit();
            }
        });
    }


    private void createUIComponents() {
        label1 = new JLabel(new ImageIcon("/Images/image4"));
        playerButton = new JButton("PLAYER");
        coachButton = new JButton("COACH");
        teamButton = new JButton("TEAM");
        EXITButton = new JButton("EXIT");
        playerButton.setFont(new Font("Arial", Font.PLAIN, 40));
        coachButton.setFont(new Font("Arial", Font.PLAIN, 40));
        teamButton.setFont(new Font("Arial", Font.PLAIN, 40));
        EXITButton.setFont(new Font("Arial", Font.PLAIN, 40));

    }
}
