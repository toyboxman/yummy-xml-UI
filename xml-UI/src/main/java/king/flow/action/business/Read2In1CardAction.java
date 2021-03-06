/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import com.github.jsonj.JsonObject;
import com.github.jsonj.tools.JsonParser;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.plaf.FontUIResource;
import king.flow.common.AudioPlayer;
import static king.flow.common.AudioPlayer.getAudioPlayer;
import king.flow.common.CommonConstants;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.getWindowNode;
import static king.flow.common.CommonUtil.showMsg;
import static king.flow.common.CommonUtil.swipe2In1Card;
import king.flow.control.driver.TwoInOneCardConductor;
import king.flow.data.TLSResult;
import king.flow.view.Action.Swipe2In1CardAction;
import king.flow.view.UiStyle;
import king.flow.view.Window;

/**
 *
 * @author liujin
 */
public class Read2In1CardAction extends ReadCardAction {

    private final String mediaFile;
    private final String animationFile;
    private final Swipe2In1CardAction.Debug debugMode;

    public Read2In1CardAction(int nextFocus, String media, String animation) {
        super(nextFocus);
        this.mediaFile = media;
        this.animationFile = animation;
        this.debugMode = null;
    }

    public Read2In1CardAction(int nextFocus, boolean editable, String media, String animation) {
        super(nextFocus, editable);
        this.mediaFile = media;
        this.animationFile = animation;
        this.debugMode = null;
    }

    public Read2In1CardAction(int nextFocus, String media, String animation, Swipe2In1CardAction.Debug debugMode) {
        super(nextFocus);
        this.mediaFile = media;
        this.animationFile = animation;
        this.debugMode = debugMode;
    }

    public Read2In1CardAction(int nextFocus, boolean editable, String media, String animation, Swipe2In1CardAction.Debug debugMode) {
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
        progressTip = new JLabel(getResourceMsg("operation.card.insert.prompt"));
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

        waitCommunicationTask(new Swipe2In1CardTask(value), progressAnimation);
    }

    protected JLabel progressTip;

    private class Swipe2In1CardTask extends SwingWorker<String, Integer> {

        private final String actionCommand;
        int cardReadingState = 0;

