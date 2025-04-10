/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  ColorFunction
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.event.EventListenerList;

public class PalletCreator extends JPanel {
    public static Color[] RAINBOW = new Color[]{
		Color.RED,
		Color.YELLOW,
		Color.GREEN,
		Color.BLUE,
		new Color(138, 43, 226),
		new Color(75, 0, 130)
	};
    public static final int MAX_COLORS = RAINBOW.length;
    protected EventListenerList listenerList;
    protected JComboBox<Integer> colorCountComboBox;
    protected JCheckBox expandCheckBox;
    protected JCheckBox smoothCheckBox;
    protected JButton[] colorButtons;

    public PalletCreator() {
        this.initComponents();
    }

    public void initComponents() {
        this.colorCountComboBox = new JComboBox<Integer>();
        this.expandCheckBox = new JCheckBox("Expand", true);
        this.smoothCheckBox = new JCheckBox("Smooth", true);
        
		this.listenerList = new EventListenerList();
        this.setLayout(new BoxLayout(this, 1));
        this.add(this.colorCountComboBox);
        this.add(this.expandCheckBox);
        this.add(this.smoothCheckBox);
        this.colorButtons = new JButton[MAX_COLORS];
        for (int ii = 0; ii < this.colorButtons.length; ++ii) {
            this.colorCountComboBox.addItem(ii + 1);
            this.colorButtons[ii] = new JButton("" + ii);
            this.colorButtons[ii].setBackground(RAINBOW[ii]);
            this.colorButtons[ii].setOpaque(true);
            this.add(this.colorButtons[ii]);
        }
        this.colorCountComboBox.setSelectedIndex(this.colorCountComboBox.getItemCount() - 1);
        this.addListeners();
    }

    protected void addListeners() {
        final PalletCreator palletCreator = this;
        this.colorCountComboBox.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == 1 && itemEvent.getItem() instanceof Integer) {
                    int n = (Integer)itemEvent.getItem();
                    for (int i = 0; i < PalletCreator.this.colorButtons.length; ++i) {
                        PalletCreator.this.colorButtons[i].setVisible(i < n);
                        PalletCreator.this.validate();
                        PalletCreator.this.repaint();
                    }
                    PalletCreator.this.fireActionListenerEvent(new ActionEvent(palletCreator, 1001, "Update Colors"));
                }
            }
        });
        this.expandCheckBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PalletCreator.this.fireActionListenerEvent(new ActionEvent(palletCreator, 1001, "Update Colors"));
            }
        });
        this.smoothCheckBox.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PalletCreator.this.fireActionListenerEvent(new ActionEvent(palletCreator, 1001, "Update Colors"));
            }
        });
        for (int i = 0; i < this.colorButtons.length; ++i) {
            this.colorButtons[i].addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    JButton jButton = (JButton)actionEvent.getSource();
                    Color color = JColorChooser.showDialog(jButton, "Select Color", jButton.getBackground());
                    if (color != null) {
                        jButton.setBackground(color);
                        PalletCreator.this.fireActionListenerEvent(new ActionEvent(palletCreator, 1001, "Update Colors"));
                    }
                }
            });
        }
    }

    public ColorFunction getColorFunction() {
        int n;
        int n2 = (Integer)this.colorCountComboBox.getSelectedItem();
        Color[] colorArray = this.expandCheckBox.isSelected() ? new Color[2 * n2 - 1] : new Color[n2];
        for (n = 0; n < n2; ++n) {
            colorArray[n] = this.colorButtons[n].getBackground();
        }
        if (this.expandCheckBox.isSelected()) {
            for (n = 0; n < n2 - 1; ++n) {
                colorArray[n2 + n] = colorArray[n2 - n - 2];
            }
        }
        if (this.smoothCheckBox.isSelected()) {
            return new SmoothColorFunction(SmoothColorFunction.generateColorGradient(colorArray, 10));
        }
        return new PalletedColorFunction(PalletedColorFunction.generateColorGradient(colorArray, 10));
    }

    public void addActionListener(ActionListener actionListener) {
        this.listenerList.add(ActionListener.class, actionListener);
    }

    public void removeActionListener(ActionListener actionListener) {
        this.listenerList.remove(ActionListener.class, actionListener);
    }

    protected void fireActionListenerEvent(ActionEvent actionEvent) {
        ActionListener[] actionListenerArray = this.listenerList.getListeners(ActionListener.class);
        for (int i = 0; i < actionListenerArray.length; ++i) {
            actionListenerArray[i].actionPerformed(actionEvent);
        }
    }
}
