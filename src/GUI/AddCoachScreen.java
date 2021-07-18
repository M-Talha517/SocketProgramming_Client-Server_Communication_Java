package GUI;

import Backend.Client.Client;
import models.Coach;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCoachScreen extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JTextField roleField;
    private JLabel name;
    private JLabel age;
    private JLabel role;
    private JPanel panel;
    private JButton BACKButton;
    private JButton ADDCOACHButton;
    ;

    AddCoachScreen() {
        this.setTitle("CRICKET TEAMS MANAGEMENT SYSTEM : ADD COACH");
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


        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CoachOptionScreen();
            }
        });
        ADDCOACHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("IN ADD COACH");
                if (nameField.getText().trim().isEmpty() || nameField == null)
                    JOptionPane.showMessageDialog(null, "NAME IS EMPTY");
                else if (roleField.getText().trim().isEmpty() || ageField.getText().trim() == null)
                    JOptionPane.showMessageDialog(null, "AGE IS EMPTY");
                else if (roleField.getText().trim().isEmpty() || roleField.getText().trim() == null)
                    JOptionPane.showMessageDialog(null, "ROLE IS EMPTY");
                else {
                    try {
                        int age = Integer.parseInt(ageField.getText().trim());
                        if (!(age >= 25 && age <= 65))
                            throw new ArithmeticException();

                        Coach c = new Coach();
                        c.setName(nameField.getText().trim());
                        c.setRole(roleField.getText().trim());
                        c.setAge(Integer.parseInt(ageField.getText().trim()));
                        String fromServer = Client.clientAddCoach(c);
                        JOptionPane.showMessageDialog(null, fromServer);
                    } catch (ArithmeticException ae) {
                        JOptionPane.showMessageDialog(null, "COACH AGE SHOULD BE WITHIN 25-60");
                    } catch (Exception E) {

                    }
                }
            }
        });

    }

    private void disposeFrame() {
        this.dispose();
    }

    ;
}




