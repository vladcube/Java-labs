import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HLThanWindow extends JFrame {
    private final static int constraint = 200;
    protected static SimpleDateFormat dayFormat = new SimpleDateFormat("dd.MM.yyyy");
    
    public static Date toDate(String str) {
        try {
            return dayFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HLThanWindow(JList list, DefaultListModel<Employee> listModel) throws HeadlessException {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(new JLabel("Higher/lower (h/l):"));
        JTextField searchField = new JTextField();
        mainPanel.add(searchField);
        mainPanel.add(new JLabel("Key: fio, date, num:"));
        JTextField searchField1 = new JTextField();
        mainPanel.add(searchField1);
        mainPanel.add(new JLabel("Value:"));
        JTextField searchField2 = new JTextField();
        mainPanel.add(searchField2);
        JButton search = new JButton("Search");
        mainPanel.add(search);

        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                	boolean higher = false, f1 = false, f2 = false, f3 = false;
                	if (searchField.getText().equals("h")) higher = true;
                	if (searchField1.getText().equals("fio")) f1 = true;
                	if (searchField1.getText().equals("date")) f2 = true;
                	if (searchField1.getText().equals("num")) f3 = true;
                
                	
                    int[] indices = null;
                    java.util.List<Employee> tempList = new ArrayList<>();

                    for (int i = 0; i < listModel.size(); i++)
                        tempList.add(listModel.get(i));
                    
                    if (f1) {
                    	if (higher) {
                    		for (Employee emp : tempList)
                                if (emp.getFio().compareTo(searchField2.getText()) > 0)
                                    indices = insertValue(indices, listModel.indexOf(emp));
                    	}
                    	else {
                    		for (Employee emp : tempList)
                                if (emp.getFio().compareTo(searchField2.getText()) < 0)
                                    indices = insertValue(indices, listModel.indexOf(emp));
                    	}
                    } else if (f2) {
                    	if (higher) {
                    		for (Employee emp : tempList)
                                if (emp.getHireDate().after(toDate(searchField2.getText())))
                                    indices = insertValue(indices, listModel.indexOf(emp));
                    	}
                    	else {
                    		for (Employee emp : tempList)
                                if (emp.getHireDate().before(toDate(searchField2.getText())))
                                    indices = insertValue(indices, listModel.indexOf(emp));
                    	}
                    	
                    } else if (f3) {
                    	if (higher) {
                    		for (Employee emp : tempList)
                                if (emp.getDepNum() >= Integer.parseInt(searchField2.getText()))
                                    indices = insertValue(indices, listModel.indexOf(emp));
                    	}
                    	else {
                    		for (Employee emp : tempList)
                                if (emp.getDepNum() >= Integer.parseInt(searchField2.getText()))
                                    indices = insertValue(indices, listModel.indexOf(emp));
                    	}
                    }
                    list.setSelectedIndices(indices);
                } catch (NullPointerException ignored) {

                }
            }
        });

        searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER: {
                        search.doClick();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        setResizable(false);
        setSize(new Dimension(2 * constraint, constraint));
        setContentPane(mainPanel);
        setVisible(true);
    }

    private static int[] insertValue(int[] arr, int value) {
        int length = (arr == null) ? 0 : arr.length;
        int[] result = new int[length + 1];
        for (int i = 0; i < length; i++) {
            result[i] = arr[i];
        }
        result[length] = value;
        return result;
    }
}
