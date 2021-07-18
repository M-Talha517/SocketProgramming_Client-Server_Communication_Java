package GUI;

import Backend.Client.Client;
import models.Team;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddTeamScreen extends JFrame {
    public JComboBox comboBox2;
    private JTable table1;
    private JTable table2;
    private JButton BACKButton;
    private JPanel panel;
    private JScrollPane scroll1;
    private JScrollPane scroll2;
    private JComboBox comboBox1;
    private JButton CLEARPLAYERSTABLEButton;
    private JButton CLEARCOACHTABLEButton;
    private JTextField nameField;

    private JButton AddTeamButton;
    private JComboBox comboBox3;
    DefaultTableModel dtmPlayer;
    DefaultTableModel dtmCoach;
    private int playerCount = 0;
    private int coachCount = 0;
    private DefaultComboBoxModel<Object> dcbmCaptain;


    public AddTeamScreen() throws IOException {

        this.setTitle("CRICKET TEAMS MANAGEMENT SYSTEM : ADD TEAM");
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


        revalidate();
        repaint();
        // setLayout(null);
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new TeamOptionScreen();
            }
        });
        AddTeamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().trim().isEmpty() || nameField.getText().trim().equals(""))
                    JOptionPane.showMessageDialog(null, "EMPTY FIELD NOT ACCEPTABLE");
                else if (table1.getRowCount() == 0)
                    JOptionPane.showMessageDialog(null, "PLEASE ADD A PLAYER");
                else if (comboBox3.getSelectedItem() == null)
                    JOptionPane.showMessageDialog(null, "PLEASE SELECT A CAPTAIN");
                else if (table2.getRowCount() == 0)
                    JOptionPane.showMessageDialog(null, "PLEASE ADD A COACH");
                else {
                    Team team = new Team();
                    team.setTeamName(nameField.getText().trim());
                    String[] p = new String[table1.getRowCount()];
                    for (int i = 0; i < table1.getRowCount(); i++)
                        p[i] = dtmPlayer.getValueAt(i, 0).toString();
                    team.setPlayers(p);
                    p = new String[table2.getRowCount()];
                    for (int i = 0; i < table2.getRowCount(); i++)
                        p[i] = dtmCoach.getValueAt(i, 0).toString();
                    team.setCaptain(comboBox3.getSelectedItem().toString());
                    team.setCoaches(p);

                    String fromServer = "";
                    try {
                        fromServer = Client.addTeam(team);
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                    dispose();
                    try {
                        new AddTeamScreen();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, fromServer);
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean playerExists = false;
                for (int i = 0; i < dtmPlayer.getRowCount(); i++)
                    if (dtmPlayer.getValueAt(i, 0).toString().equalsIgnoreCase(comboBox1.getSelectedItem().toString())) {
                        dtmPlayer.removeRow(i);
                        System.out.println(i);
                        dcbmCaptain.removeElement(comboBox1.getSelectedItem().toString());
                        JOptionPane.showMessageDialog(null, "Player Removed");
                        playerExists = true;
                        playerCount--;

                    }

                if (playerCount > 10)
                    JOptionPane.showMessageDialog(null, "MAXIMUM PLAYERS ADDED (11)");
                else if (!playerExists) {
                    dtmPlayer.addRow(new String[]{comboBox1.getSelectedItem().toString()});
                    dcbmCaptain.addElement(comboBox1.getSelectedItem().toString());
                    JOptionPane.showMessageDialog(null, "Player Added");
                    playerCount++;
                }

            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean coachExists = false;
                for (int i = 0; i < dtmCoach.getRowCount(); i++)
                    if (dtmCoach.getValueAt(i, 0).toString().equalsIgnoreCase(comboBox1.getSelectedItem().toString())) {
                        dtmCoach.removeRow(i);
                        System.out.println(i);
                        JOptionPane.showMessageDialog(null, "COACH ALREADY EXISTS");
                        coachExists = true;
                        playerCount--;
                    }

                if (coachCount > 10)
                    JOptionPane.showMessageDialog(null, "MAXIMUM COACHES ADDED (3)");
                else if (!coachExists) {
                    dtmCoach.addRow(new String[]{comboBox2.getSelectedItem().toString()});
                    JOptionPane.showMessageDialog(null, "COACH Added");
                    coachCount++;
                }

            }
        });

        CLEARPLAYERSTABLEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtmPlayer.setRowCount(0);
                dcbmCaptain.removeAllElements();
                playerCount = 0;
            }
        });
        CLEARCOACHTABLEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtmCoach.setRowCount(0);
                coachCount = 0;
            }
        });
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setVisible(true);
    }

    private void createUIComponents() throws IOException, ClassNotFoundException {

        Object[][] populationData = Client.getPlayerandCoachDataForTeamPopulation();
        String players[] = new String[populationData[0].length];
        for (int i = 0; i < populationData[0].length; i++) {
            try {
                players[i] = populationData[0][i].toString();
            } catch (NullPointerException ex) {

            }
        }

        comboBox1 = new JComboBox(players);
        String coaches[] = new String[populationData[1].length];
        for (int i = 0; i < populationData[1].length; i++) {
            try {
                coaches[i] = populationData[1][i].toString();
            } catch (NullPointerException ex) {

            }
        }
        comboBox2 = new JComboBox(coaches);


        String[] Headings = {"Player Name, {Age}"};
        dtmPlayer = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        dtmPlayer.setColumnIdentifiers(Headings);
        table1 = new JTable();
        table1.setModel(dtmPlayer);
        table1.getTableHeader().setResizingAllowed(false);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table1.setFillsViewportHeight(true);
        scroll1 = new JScrollPane(table1);
        scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);


        String[] Headings2 = new String[]{"Coach Name"};
        dtmCoach = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        dtmCoach.setColumnIdentifiers(Headings2);
        table2 = new JTable();
        table2.setModel(dtmCoach);
        table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table2.setFillsViewportHeight(true);
        scroll2 = new JScrollPane(table2);
        scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        dcbmCaptain = new DefaultComboBoxModel<>();
        comboBox3 = new JComboBox(dcbmCaptain);
    }
}
