package GUI;

import Backend.Client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SearchTeamScreen extends JFrame {

    private JButton SEARCHButton;
    private JLabel label;
    private JTextField textField1;
    private JButton BACKButton;
    private JPanel panel;

    public SearchTeamScreen() {
        setTitle("CRICKET TEAMS MANAGEMENT SYSTEM : SEARCH TEAM");
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
                new TeamOptionScreen();
            }
        });
        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().trim().isEmpty() || textField1.getText().trim().equalsIgnoreCase(""))
                    JOptionPane.showMessageDialog(null, "SEARCH FIELD IS EMPTY");
                else {
                    try {
                        if (Client.clientSearchTeam(textField1.getText().trim()))
                            label.setText("TEAM EXISTS");
                        else
                            label.setText("TEAM DOES NOT EXISTS");

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }
            }
        });
    }
}
