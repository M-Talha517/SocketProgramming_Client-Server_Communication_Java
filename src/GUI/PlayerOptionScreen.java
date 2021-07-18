package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerOptionScreen extends JFrame {
    private JPanel panel;
    private JButton addPlayerButton;
    private JButton viewPlayerButton;
    private JButton searchPlayerButton;
    private JLabel label;
    private JButton BACKButton;


    PlayerOptionScreen( ) {

        this.setTitle("CRICKET TEAMS MANAGEMENT SYSTEM : PLAYER OPTIONS");
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
                disposeFrame();
                new EntitySelectScreen();
            }
        });


        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disposeFrame();
                new AddPlayerScreen()  ;
            }
        });

        viewPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new viewBasicObjectScreen(0);
            }
        });

        searchPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disposeFrame();
                new SearchPlayerScreen();
            }
        });

    }
    private void disposeFrame(){
        this.dispose();
    };



}
