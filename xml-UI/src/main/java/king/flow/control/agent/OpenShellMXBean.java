/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.agent;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import static java.awt.event.KeyEvent.VK_ALT;
import static java.awt.event.KeyEvent.VK_TAB;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import king.flow.common.CommonConstants;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getCurrentView;
import static king.flow.common.CommonUtil.getLogger;
import king.flow.view.Component;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author liujin
 */
public class OpenShellMXBean implements OpenCLI {

    private static final Logger LOGGER = getLogger(OpenShellMXBean.class.getName());
    public static final String OPEN_SHELL_JMX_BEAN_NAME = "king.flow.control.agent:Name=OpenShellMXBean,Type=OpenCLI";
    private static final String TRIGGER_KEY = "trigger";
    private static final String DISPLAY_KEY = "display";
    private final Map<Integer, Object> building_blocks;
    private final Map<Integer, Object> meta_blocks;

    public OpenShellMXBean(Map<Integer, Object> building_blocks, Map<Integer, Object> meta_blocks) {
        this.building_blocks = building_blocks;
        this.meta_blocks = meta_blocks;
    }

    @Override
    public void showApp(String jsonData) {
        LOGGER.log(Level.INFO,
                "Try to show bankApp window via OpenShell with parameter:\n{0}", jsonData);
        Window window = getCurrentView();
        if (window != null) {
            window.setVisible(true);
            window.setAutoRequestFocus(true);
            window.setAlwaysOnTop(true);
            window.toFront();
            window.requestFocus();
            window.setAlwaysOnTop(false);
            window.repaint();
            //switchApp();

            //supported avaiable json parameter format like this:
            //{"trigger":111, "display":{"112":"$100", "113":"-200", ...}}
            if (jsonData != null && jsonData.length() > 0) {
                LOGGER.log(Level.INFO,
                        "Parse showApp parameter :\n{0}", jsonData);
                JSONParser jsonParser = new JSONParser();
                try {
                    JSONObject parameters = (JSONObject) jsonParser.parse(jsonData);
                    if (parameters.isEmpty()) {
                        //ignore empty json command parameter
                        return;
                    }

                    if (parameters.containsKey(DISPLAY_KEY)) {
                        JSONObject displayParameters = (JSONObject) parameters.get(DISPLAY_KEY);
                        Set<Map.Entry<String, String>> displayParameterSet = displayParameters.entrySet();
                        for (Map.Entry<String, String> parameter : displayParameterSet) {
                            String name = parameter.getKey();
                            try {
                                int id = Integer.parseInt(name);
                                String value = parameter.getValue();
                                Component meta = (Component) meta_blocks.get(id);
                                if (meta == null) {
                                    LOGGER.log(Level.WARNING,
                                            "showApp display parameter[id={0}] is invalid, no component could be found", id);
                                    continue;
                                }
                                switch (meta.getType()) {
                                    case LABEL:
                                        JLabel label = (JLabel) building_blocks.get(meta.getId());
                                        label.setText(value);
                                        break;
                                    case TEXT_FIELD:
                                        JTextField textField = (JTextField) building_blocks.get(meta.getId());
                                        textField.setText(value);
                                        break;
                                    default:
                                        LOGGER.log(Level.WARNING,
                                                "Unsupported display parameter component type : {0}", meta.getType());
                                }
                            } catch (NumberFormatException numberFormatException) {
                                LOGGER.log(Level.WARNING,
                                        "display parameter parsing failure due to : \n{0}", numberFormatException.getMessage());
                                continue;
                            }
                        }
                    }

                    if (parameters.containsKey(TRIGGER_KEY)) {
                        String triggerId = (String) parameters.get(TRIGGER_KEY);
                        try {
                            int trigger = (int) Long.parseLong(triggerId);//simple json only support long type
                            if (meta_blocks.get(trigger) != null) {
                                final Component blockMeta = (Component) meta_blocks.get(trigger);
                                switch (blockMeta.getType()) {
                                    case COMBO_BOX:
                                        JComboBox comboBlock = (JComboBox) building_blocks.get(trigger);
                                        ItemListener[] itemListeners = comboBlock.getItemListeners();
                                        ItemEvent itemEvent = new ItemEvent(comboBlock,
                                                ItemEvent.ITEM_STATE_CHANGED,
                                                comboBlock.getItemAt(comboBlock.getItemCount() - 1).toString(),
                                                ItemEvent.SELECTED);
                                        for (ItemListener itemListener : itemListeners) {
                                            itemListener.itemStateChanged(itemEvent);//wait and hang on util progress dialog gets to dispose
                                        }
                                        break;
                                    case BUTTON:
                                        JButton btnBlock = (JButton) building_blocks.get(trigger);
                                        btnBlock.doClick();
                                        break;
                                    default:
                                        LOGGER.log(Level.WARNING,
                                                "Invalid trigger parameter component[{0}] as unsupported type[{1}]",
                                                new Object[]{trigger, blockMeta.getType()});
                                        break;
                                }
                            }
                        } catch (NumberFormatException numberFormatException) {
                            LOGGER.log(Level.WARNING,
                                    "trigger parameter parsing failure due to : \n{0}", numberFormatException.getMessage());
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(OpenShell.class.getName()).log(Level.WARNING,
                            "json-parsing hits a problem due to :\n{0}", ex.getMessage());
                }
            }
        } else {
            LOGGER.log(Level.INFO,
                    "Fail to show because bankApp probably failed to start up and no window can be found");
        }
    }

    @Override
    public void hideApp() {
        LOGGER.log(Level.INFO,
                "Try to hide bankApp window via OpenShell");
        Window window = getCurrentView();
        if (window != null) {
            window.setVisible(false);
        } else {
            LOGGER.log(Level.INFO,
                    "Fail to hide because bankApp probably failed to start up and no window can be found");
        }
    }

    @Override
    public String getVersion() {
        return CommonConstants.VERSION;
    }

    //how to switch to visible bankApp? toFront just works fine in JVM domain not in window7
    //Robot is used to generate native system input events for the purposes of test automation,
    //self-running demos, and other applications where control of the mouse and keyboard is needed
    //http://stackoverflow.com/questions/2694365/can-i-move-another-programs-window-to-the-front-of-focus
    public void switchApp() {
        try {
            final Robot robot = createRobot();
            robot.keyPress(VK_ALT);
            for (int i = 0; i < 1; i++) {
                robot.keyPress(VK_TAB);
                robot.keyRelease(VK_TAB);
                robot.delay(500);
            }
            robot.keyRelease(VK_ALT);
        } catch (AWTException ex) {
            Logger.getLogger(OpenShell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Robot createRobot() throws AWTException {
        final Robot robot = new Robot();
        robot.setAutoWaitForIdle(true);
        robot.setAutoDelay(10);
        return robot;
    }

    @Override
    public String showAppInfo() {
        return CommonUtil.showSystemInfo();
    }
}
