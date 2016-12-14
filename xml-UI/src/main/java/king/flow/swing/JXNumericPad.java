/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author liujin
 */
public class JXNumericPad extends JPanel {

    private static final String[] PAD_LAYOUT = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "X"};
    private JTextField target;

    public JXNumericPad() {
        init();
    }

    private void init() {
        setLayout(new GridLayout(4, 3));
        for (String value : PAD_LAYOUT) {
            final JButton button = new JButton(value);
            button.setActionCommand(value);
            add(button);
            button.addActionListener((ActionEvent e) -> {
                if (target == null) {
                    return;
                }
                String inputValue = e.getActionCommand();
                StringBuilder text = new StringBuilder(target.getText());
                switch (inputValue) {
                    case "X":
                        if (text.length() > 0) {
                            text.deleteCharAt(text.length() - 1);
                        }
                        break;
                    case "":
                        break;
                    default:
                        text.append(inputValue);
                        break;
                }
                target.setText(text.toString());
            });
        }
    }

    public void setTarget(JTextField input) {
        this.target = input;
    }

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        final JXNumericPad numericPad = new JXNumericPad();
        numericPad.setSize(300, 300);
        JTextField jTextField = new JTextField();
        numericPad.setTarget(jTextField);
        jFrame.getContentPane().add(jTextField, BorderLayout.NORTH);
        jFrame.getContentPane().add(numericPad, BorderLayout.CENTER);
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(300, 380);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
