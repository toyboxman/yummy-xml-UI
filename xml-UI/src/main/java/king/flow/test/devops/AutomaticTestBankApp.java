/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.test.devops;

import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.Window;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.xml.bind.JAXBException;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.TerminalStatus.RUNNING;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.getTerminalStatus;
import static king.flow.common.CommonUtil.parseString2Date;
import king.flow.control.BankAppStarter;
import king.flow.test.devops.define.TestCase;
import king.flow.test.devops.define.TestStep;
import king.flow.test.devops.define.TestSuits;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author liujin
 */
public class AutomaticTestBankApp {

    private static final String TEST_SUITS_PATH = "./test/test_suits.xml";

    public static void main(String[] args) {
        setup();

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                bankAppStarter.start();
            }
        });

//        sleep(9);
        checkStartup();

        parseTestSuits();

        if (testsuits.getLoop() != null) {
            for (int i = 0; i < testsuits.getLoop(); i++) {
                runTestSuits();
            }

            sleep(3);
            System.exit(0);
        } else {
            AtomicInteger loopTime = new AtomicInteger(1);
            while (true) {
                getLogger(AutomaticTestBankApp.class.getName()).log(Level.INFO,
                        "\n/~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*\nRun the {0} cycle of testsuits...",
                        loopTime.getAndIncrement());
                try {
                    runTestSuits();
                } catch (Throwable throwable) {
                    getLogger(AutomaticTestBankApp.class.getName()).log(Level.SEVERE, null, throwable);
                }
            }
        }
    }

    private static void parseTestSuits() {
        try {
            testsuits = new TestProcessor(TEST_SUITS_PATH).init().parseTestSuits();
            for (String testcasePath : testsuits.getTestcasePath()) {
                if (testcasePath == null || testcasePath.length() == 0) {
                    exitWithErr(new StringBuilder("Empty testcase path is set in test_suits.xml").toString());
                }
                TestCase tcase = new TestProcessor(testcasePath).init().parseTestCase();
                testCaseList.add(tcase);
            }
        } catch (JAXBException ex) {
            getLogger(AutomaticTestBankApp.class.getName()).log(Level.SEVERE, null, ex);
            exitWithErr("test_suits.xml has an fatal error");
        }
    }

    private static TestSuits testsuits;

    private static void runTestSuits() throws HeadlessException {
        for (TestCase testcase : testCaseList) {
            runTestCase(testcase);
        }
    }

    private static void runTestCase(TestCase testcase) throws HeadlessException {
        for (TestStep step : testcase.getSteps().getStep()) {
            runStep(step);
        }
    }

    private static void runStep(TestStep step) throws HeadlessException {
        int componentId = step.getComponent();
        Object metaData = bankAppStarter.retrieveComponentMeta(componentId);
        if (metaData == null) {
            exitWithErr(new StringBuilder("No component can be found by erroneous component id ")
                    .append(componentId).toString());
        }
        if (metaData instanceof king.flow.view.Component) {
            king.flow.view.Component component = (king.flow.view.Component) metaData;
            Component block = bankAppStarter.retrieveComponent(componentId, Component.class);
            if (!block.isShowing()) {
                exitWithErr(mouldComponentErr(component)
                        .append("is not showing now, any operation on it is invalid").toString());
            }
            switch (component.getType()) {
                case BUTTON:
                    doButtonAction(step);
                    break;
                case TEXT_FIELD:
                case PASSWORD_FIELD:
                    doTextFieldAction(step);
                    break;
                case COMBO_BOX:
                    doComboBoxAction(step);
                    break;
                case DATE:
                    doDatePickerAction(step);
                    break;
                default:
                    exitWithErr(mouldComponentErr(component)
                            .append("is unsupported component type").toString());
            }
        } else {
            exitWithErr(new StringBuilder("Component[ID:").append(componentId)
                    .append(", Type:").append(metaData.getClass().getName()).append("]")
                    .append(System.lineSeparator())
                    .append("is invalid component").toString());
        }
    }

    private static StringBuilder mouldComponentErr(king.flow.view.Component component) {
        StringBuilder err = new StringBuilder("Component[ID:").append(component.getId())
                .append(", Type:").append(component.getType()).append("]").append(System.lineSeparator());
        return err;
    }

    private static void doDatePickerAction(TestStep step) throws HeadlessException {
        int componentId = step.getComponent();
        TestStep.Action action = step.getAction();
        JXDatePicker datePicker = bankAppStarter.retrieveComponent(componentId, JXDatePicker.class);
        switch (action.getName()) {
            case PICK_DATE:
                sleep(step.getPreWait());
                Date date = Calendar.getInstance().getTime();
                try {
                    date = parseString2Date(action.getParameter());
                } catch (ParseException ex) {
                    exitWithErr(new StringBuilder("Illegal parameter setting in DatePicker PickDate action, it must be yyyy-MM-dd. ")
                            .append(" with component ID ")
                            .append(componentId).toString());
                }
                datePicker.setDate(date);
                sleep(step.getPostWait());
                break;
            default:
                exitWithErr(new StringBuilder("Unsupported DatePicker action ")
                        .append(action.getName())
                        .append(" with component ID ")
                        .append(componentId).toString());
        }
    }

    private static void doComboBoxAction(TestStep step) throws HeadlessException {
        int componentId = step.getComponent();
        TestStep.Action action = step.getAction();
        JComboBox combo = bankAppStarter.retrieveComponent(componentId, JComboBox.class);
        switch (action.getName()) {
            case SELECT_VALUE:
                sleep(step.getPreWait());
                int selectedValue = 0;
                try {
                    selectedValue = Integer.valueOf(action.getParameter());
                } catch (Exception e) {
                    exitWithErr(new StringBuilder("Illegal parameter setting in ComboBox SelectValue action, it must be integer. ")
                            .append(" with component ID ")
                            .append(componentId).toString());
                }
                combo.setSelectedIndex(selectedValue);
                sleep(step.getPostWait());
                break;
            case FILL_VALUE:
                sleep(step.getPreWait());
                combo.getEditor().setItem(action.getParameter());
                sleep(step.getPostWait());
                break;
            default:
                exitWithErr(new StringBuilder("Unsupported ComboBox action ")
                        .append(action.getName())
                        .append(" with component ID ")
                        .append(componentId).toString());
        }
    }

    private static void doTextFieldAction(TestStep step) throws HeadlessException {
        int componentId = step.getComponent();
        TestStep.Action action = step.getAction();
        JTextField text = bankAppStarter.retrieveComponent(componentId, JTextField.class);
        switch (action.getName()) {
            case FILL_VALUE:
                sleep(step.getPreWait());
                text.setText(action.getParameter());
                sleep(step.getPostWait());
                break;
            default:
                exitWithErr(new StringBuilder("Unsupported TextField action ")
                        .append(action.getName())
                        .append(" with component ID ")
                        .append(componentId).toString());
        }
    }

    private static void doButtonAction(TestStep step) throws HeadlessException {
        int componentId = step.getComponent();
        TestStep.Action action = step.getAction();
        JButton button = bankAppStarter.retrieveComponent(componentId, JButton.class);
        switch (action.getName()) {
            case CLICK:
                sleep(step.getPreWait());
                button.doClick(BUTTON_PRESS_TIME);
                sleep(step.getPostWait());
                break;
            default:
                exitWithErr(new StringBuilder("Unsupported Button action ")
                        .append(action.getName())
                        .append(" with component ID ")
                        .append(componentId).toString());
        }
    }

    private static List<TestCase> testCaseList = new ArrayList<>();

    private static void exitWithErr(String err) throws HeadlessException {
        getLogger(AutomaticTestBankApp.class.getName()).warning(err);
        JOptionPane.showMessageDialog(CommonUtil.getCurrentView(), new StringBuilder(err).append('\n').append("Please read log file to get details").toString());
        System.exit(1);
    }

    private static void sleep(int seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (InterruptedException ex) {
            getLogger(AutomaticTestBankApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void checkStartup() {
        while (!isStartupCompleted()) {
            for (Window window : Window.getWindows()) {
                if (window.isShowing()) {
                    if (window instanceof JDialog) {
                        JDialog activeWindow = (JDialog) window;
                        if (activeWindow.getTitle() == null) {
                            continue;
                        } else if (activeWindow.getTitle().equals(BANK_APP_INFO_DIALOG_TITLE)) {
                            getLogger(AutomaticTestBankApp.class.getName()).log(Level.INFO, "Ignore download key message box");
                            nodToInfoDialog(activeWindow);
                        }
                    }
                }
            }
        }
    }

    private static boolean isStartupCompleted() {
        return getTerminalStatus() == RUNNING ? true : false;
    }

    private static void nodToInfoDialog(JDialog activeWindow) {
        Component[] components = activeWindow.getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JOptionPane) {
                Component[] subComponents = ((JOptionPane) component).getComponents();
                Component subContainer = subComponents[subComponents.length - 1];
                if (subContainer instanceof JPanel) {
                    for (Component subComponent : ((JPanel) subContainer).getComponents()) {
                        if (subComponent instanceof JButton) {
                            JButton button = (JButton) subComponent;
                            if (button.getText().equals(OK)) {
                                button.doClick(BUTTON_PRESS_TIME);
                            }
                        }
                    }
                }
            }
        }
    }

    private static final int BUTTON_PRESS_TIME = (int) TimeUnit.MILLISECONDS.toMillis(500);
    public static final String BANK_APP_INFO_DIALOG_TITLE = getResourceMsg("bank.app.info.dialog.title");
    public static final String OK = UIManager.getString("OptionPane.okButtonText", Locale.getDefault());

    public static void setup() {
        bankAppStarter = new BankAppStarter();
    }

    public static BankAppStarter bankAppStarter;
}
