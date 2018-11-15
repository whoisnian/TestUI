import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class TestUI extends JPanel implements ActionListener, ChangeListener {
    private UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();
    private JComboBox<String> jcb = new JComboBox<>();
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
        setLayout(null);
        
        for(int i = 0;i < laf.length;i++) {
            jcb.addItem(laf[i].getName());
        }

        /*try {
            UIManager.setLookAndFeel("com.bulenkov.darcula.DarculaLaf");
        }
        catch (Exception excep) {}*/

        // UI ComboBox
        JLabel jl1 = new JLabel("UI :");
        jl1.setBounds(10, 10, 30, 30);
        add(jl1);

        jcb.setBounds(40, 10, 100, 30);
        add(jcb);
        jcb.addActionListener(this);

        jtf1.setEditable(false);
        jtf1.setText(UIManager.getCrossPlatformLookAndFeelClassName());
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
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == jcb) {
            //System.out.println(jcb.getSelectedIndex());
            jtf1.setText(laf[jcb.getSelectedIndex()].getClassName());
            try {
                UIManager.setLookAndFeel(laf[jcb.getSelectedIndex()].getClassName());
                SwingUtilities.updateComponentTreeUI(this);
            }
            catch(Exception excep) {}
        }
        else if(source == jbn1) {
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
        JFrame frame = new JFrame();
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
