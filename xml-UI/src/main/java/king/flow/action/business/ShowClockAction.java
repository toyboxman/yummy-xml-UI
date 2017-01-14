/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import com.github.jsonj.JsonObject;
import com.github.jsonj.tools.JsonParser;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.swing.JLabel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import king.flow.action.DefaultBaseAction;
import static king.flow.common.CommonUtil.getLogger;

/**
 *
 * @author liujin
 */
public class ShowClockAction extends DefaultBaseAction {

    private final String dateFormat;
    private final Integer countDown;

    public ShowClockAction() {
        dateFormat = "%1$tc";
        countDown = null;
    }

    public ShowClockAction(String dateFormat) {
        this.dateFormat = dateFormat;
        Integer startPoint = null;
        try {
            JsonObject element = new JsonParser().parse(dateFormat).asObject();
            startPoint = element.getInt(COUNT_DOWN);
        } catch (Exception e) {
            getLogger(ShowClockAction.class.getName())
                    .log(Level.WARNING, "Invalid countDown clock format {0}, if you hope to set countDown clock, the correct format should be {1}",
                            new String[]{dateFormat, String.format("{\"%s\":60}", COUNT_DOWN)});
        }
        countDown = startPoint;
    }
    private static final String COUNT_DOWN = "countDown";

    @Override
    public void initializeData() {
        JLabel clock = (JLabel) owner;
        if (countDown != null) {
            clock.setText(String.valueOf(countDown));
        } else {
            clock.setText(showDate());
        }
    }

    @Override
    protected void installClockAction() {
        if (countDown != null) {
            owner.addAncestorListener(new CountDownClock());
        } else {
            owner.addAncestorListener(new TimeClock());
        }
    }

    private String showDate() {
        return String.format(Locale.getDefault(), dateFormat, System.currentTimeMillis());
    }

    private class TimeClock implements AncestorListener {

        private final JLabel clock;

        public TimeClock() {
            this.clock = (JLabel) owner;
        }
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
    }

    private class CountDownClock implements AncestorListener {

        private final JLabel clock;

        public CountDownClock() {
            this.clock = (JLabel) owner;
        }
        private Timer clockTimer = null;

        @Override
        public void ancestorAdded(AncestorEvent event) {
            clockTimer = new Timer();
            clockTimer.scheduleAtFixedRate(new TimerTask() {

                private int startPoint = countDown;

                @Override
                public void run() {
                    if (startPoint < 0) {
                        clockTimer.cancel();
                        return;
                    }
                    clock.setText(String.valueOf(startPoint--));
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
    }
}
