import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private final static int constraint = 380;
    private final static String statusHead = " Status: ";
    private static SearchWindow searchWindow = null;
    private static SearchWindowDepNum searchWindowDepNum = null;
    private static SearchWindowDate searchWindowDate = null;
    protected SimpleDateFormat dayFormat = new SimpleDateFormat("dd.MM.yyyy");
    
    //ADD
    private static DeleteWindowName delwindow = null;
    private static DeleteWindowDate delwindowdate = null;
    private static DeleteWindowNum delwindownum = null;
    private static HLThanWindow hlwindow = null;
    
    public Date toDate(String str) {
        try {
            return dayFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    

    public MainWindow() throws HeadlessException {
        JFrame self = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel status = new JLabel(statusHead);
        DefaultListModel<Employee> listModel = new DefaultListModel();
        JList listView = new JList(listModel);
        JScrollPane scrollPane = new JScrollPane(listView);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        
        
        listView.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DELETE: {
                        if (!listView.isSelectionEmpty()) {
                            Employee employee = (Employee) listView.getSelectedValue();
                            if(employee.isDeleted()) {
                                status.setText(statusHead + "object is already deleted");
                                return;
                            }
                            if (JOptionPane.showConfirmDialog(null, "Delete this object?") == 0) {
                                employee.setDeleted(true);
                                status.setText(statusHead + "object " + employee.getTabNumFormat() + " deleted");
                            } else
                                status.setText(statusHead + "deleting refused");
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        

        JTextField depNumInput = new JTextField();
        JTextField fioInput = new JTextField();
        JTextField salaryInput = new JTextField();
        JTextField dateInput = new JTextField();
        JButton submit = new JButton("Submit");
        formPanel.add(new JLabel("Department number:"));
        formPanel.add(depNumInput);
        formPanel.add(new JLabel("Full name:"));
        formPanel.add(fioInput);
        formPanel.add(new JLabel("Salary:"));
        formPanel.add(salaryInput);
        formPanel.add(new JLabel("Hire date (DD.MM.YYYY):"));
        formPanel.add(dateInput);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int depNum = Integer.parseInt(depNumInput.getText());
                    double salary = Double.parseDouble(salaryInput.getText());
                    String fio = fioInput.getText();
                    Date date = toDate(dateInput.getText());
                    listModel.addElement(new Employee(depNum, fio, salary, date));
                    depNumInput.setText("");
                    fioInput.setText("");
                    salaryInput.setText("");
                    dateInput.setText("");
                    status.setText(statusHead + "input succeed");
                } catch (IllegalArgumentException ignored) {
                    status.setText(statusHead + "input error, check input data");
                }
            }
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save as");
        JMenuItem exit = new JMenuItem("Exit");
        JMenu sortMenu = new JMenu("Sort by...");
        JMenuItem byDepNum = new JMenuItem("dep.num.");
        JMenuItem byFio = new JMenuItem("full name");
        JMenuItem byHireDate = new JMenuItem("hire date");
        JMenu searchMenu = new JMenu("Search by...");
        JMenuItem sByFio = new JMenuItem("full name");
        JMenuItem sByDepNum = new JMenuItem("department number");
        JMenuItem sByDate = new JMenuItem("date");
        
        // ADD
        JMenu deleteMenu = new JMenu("Delete by...");
        JMenuItem dByFio = new JMenuItem("full name");
        JMenuItem dByDate = new JMenuItem("date");
        JMenuItem dByNum = new JMenuItem("depurtment numer");
        deleteMenu.add(dByFio);
        deleteMenu.add(dByDate);
        deleteMenu.add(dByNum);
        JMenuItem hlthan = new JMenuItem("Higher/lower than...");
        
        
        
        
        dByFio.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listView.clearSelection();
                if(delwindow == null)
                	delwindow = new DeleteWindowName(listView, listModel);
                else {
                    delwindow.dispose();
                    delwindow = new DeleteWindowName(listView, listModel);
                }
                status.setText(statusHead + "deleting");
            }
        });
        
        dByDate.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listView.clearSelection();
                if(delwindowdate == null)
                	delwindowdate = new DeleteWindowDate(listView, listModel);
                else {
                    delwindowdate.dispose();
                    delwindowdate = new DeleteWindowDate(listView, listModel);
                }
                status.setText(statusHead + "deleting");
            }
        });
        
        dByNum.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listView.clearSelection();
                if(delwindownum == null)
                	delwindownum = new DeleteWindowNum(listView, listModel);
                else {
                    delwindownum.dispose();
                    delwindownum = new DeleteWindowNum(listView, listModel);
                }
                status.setText(statusHead + "deleting");
            }
        });
        
        
        

        fileMenu.add(load);
        fileMenu.add(save);
        fileMenu.add(new JSeparator());
        fileMenu.add(exit);
        sortMenu.add(byDepNum);
        sortMenu.add(byFio);
        sortMenu.add(byHireDate);
        searchMenu.add(sByFio);
        searchMenu.add(sByDepNum);
        searchMenu.add(sByDate);
        menuBar.add(fileMenu);
        menuBar.add(sortMenu);
        menuBar.add(searchMenu);
        menuBar.add(deleteMenu);
        menuBar.add(hlthan);
        setJMenuBar(menuBar);
        
        hlthan.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listView.clearSelection();
                if(hlwindow == null)
                	hlwindow = new HLThanWindow(listView, listModel);
                else {
                    hlwindow.dispose();
                    hlwindow = new HLThanWindow(listView, listModel);
                }
                status.setText(statusHead + "deleting");
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(self);
                if (result != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                File file = fileChooser.getSelectedFile();
                try {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    ArrayList<Employee> empsList = (ArrayList<Employee>) ois.readObject();
                    ois.close();
                    fis.close();
                    listModel.clear();
                    for (Employee emp : empsList) {
                        if (!emp.isDeleted())
                            listModel.addElement(emp);
                    }
                    status.setText(statusHead + "loaded file \"" + file.getName() + '\"');
                } catch (IOException | ClassNotFoundException e1) {
                    status.setText(statusHead + e1.getMessage());
                }
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(self);
                if (result != JFileChooser.APPROVE_OPTION) {
                    return;
                }
                File file = fileChooser.getSelectedFile();
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    ArrayList<Employee> emps = new ArrayList<>();
                    for (int i = 0; i < listModel.size(); i++) {
                        emps.add(listModel.get(i));
                    }
                    oos.writeObject(emps);
                    oos.close();
                    fos.close();
                    status.setText(statusHead + "list saved as \"" + file.getName() + '\"');
                } catch (IOException e1) {
                    status.setText(statusHead + e1.getMessage());
                }
            }
        });

        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        byDepNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<Employee> tempList = new ArrayList<>();
                for (int i = 0; i < listModel.size(); i++)
                    tempList.add(listModel.get(i));
                listModel.clear();
                tempList.sort(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return Integer.compare(o1.getDepNum(), o2.getDepNum());
                    }
                });
                for (Employee emp : tempList)
                    listModel.addElement(emp);

                status.setText(statusHead + "sorted by dep.num.");
            }
        });

        byFio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<Employee> tempList = new ArrayList<>();
                for (int i = 0; i < listModel.size(); i++)
                    tempList.add(listModel.get(i));
                listModel.clear();
                tempList.sort(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return o1.getFio().compareTo(o2.getFio());
                    }
                });
                for (Employee emp : tempList)
                    listModel.addElement(emp);

                status.setText(statusHead + "sorted by full name");
            }
        });

        byHireDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                java.util.List<Employee> tempList = new ArrayList<>();
                for (int i = 0; i < listModel.size(); i++)
                    tempList.add(listModel.get(i));
                listModel.clear();
                tempList.sort(new Comparator<Employee>() {
                    @Override
                    public int compare(Employee o1, Employee o2) {
                        return (o1.getHireDate()).compareTo(o2.getHireDate());
                    }
                });
                for (Employee emp : tempList)
                    listModel.addElement(emp);

                status.setText(statusHead + "sorted by hire date");
            }
        });

        sByFio.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listView.clearSelection();
                if(searchWindow == null)
                    searchWindow = new SearchWindow(listView, listModel);
                else {
                    searchWindow.dispose();
                    searchWindow = new SearchWindow(listView, listModel);
                }
                status.setText(statusHead + "searching");
            }
        });
        
        sByDepNum.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listView.clearSelection();
                if(searchWindowDepNum == null)
                    searchWindowDepNum = new SearchWindowDepNum(listView, listModel);
                else {
                    searchWindowDepNum.dispose();
                    searchWindowDepNum = new SearchWindowDepNum(listView, listModel);
                }
                status.setText(statusHead + "searching");
            }
        });
        
        sByDate.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listView.clearSelection();
                if(searchWindowDate == null)
                    searchWindowDate = new SearchWindowDate(listView, listModel);
                else {
                    searchWindowDate.dispose();
                    searchWindowDate = new SearchWindowDate(listView, listModel);
                }
                status.setText(statusHead + "searching");
            }
        });

        KeyListener enterKL = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER: {
                        submit.doClick();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };
        depNumInput.addKeyListener(enterKL);
        fioInput.addKeyListener(enterKL);
        salaryInput.addKeyListener(enterKL);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.NORTH);

        JPanel southPanel = new JPanel(new BorderLayout());
        JPanel southButtonPanel = new JPanel();
        southButtonPanel.setLayout(new BoxLayout(southButtonPanel, BoxLayout.X_AXIS));
        southButtonPanel.add(submit);
        southButtonPanel.add(status);
        southPanel.add(formPanel, BorderLayout.CENTER);
        southPanel.add(southButtonPanel, BorderLayout.SOUTH);
        panel.add(southPanel, BorderLayout.SOUTH);

        setSize(new Dimension(2 * constraint, constraint));
        setResizable(false);
        setContentPane(panel);
        setVisible(true);
    }
}