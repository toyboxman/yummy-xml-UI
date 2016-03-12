/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.util.logging.Level;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import static king.flow.common.CommonUtil.getLogger;

/**
 *
 * @author LiuJin
 */
public class DefaultTextFieldAction extends DefaultAction<JTextField> {

    public static enum InputType {

        ALL, NUMBER
    }
    private final int limit;
    private final InputType type;
    private final boolean moneyDeal;
    private final boolean editable;

    public DefaultTextFieldAction(int limit) {
        this(limit, false);
    }

    public DefaultTextFieldAction(int limit, InputType type) {
        this(limit, type, false, true);
    }

    public DefaultTextFieldAction(int limit, boolean cashUse) {
        this(limit, InputType.ALL, cashUse, true);
    }

    public DefaultTextFieldAction(int limit, boolean cashUse, boolean editable) {
        this(limit, InputType.ALL, cashUse, editable);
    }

    public DefaultTextFieldAction(int limit, InputType type, boolean cashUse, boolean editable) {
        this.limit = limit;
        this.type = type;
        this.moneyDeal = cashUse;
        this.editable = editable;
    }

    public void setOwner(JTextField owner) {
        this.owner = owner;
    }

    @Override
    public void setupListener() {
        if (moneyDeal) {
            owner.addAncestorListener(new AncestorListener() {

                @Override
                public void ancestorAdded(AncestorEvent event) {
                    owner.setText("0");
                }

                @Override
                public void ancestorRemoved(AncestorEvent event) {

                }

                @Override
                public void ancestorMoved(AncestorEvent event) {

                }
            });
        }
    }

    @Override
    public void initializeData() {
        //as textfield set uneditable, it cannot get cursor focusing. so I decide to use document to limit input
        //owner.setEditable(editable);
        if (!editable) {
            owner.setEnabled(editable);
        } else if (moneyDeal) {
            owner.setDocument(new MoneyInputLimit());
        } else if (type == InputType.NUMBER) {
            owner.setDocument(new NumberInputLimit());
        } else {
            owner.setDocument(new LengthInputLimit());
        }
    }

    private class LengthInputLimit extends PlainDocument {

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) {
                return;
            }

            if (!editable && str.length() < 2) {
                return;
            }

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }

        @Override
        public void remove(int offs, int len) throws BadLocationException {
            if (!editable && len < 2) {
                return;
            }
            super.remove(offs, len);
        }
    }

    private class NumberInputLimit extends PlainDocument {

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) {
                return;
            }

            if (!editable && str.length() < 2) {
                return;
            }

            if ((getLength() + str.length()) <= limit) {
                try {
                    Integer.parseInt(str);
                } catch (NumberFormatException numberFormatException) {
                    getLogger(NumberInputLimit.class.getName()).log(Level.FINE, "", numberFormatException);
                    return;
                }
                super.insertString(offset, str, attr);
            }
        }

        @Override
        public void remove(int offs, int len) throws BadLocationException {
            if (!editable && len < 2) {
                return;
            }
            super.remove(offs, len);
        }
    }

    private class MoneyInputLimit extends PlainDocument {

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (!editable && str.length() < 2) {
                return;
            }

            if (str != null && str.length() + getLength() <= limit) {
                int value = 0;
                try {
                    value = Integer.parseInt(str);
                } catch (NumberFormatException numberFormatException) {
                    getLogger(MoneyInputLimit.class.getName()).log(Level.FINE, "", numberFormatException);
                    return;
                }
                str = String.valueOf(value);
                if (getLength() == 0) {
                    if (str.length() == 1) {
                        super.insertString(0, "0.0" + str, attr);
                    } else if (str.length() == 2) {
                        super.insertString(0, "0." + str, attr);
                    } else if (str.length() == 3) {
                        super.insertString(0, new StringBuilder(str).insert(2, '.').append("0").toString(), attr);
                    } else {
                        super.insertString(0, new StringBuilder(str).insert(str.length() - 2, '.').toString(), attr);
                    }
                } else {
                    final String text = getText(0, getLength());
                    String txt = text.replace(".", "");
                    txt = String.valueOf(Integer.parseInt(txt));
                    super.remove(0, getLength());
                    final StringBuilder sb = new StringBuilder(txt);
                    if (txt.length() == 1) {
                        txt = sb.insert(0, "0.").append(str).toString();
                    } else if (txt.length() == 2) {
                        txt = sb.insert(1, ".").append(str).toString();
                    } else {
                        txt = sb.append(str).insert(sb.length() - 2, ".").toString();
                    }
                    super.insertString(0, txt, attr);
                }
            }
        }

        @Override
        public void remove(int offs, int len) throws BadLocationException {
            if (!editable && len < 2) {
                return;
            }
            super.remove(offs, len);
        }
    }
}
