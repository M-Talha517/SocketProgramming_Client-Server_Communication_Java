package GUI;

import Backend.Client.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class deleteObjectScreen extends JFrame {
    Object[][] cases = {{"Name", "Age", "Role", "Rating"},
            {"Name", "Age", "Role"},
            {"Team Name", "Players", "Captain", "Coaches"}
    };
    int objectNum;
    Object[] objectData;
    JScrollPane scroll;
    JTable table;
    DefaultComboBoxModel cbmPlayer;
    deleteObjectScreen(int objectNum) {
        this.objectNum = objectNum;
        Object[] Headings = cases[objectNum];
        this.setSize(new Dimension(1000, 500));

        DefaultTableModel dtm = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        dtm.setColumnIdentifiers(Headings);
        table = new JTable();
        table.setModel(dtm);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.addMouseListener(new mouseListener());
        scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        try {
            ArrayList<Object[]> arr = null;
            if (objectNum == 0) arr = Client.testclientViewPlayer();
            else if (objectNum == 1) arr = Client.testclientViewCoach();
            else if (objectNum == 2) arr = Client.testclientViewTeam();

            objectData = arr.toArray();
//            Object[][] data = new Object[objectData.length][];
            for (Object object : objectData)
                dtm.addRow((Object[]) object);

            this.add(scroll);
        } catch (Exception p) {
            System.out.println("EXCEPTION");
        }
//        this.// setLayout(null);
        revalidate();
        repaint();
        this.add(scroll);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
//        this.pack();

    }
    class mouseListener implements MouseListener{
        @Override
        public void mouseClicked(MouseEvent e) {
            int row = table.rowAtPoint(e.getPoint());
            int col = table.columnAtPoint(e.getPoint());
            int option = JOptionPane.showConfirmDialog(null,"ARE YOU SURE YOU WANT TO DELETE", "DELETING OBJECT", JOptionPane.OK_CANCEL_OPTION);
            if (option ==0) {
                try {
                    Client.deleteTeam(Integer.toString(objectNum+1)+"6",table.getValueAt(row, col));
                    JOptionPane.showConfirmDialog(null,"TEAM SUCCESSFULLY DELETED", "DELETING OBJECT", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new deleteObjectScreen(objectNum);
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            }
            System.out.println(option);
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }


    }
    public static void main(String args[]){
        // testing delete teams for future implementation
        new deleteObjectScreen(2);
    }
}