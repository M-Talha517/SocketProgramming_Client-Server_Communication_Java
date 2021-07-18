package GUI;

import Backend.Client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class InitialScreen implements ActionListener, Serializable {


    int[] screenSize = {1366, 700};
    private final JFrame frame;
    private final JLabel background;
    private final JLabel intro;
//    private JLabel names;
    private final JButton startButton;

    public InitialScreen() {
        frame = new JFrame();
        frame.setTitle("CRICKET TEAMS MANAGEMENT SYSTEM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        background = new JLabel(new ImageIcon("src/Images/image4.jpg"));
        background.setMaximumSize(new Dimension(1366, 700));
        background.setBounds(new Rectangle());


        intro = new JLabel("WELCOME TO CRICKET TEAMS MANAGEMENT SYSTEM", SwingConstants.CENTER);
        intro.setOpaque(true);
        intro.setFont(new Font("Mshtakan", Font.BOLD, 40));
        intro.setBounds(0,0,1366,200);
        intro.setBackground(new Color(255, 255, 255, 160));

        intro.setOpaque(true);
        startButton = new JButton(" C O N T I N U E ");

        startButton.setBounds(550, 600, 300, 60);
        startButton.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.yellow));
//        startButton.setVisible(true);
        startButton.setBackground(new Color(255, 255, 255,100));
        startButton.setForeground(new Color(150, 28, 39));
        startButton.setFont(new Font("Mshtakan", Font.ITALIC, 30));
        startButton.setOpaque(true);
        startButton.addActionListener(this);


        background.add(intro);
        background.add(startButton);

        frame.add(background);
        frame.setVisible(true);
        startButton.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            new Client();
            frame.dispose();
            new EntitySelectScreen();
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Failed To Connect To Server");
            e1.printStackTrace();
        }


    }

}
