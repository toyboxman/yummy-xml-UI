/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javafx.scene.paint.Color;
import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import king.flow.common.CommonConstants;
import king.flow.net.TunnelBuilder;
import org.jdesktop.swingx.JXLabel;

/**
 *
 * @author king
 */
public class ProgressLabel extends JXLabel {
    
    private int timeout = TunnelBuilder.getTunnelBuilder().getChannelTimeout();
    private HashMap<TextAttribute, Object> attributes;
    
    public ProgressLabel() {
        super(UIManager.getIcon(CommonConstants.KING_FLOW_PROGRESS));
    }
    
    public ProgressLabel showCountDown() {
        if (attributes != null) {
            return this;
        }
        this.addAncestorListener(new AncestorListenerImpl(this));
        attributes = new HashMap<>();
        attributes.put(TextAttribute.FOREGROUND, Color.RED);
        attributes.put(TextAttribute.SIZE, 40);
        attributes.put(TextAttribute.FAMILY, "Arial");
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_ULTRABOLD);
        return this;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D tmp = (Graphics2D) g.create();
        if (attributes != null) {
            try {
//            tmp.drawString(String.valueOf(timeout), 105, 135);
                tmp.drawString(makeACI(), 95, 140);
            } catch (Exception e) {
            } finally {
                tmp.dispose();
            }
        }
    }
    
    private AttributedCharacterIterator makeACI() {
        return new AttributedString(String.valueOf(timeout), attributes).getIterator();
    }
    
    private int countDown() {
        if (timeout > 0) {
            timeout--;
        }
        return timeout;
    }
    
    private static class AncestorListenerImpl implements AncestorListener {
        
        private final ProgressLabel parent;
        private Timer countDownTimer = null;
        
        public AncestorListenerImpl(ProgressLabel parent) {
            this.parent = parent;
        }
        
        @Override
        public void ancestorAdded(AncestorEvent event) {
            countDownTimer = new Timer();
            countDownTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    parent.repaint();
                    parent.countDown();
                }
            }, 0, TimeUnit.SECONDS.toMillis(1));
        }
        
        @Override
        public void ancestorRemoved(AncestorEvent event) {
            countDownTimer.cancel();
        }
        
        @Override
        public void ancestorMoved(AncestorEvent event) {
            
        }
    }
    
}
