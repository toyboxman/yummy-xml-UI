/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import com.github.jsonj.JsonObject;
import com.github.jsonj.tools.JsonParser;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.plaf.FontUIResource;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.getWindowNode;
import static king.flow.common.CommonUtil.showMsg;
import king.flow.view.Action.SwipeIDCardAction;
import king.flow.view.UiStyle;
import king.flow.view.Window;

/**
 *
 * @author liujin
 */
public class ReadIDCardAction extends ReadCardAction {

    private final String mediaFile;
    private final String animationFile;
    private final SwipeIDCardAction.Debug debugMode;

    public ReadIDCardAction(int nextFocus, String media, String animation) {
        super(nextFocus);
        this.mediaFile = media;
        this.animationFile = animation;
        this.debugMode = null;
    }

    public ReadIDCardAction(int nextFocus, boolean editable, String media, String animation) {
        super(nextFocus, editable);
        this.mediaFile = media;
        this.animationFile = animation;
        this.debugMode = null;
    }

    public ReadIDCardAction(int nextFocus, String media, String animation, SwipeIDCardAction.Debug debugMode) {
        super(nextFocus);
        this.mediaFile = media;
        this.animationFile = animation;
        this.debugMode = debugMode;
    }

    public ReadIDCardAction(int nextFocus, boolean editable, String media, String animation, SwipeIDCardAction.Debug debugMode) {
        super(nextFocus, editable);
        this.mediaFile = media;
        this.animationFile = animation;
        this.debugMode = debugMode;
    }

    @Override
    protected void limitUserInput() {
        if (debugMode == null || debugMode.isLimit()) {
            setEditorDocument();
        }
    }

    @Override
    protected void readCard(String value) {
        progressTip = new JLabel(getResourceMsg("operation.id.card.insert.prompt"));
        Window windowNode = getWindowNode();
        UiStyle uiStyle = windowNode.getUiStyle();
        if (uiStyle != null && uiStyle.getFont() != null && uiStyle.getFont().getName() != null) {
            progressTip.setFont(new FontUIResource(uiStyle.getFont().getName(), java.awt.Font.BOLD, 50));
        } else {
            progressTip.setFont(new FontUIResource("Dialog", java.awt.Font.BOLD, 50));
        }
        progressTip.setHorizontalAlignment(SwingConstants.CENTER);
        progressTip.setVerticalAlignment(SwingConstants.BOTTOM);
//        progressTip.setBorder(new LineBorder(Color.RED, 2));
        final JDialog progressAnimation = buildAnimationDialog(this.animationFile);
        progressTip.setBounds(0, 120, progressAnimation.getBounds().width, 80);
        progressAnimation.getContentPane().add(progressTip, 1);
        final ImageIcon bgImage = CommonUtil.getImageIcon("/image/2.jpg");
        //final ImageIcon bgImage = CommonUtil.getDefaultBackgroundImage();
        if (bgImage != null) {
            progressAnimation.getContentPane().add(new JLabel(bgImage), 2);//this is pad label, otherwise progressTip will become fullscreen bound
        } else {
            progressAnimation.getContentPane().add(new JLabel(), 2);//this is pad label, otherwise progressTip will become fullscreen bound
        }

        waitCommunicationTask(new SwipeIDCardTask(value), progressAnimation);
    }

    protected JLabel progressTip;

    private class SwipeIDCardTask extends SwingWorker<String, Integer> {

        private final String actionCommand;
        int cardReadingState = 0;

        public SwipeIDCardTask(String actionCommand) {
            this.actionCommand = actionCommand;
        }

        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(1000);
            switch (actionCommand) {
                case "ACTION5":
                    String cardInfo = null;
                    JsonParser jsonParser = new JsonParser();
                    JsonObject jsonElement = null;

                    cardInfo = CommonUtil.swipeIDCard();// driver will blocking thread and wait IC card information return
                    if (cardInfo == null || cardInfo.length() == 0) {
                        //fail to read card information
                        getLogger(SwipeIDCardTask.class.getName()).log(Level.WARNING,
                                "No ID card information is read, probably card is invalid");
                        return null;
                    } else {
                        getLogger(SwipeIDCardTask.class.getName()).log(Level.INFO,
                                "Reading information from ID card:\n{0}", cardInfo);
                        try {
                            jsonElement = jsonParser.parse(cardInfo).asObject();
                        } catch (Exception e) {
                            return null;
                        }
                        return cardInfo;
                    }
                default:
                    getLogger(SwipeIDCardTask.class.getName()).log(Level.WARNING,
                            "Unknown swipe card action {0} happens in ID reading device", actionCommand);
            }
            return null;
        }

        @Override
        protected void done() {
            try {
                String cardInfo = get();
                if (cardInfo == null) {
                    showMsg(owner.getTopLevelAncestor(),
                            getResourceMsg("operation.id.card.read.error"));
                } else {
                    owner.setSelectedItem(cardInfo);
                    if (nextFocus != id) {
                        owner.setFocusable(false); //prevent refocus action from playing wav file
                        getBlock(nextFocus, JComponent.class).requestFocusInWindow(); // switch focus
                        owner.setFocusable(true);  //restore owner focus capability
                    }
                }
            } catch (InterruptedException | ExecutionException ex) {
                getLogger(ReadCardAction.class.getName()).log(Level.SEVERE,
                        "fail to read card information due to error {0}", ex.getMessage());
                showMsg(owner.getTopLevelAncestor(),
                        getResourceMsg("operation.id.card.read.error"));
            }
        }

    }
}
