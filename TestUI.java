import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.metal.*;

public class TestUI extends JPanel implements ActionListener, ChangeListener, AdjustmentListener {
    private static JFrame frame = new JFrame();
    private UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();
    private String LafClassName = UIManager.getSystemLookAndFeelClassName();
    private String[] items = {"item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8", "item9"};
    private JComboBox<String> jcb = new JComboBox<>(items);
    private JTextField jtf1 = new JTextField();
    private JTextField jtf2 = new JTextField();
    private JCheckBox jcb1 = new JCheckBox("CheckBox 1");
    private JCheckBox jcb2 = new JCheckBox("CheckBox 2");
    private JRadioButton jrb1 = new JRadioButton("RadioButton 1");
    private JRadioButton jrb2 = new JRadioButton("RadioButton 2");
    private JProgressBar jpb1 = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
    private JProgressBar jpb2 = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
    private JSlider slider1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 75);
    private JSlider slider2 = new JSlider(JSlider.VERTICAL, 0, 100, 75);
    private JSpinner spinner = new JSpinner(new SpinnerNumberModel(75, 0, 100, 1));
    private JScrollBar jsb = new JScrollBar(JScrollBar.HORIZONTAL, 75, 20, 0, 120);
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
        jcbd.addItem(" - - Click to select a Laf - - ");
        for(int i = 0;i < laf.length;i++) {
            jcbd.addItem(laf[i].getName());
        }
        jcbd.addItem("BeautyEye");
        jcbd.addItem("Darcula");
        jcbd.addItem("Material");
        jcbd.addItem("Quaqua");
        jcbd.addItem("WebLAF");
        jcbd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(jcbd.getSelectedIndex() > 0&&jcbd.getSelectedIndex() <= laf.length) {
                    LafClassName = laf[jcbd.getSelectedIndex()-1].getClassName();
                }
                else if(jcbd.getSelectedItem().toString() == "BeautyEye") {
                    LafClassName = "org.jb2011.lnf.beautyeye.BeautyEyeLookAndFeelCross";
                }
                else if(jcbd.getSelectedItem().toString() == "Darcula") {
                    LafClassName = "com.bulenkov.darcula.DarculaLaf";
                }
                else if(jcbd.getSelectedItem().toString() == "Material") {
                    LafClassName = "mdlaf.MaterialLookAndFeel";
                }
                else if(jcbd.getSelectedItem().toString() == "Quaqua") {
                    LafClassName = "ch.randelshofer.quaqua.QuaquaLookAndFeel";
                }
                else if(jcbd.getSelectedItem().toString() == "WebLAF") {
                    LafClassName = "com.alee.laf.WebLookAndFeel";
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

        // LafClassName
        JLabel jl1 = new JLabel("UI :");
        jl1.setBounds(10, 10, 30, 30);
        add(jl1);

        jtf1.setEditable(false);
        jtf1.setText(LafClassName);
        jtf1.setBounds(40, 10, 440, 30);
        add(jtf1);

        // ComboBox
        jcb.setBounds(490, 10, 100, 30);
        add(jcb);

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

        // ProgressBar1
        jpb1.setBounds(300, 50, 210, 30);
        jpb1.setValue(75);
        add(jpb1);

        // ProgressBar2
        jpb2.setBounds(520, 50, 30, 150);
        jpb2.setValue(75);
        add(jpb2);

        // Slider 1
        slider1.setBounds(300, 100, 210, 50);
        add(slider1);
        slider1.addChangeListener(this);

        // Slider 2
        slider2.setBounds(560, 50, 30, 150);
        add(slider2);
        slider2.addChangeListener(this);
        
        // Spinner
        spinner.setBounds(230, 170, 60, 30);
        add(spinner);
        spinner.addChangeListener(this);

        // ScrollBar
        jsb.setBounds(300, 175, 210, 20);
        add(jsb);
        jsb.addAdjustmentListener(this);

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
        if(source == slider1) {
            jpb1.setValue(slider1.getValue());
            jpb2.setValue(slider1.getValue());
            spinner.setValue(slider1.getValue());
            slider2.setValue(slider1.getValue());
            jsb.setValue(slider1.getValue());
        }
        if(source == slider2) {
            jpb1.setValue(slider2.getValue());
            jpb2.setValue(slider2.getValue());
            spinner.setValue(slider2.getValue());
            slider1.setValue(slider2.getValue());
            jsb.setValue(slider2.getValue());
        }
        else if(source == spinner) {
            jpb1.setValue((Integer)spinner.getValue());
            jpb2.setValue((Integer)spinner.getValue());
            slider1.setValue((Integer)spinner.getValue());
            slider2.setValue((Integer)spinner.getValue());
            jsb.setValue((Integer)spinner.getValue());
        }
    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
        Object source = e.getSource();
        if(source == jsb) {
            jpb1.setValue(jsb.getValue());
            jpb2.setValue(jsb.getValue());
            slider1.setValue(jsb.getValue());
            slider2.setValue(jsb.getValue());
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
