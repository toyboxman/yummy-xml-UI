/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.lookandfeel;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.synth.SynthButtonUI;
import javax.swing.plaf.synth.SynthContext;

/**
 *
 * @author LiuJin
 */
public class BankSynthArrowButton extends JButton implements SwingConstants, UIResource {

    private int direction;

    public BankSynthArrowButton(int direction) {
        super();
        super.setFocusable(false);
        setDirection(direction);
        setDefaultCapable(false);
    }

    public String getUIClassID() {
        return "ArrowButtonUI";
    }

    public void updateUI() {
        setUI(new BankSynthArrowButtonUI());
    }

    public void setDirection(int dir) {
        direction = dir;
        putClientProperty("__arrow_direction__", Integer.valueOf(dir));
        repaint();
    }

    public int getDirection() {
        return direction;
    }

    public void setFocusable(boolean focusable) {
    }

    private static class BankSynthArrowButtonUI extends SynthButtonUI {

        protected void paint(SynthContext context, Graphics g) {
            BankSynthArrowButton button = (BankSynthArrowButton) context.
                    getComponent();
            context.getStyle().getPainter(context).paintArrowButtonForeground(
                    context, g, 0, 0, button.getWidth(), button.getHeight(),
                    button.getDirection());
        }

        void paintBackground(SynthContext context, Graphics g, JComponent c) {
            context.getStyle().getPainter(context).paintArrowButtonBackground(context, g, 0, 0,
                    c.getWidth(), c.getHeight());
        }

        public void paintBorder(SynthContext context, Graphics g, int x,
                int y, int w, int h) {
            context.getStyle().getPainter(context).paintArrowButtonBorder(context, g, x, y, w, h);
        }

        public Dimension getMinimumSize() {
            return new Dimension(5, 5);
        }

        public Dimension getMaximumSize() {
            return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        public Dimension getPreferredSize(JComponent c) {
            SynthContext context = getContext(c);
            Dimension dim = null;
            if (context.getComponent().getName() == "ScrollBar.button") {
                // ScrollBar arrow buttons can be non-square when
                // the ScrollBar.squareButtons property is set to FALSE
                // and the ScrollBar.buttonSize property is non-null
                dim = (Dimension) context.getStyle().get(context, "ScrollBar.buttonSize");
            }
            if (dim == null) {
                // For all other cases (including Spinner, ComboBox), we will
                // fall back on the single ArrowButton.size value to create
                // a square return value
                int size
                        = context.getStyle().getInt(context, "ArrowButton.size", 16);
                dim = new Dimension(size, size);
            }

            // handle scaling for sizeVarients for special case components. The
            // key "JComponent.sizeVariant" scales for large/small/mini
            // components are based on Apples LAF
            Container parent = context.getComponent().getParent();
            if (parent instanceof JComponent && !(parent instanceof JComboBox)) {
                Object scaleKey = ((JComponent) parent).
                        getClientProperty("JComponent.sizeVariant");
                if (scaleKey != null) {
                    if ("large".equals(scaleKey)) {
                        dim = new Dimension(
                                (int) (dim.width * 1.15),
                                (int) (dim.height * 1.15));
                    } else if ("small".equals(scaleKey)) {
                        dim = new Dimension(
                                (int) (dim.width * 0.857),
                                (int) (dim.height * 0.857));
                    } else if ("mini".equals(scaleKey)) {
                        dim = new Dimension(
                                (int) (dim.width * 0.714),
                                (int) (dim.height * 0.714));
                    }
                }
            }

            context = null;
            return dim;
        }
    }
}
