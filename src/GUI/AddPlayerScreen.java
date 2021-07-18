package GUI;

import Backend.Client.Client;
import models.Player;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddPlayerScreen extends JFrame {
    private JTextField nameField;
    private JTextField ratingField;
    private JTextField ageField;
    private JTextField roleField;
    private JLabel name;
    private JLabel rating;
    private JLabel age;
    private JLabel role;
    private JButton AddPlayerButton;
    private JPanel panel;
    private JComboBox comboBox1;
    private JButton BACKButton;

    AddPlayerScreen() {


        this.setTitle("CRICKET TEAMS MANAGEMENT SYSTEM : ADD PLAYER");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        ;

        // setLayout(null);
        BACKButton = new JButton("BACK");
        BACKButton.setBounds(5, 5, 70, 25);

        this.add(BACKButton);
        this.add(panel);
        this.setVisible(true);

        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PlayerOptionScreen();
            }
        });
        AddPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().trim().isEmpty() || nameField.getText().trim().equalsIgnoreCase(""))
                    JOptionPane.showMessageDialog(null, "NAME IS EMPTY");
                else if (ageField.getText().trim().isEmpty() || ageField.getText().trim().equalsIgnoreCase(""))
                    JOptionPane.showMessageDialog(null, "AGE IS EMPTY");
                else if (comboBox1.getSelectedItem()==null)
                    JOptionPane.showMessageDialog(null, "PLAYER ROLE NOT SELECTED");
                else if (ratingField.getText().trim().isEmpty() || ratingField.getText().trim().equalsIgnoreCase(""))
                    JOptionPane.showMessageDialog(null, "RATING IS EMPTY");
                else {
                    try {
                        int age = Integer.parseInt(ageField.getText().trim());
                        if (!(age > 17 && age <= 40))
                            throw new ArithmeticException();

                        int rating = Integer.parseInt(ratingField.getText().trim());
                        if (rating < 1 && rating < 100)
                            throw new IOException();
                        Player p = new Player();
                        p.setName(nameField.getText().trim());
                        p.setRole(comboBox1.getSelectedItem().toString());
                        p.setAge(age);
                        p.setRating(rating);
                        String fromServer = Client.clientAddPlayer(p);
                        JOptionPane.showMessageDialog(null, fromServer);
                    } catch (ArithmeticException ae) {
                        JOptionPane.showMessageDialog(null, "PLAYER AGE SHOULD BE WITHIN 18-40");
                    } catch (IOException ie) {
                        JOptionPane.showMessageDialog(null, "RATING SHOULD BE WITHIN 1-100");
                    } catch (Exception ee) {

                    }
                }
            }
        });

    }

    private void createUIComponents() {
        DefaultComboBoxModel dcbm = new DefaultComboBoxModel();
        dcbm.addElement("Batsman");
        dcbm.addElement("Bowler");
        dcbm.addElement("Wicket-Keeper");
        dcbm.addElement("All-Rounder");
        dcbm.addElement("Fielder");
        comboBox1 = new JComboBox(dcbm);

    }
}
