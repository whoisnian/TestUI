import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.metal.*;

public class TestUI extends JPanel implements ActionListener, ChangeListener {
    private static JFrame frame = new JFrame();
    private UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();
    private String LafClassName = UIManager.getSystemLookAndFeelClassName();
    private String[] items = {"item1", "item2", "item3", "item4", "item5"};
    private JComboBox<String> jcb = new JComboBox<>(items);
    private JTextField jtf1 = new JTextField();
    private JTextField jtf2 = new JTextField();
    private JCheckBox jcb1 = new JCheckBox("CheckBox 1");
    private JCheckBox jcb2 = new JCheckBox("CheckBox 2");
    private JRadioButton jrb1 = new JRadioButton("RadioButton 1");
    private JRadioButton jrb2 = new JRadioButton("RadioButton 2");
    private JProgressBar jpb = new JProgressBar(0, 100);
    private JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 75);
    private JSpinner spinner = new JSpinner(new SpinnerNumberModel(75, 0, 100, 1));
    private JButton jbn1 = new JButton("Color");
    private JButton jbn2 = new JButton("File");
    private String[][] data = { {"001", "name1", "other1"},
                                {"002", "name2", "other2"},
                                {"003", "name3", "other3"}};
    private String[] col = {"id", "name", "other"};
    private JTable jt = new JTable(data, col);

    public TestUI() {
        JDialog jd = new JDialog(frame, "Select a Laf", true);
        jd.setLayout(null);

        JComboBox<String> jcbd = new JComboBox<>();
        jcbd.setBounds(20, 20, 190, 30);
        jcbd.addItem(" -- Click to select a Laf -- ");
        for(int i = 0;i < laf.length;i++) {
            jcbd.addItem(laf[i].getName());
        }
        jcbd.addItem("Darcula");
        jcbd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jcbd.getSelectedIndex() > 0&&jcbd.getSelectedIndex() <= laf.length) {
                    LafClassName = laf[jcbd.getSelectedIndex()-1].getClassName();
                }
                else if(jcbd.getSelectedItem().toString() == "Darcula") {
                    LafClassName = "com.bulenkov.darcula.DarculaLaf";
                }
                else {
                    LafClassName = UIManager.getSystemLookAndFeelClassName();
                }
            }
        });
        jd.add(jcbd);

        JButton jbnd = new JButton("OK");
        jbnd.setBounds(220, 20, 60, 30);
        jbnd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jd.dispose();
            }
        });
        jd.add(jbnd);

        jd.setSize(300, 110);
        jd.setVisible(true);

        setLayout(null);

        // UI ComboBox
        JLabel jl1 = new JLabel("UI :");
        jl1.setBounds(10, 10, 30, 30);
        add(jl1);

        jcb.setBounds(40, 10, 100, 30);
        add(jcb);

        jtf1.setEditable(false);
        jtf1.setText(LafClassName);
        jtf1.setBounds(150, 10, 440, 30);
        add(jtf1);

        // TestField
        JLabel jl2 = new JLabel("TextField :");
        jl2.setBounds(10, 50, 80, 30);
        add(jl2);

        jtf2.setBounds(90, 50, 200, 30);
        add(jtf2);

        // CheckBox
        jcb1.setBounds(10, 90, 135, 30);
        add(jcb1);

        jcb2.setBounds(10, 130, 135, 30);
        add(jcb2);

        // RadioButton
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);

        jrb1.setBounds(155, 90, 135, 30);
        add(jrb1);

        jrb2.setBounds(155, 130, 135, 30);
        add(jrb2);

        // ProgressBar
        jpb.setBounds(300, 50, 290, 30);
        jpb.setValue(75);
        add(jpb);

        // Slider 
        slider.setBounds(300, 100, 220, 50);
        add(slider);
        slider.addChangeListener(this);
        
        // Spinner
        spinner.setBounds(530, 110, 60, 30);
        add(spinner);
        spinner.addChangeListener(this);

        // ColorChooser
        jbn1.setBounds(10, 170, 100, 30);
        add(jbn1);
        jbn1.addActionListener(this);

        // FileChooser
        jbn2.setBounds(120, 170, 100, 30);
        add(jbn2);
        jbn2.addActionListener(this);

        // Table
        JScrollPane jsp = new JScrollPane(jt);
        jsp.setBounds(10, 210, 580, 90);
        add(jsp);

        try {
            UIManager.setLookAndFeel(LafClassName);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception excep) {
            System.err.println(excep.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == jbn1) {
            Color color = JColorChooser.showDialog(this, "Select a color", Color.BLACK);
        }
        else if(source == jbn2) {
            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(this);
        }
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        if(source == slider) {
            jpb.setValue(slider.getValue());
            spinner.setValue(slider.getValue());
        }
        else if(source == spinner) {
            jpb.setValue((Integer)spinner.getValue());
            slider.setValue((Integer)spinner.getValue());
        }
    }

    public static void main(String[] args) {
        frame.setTitle("Test UI");
        frame.setSize(600, 400);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        Container contentPane = frame.getContentPane();
        contentPane.add(new TestUI());
        frame.setVisible(true);
    }
}
