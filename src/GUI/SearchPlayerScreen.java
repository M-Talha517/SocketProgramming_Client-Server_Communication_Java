package GUI;

import Backend.Client.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class SearchPlayerScreen extends JFrame {
    ;
    private JButton SEARCHButton;
    private JPanel panel;
    private JTextField textField1;
    private JLabel label;
    private JButton BACKButton;

    SearchPlayerScreen() {
        ;
        setTitle("CRICKET TEAMS MANAGEMENT SYSTEM : SEARCH PLAYER");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ;

        // setLayout(null);
        BACKButton = new JButton("BACK");
        BACKButton.setBounds(5, 5, 70, 25);

        this.add(BACKButton);
        this.add(panel);
        this.setVisible(true);

        SEARCHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (textField1.getText().trim().isEmpty() || textField1.getText().trim().equalsIgnoreCase(""))
                    JOptionPane.showMessageDialog(null, "NAME IS EMPTY");
                else {
                    try {
                        if (Client.clientSearchPlayer(textField1.getText().trim()))
                            label.setText("PLAYER EXISTS");
                        else
                            label.setText("PLAYER DOES NOT EXISTS");

                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }
            }
        });
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PlayerOptionScreen();
            }
        });
    }


}
