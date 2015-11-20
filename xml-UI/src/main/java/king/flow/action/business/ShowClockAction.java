/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import king.flow.action.DefaultBaseAction;

/**
 *
 * @author liujin
 */
public class ShowClockAction extends DefaultBaseAction {

    private final String DateFormat;

    public ShowClockAction() {
        DateFormat = "%1$tc";
    }

    public ShowClockAction(String DateFormat) {
        this.DateFormat = DateFormat;
    }

    @Override
    public void initializeData() {
        JLabel clock = (JLabel) owner;
        clock.setText(showDate());
    }

    @Override
    protected void installClockAction() {
        JLabel clock = (JLabel) owner;
        owner.addAncestorListener(new AncestorListener() {
            private Timer clockTimer = null;

            @Override
            public void ancestorAdded(AncestorEvent event) {
                clock.setText(showDate());
                clockTimer = new Timer();
                clockTimer.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        clock.setText(showDate());
                    }
                }, 0, TimeUnit.SECONDS.toMillis(1));
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                clockTimer.cancel();
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
            }
        });

    }

    private String showDate() {
        return String.format(Locale.getDefault(), DateFormat, System.currentTimeMillis());
    }
}