        public Swipe2In1CardTask(String actionCommand) {
            this.actionCommand = actionCommand;
        }

        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(1000);
            switch (actionCommand) {
                case "ACTION4":
                    String cardInfo = null;
                    if (debugMode != null) {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                        cardReadingState = CommonConstants.MAGNET_CARD_STATE;
                    } else {
                        cardReadingState = CommonUtil.check2In1Card();
                    }

                    JsonParser jsonParser = new JsonParser();
                    JsonObject jsonElement = null;
                    switch (cardReadingState) {
                        case CommonConstants.MAGNET_CARD_STATE:
                            // should show tip to user and play audio prompt
                            progressTip.setText(getResourceMsg("operation.card.draw.prompt"));
                            if (mediaFile != null) {
                                AudioPlayer audioPlayer = getAudioPlayer(new File(mediaFile));
                                if (audioPlayer != null) {
                                    audioPlayer.play();
                                } else {
                                    getLogger(Swipe2In1CardTask.class.getName()).log(Level.WARNING, "Fail to play media file {0}", mediaFile);
                                }
                            }

                            if (debugMode != null) {
                                Thread.sleep(TimeUnit.SECONDS.toMillis(4));
                                return debugMode.getCardId();
                            }

                            cardInfo = swipe2In1Card(CommonConstants.MAGNET_CARD_STATE);// driver will blocking thread and wait magnet card information return
                            if (cardInfo == null || cardInfo.length() == 0) {
                                //fail to read card information
                                getLogger(Swipe2In1CardTask.class.getName()).log(Level.WARNING,
                                        "No magnet card information is read, probably card is invalid", cardReadingState);
                                return null;
                            } else {
                                getLogger(Swipe2In1CardTask.class.getName()).log(Level.INFO,
                                        "Reading information {0} from magnet card", cardInfo);
                                // need to change raw card information format
                                //return cardInfo.trim().substring(0, cardInfo.indexOf('='));
                                try {
                                    jsonElement = jsonParser.parse(cardInfo).asObject();
                                    CommonUtil.putCargo(TLSResult.UNIONPAY_CARD_INFO, cardInfo);
                                } catch (Exception e) {
                                    return null;
                                }
                                return jsonElement.getString(TwoInOneCardConductor.CARD_NO);
                            }
                        case CommonConstants.IC_CARD_STATE:
                            if (debugMode != null) {
                                Thread.sleep(TimeUnit.SECONDS.toMillis(2));
                                return debugMode.getCardId();
                            }

                            cardInfo = swipe2In1Card(CommonConstants.IC_CARD_STATE);// driver will blocking thread and wait IC card information return
                            if (cardInfo == null || cardInfo.length() == 0) {
                                //fail to read card information
                                getLogger(Swipe2In1CardTask.class.getName()).log(Level.WARNING,
                                        "No IC card information is read, probably card is invalid", cardReadingState);
                                return null;
                            } else {
                                getLogger(Swipe2In1CardTask.class.getName()).log(Level.INFO,
                                        "Reading information {0} from IC card", cardInfo);
                                // need to change raw card information format
                                //return cardInfo.trim();
                                try {
                                    jsonElement = jsonParser.parse(cardInfo).asObject();
                                    CommonUtil.putCargo(TLSResult.UNIONPAY_CARD_INFO, cardInfo);
                                } catch (Exception e) {
                                    return null;
                                }
                                return jsonElement.getString(TwoInOneCardConductor.CARD_NO);
                            }
                        case CommonConstants.INVALID_CARD_STATE:
                            break;
                        default:
                            getLogger(Swipe2In1CardTask.class.getName()).log(Level.WARNING,
                                    "Unknown card-checking state {0} happens in 2in1 reading device", cardReadingState);
                    }
                    break;
                default:
                    getLogger(Swipe2In1CardTask.class.getName()).log(Level.WARNING,
                            "Unknown swipe card action {0} happens in 2in1 reading device", actionCommand);
            }
            return null;
        }

        @Override
        protected void done() {
            try {
                String cardNumber = get();
                if (cardNumber == null) {
                    CommonUtil.cleanTranStation(CommonConstants.VALID_BANK_CARD);
                    switch (cardReadingState) {
                        case CommonConstants.MAGNET_CARD_STATE:
                            showMsg(owner.getTopLevelAncestor(),
                                    getResourceMsg("operation.magnet.card.read.error"));
                            return;
                        case CommonConstants.IC_CARD_STATE:
                            showMsg(owner.getTopLevelAncestor(),
                                    getResourceMsg("operation.ic.card.read.error"));
                            return;
                        default:
                            showMsg(owner.getTopLevelAncestor(),
                                    getResourceMsg("operation.card.read.error"));
                    }
                } else {
                    owner.setSelectedItem(cardNumber);
                    CommonUtil.putCargo(CommonConstants.VALID_BANK_CARD, cardNumber);
                    if (nextFocus != id) {
                        owner.setFocusable(false); //prevent refocus action from playing wav file
                        getBlock(nextFocus, JComponent.class).requestFocusInWindow(); // switch focus
                        owner.setFocusable(true);  //restore owner focus capability
                    }
                }
            } catch (InterruptedException | ExecutionException ex) {
                CommonUtil.cleanTranStation(CommonConstants.VALID_BANK_CARD);
                getLogger(ReadCardAction.class.getName()).log(Level.SEVERE,
                        "fail to read card information due to error {0}", ex.getMessage());
                showMsg(owner.getTopLevelAncestor(),
                        getResourceMsg("operation.card.read.error"));
            }
        }

    }
}
