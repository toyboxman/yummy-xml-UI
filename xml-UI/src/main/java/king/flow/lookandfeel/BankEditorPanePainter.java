/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.lookandfeel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.lang.reflect.Field;
import javax.swing.JComponent;
import javax.swing.plaf.nimbus.AbstractRegionPainter;

final public class BankEditorPanePainter extends AbstractRegionPainter {

    //package private integers representing the available states that
    //this painter will paint. These are used when creating a new instance
    //of EditorPanePainter to determine which region/state is being painted
    //by that instance.
    static final int BACKGROUND_DISABLED = 1;
    public static final int BACKGROUND_ENABLED = 2;
    static final int BACKGROUND_SELECTED = 3;

    private int state; //refers to one of the static final ints above
    private AbstractRegionPainter.PaintContext ctx;

    //the following 4 variables are reused during the painting code of the layers
    private Path2D path = new Path2D.Float();
    private Rectangle2D rect = new Rectangle2D.Float(0, 0, 0, 0);
    private RoundRectangle2D roundRect = new RoundRectangle2D.Float(0, 0, 0, 0, 0, 0);
    private Ellipse2D ellipse = new Ellipse2D.Float(0, 0, 0, 0);

    //All Colors used for painting are stored here. Ideally, only those colors being used
    //by a particular instance of EditorPanePainter would be created. For the moment at least,
    //however, all are created for each instance.
    private Color color1 = decodeColor("nimbusBlueGrey", -0.015872955f, -0.07995863f, 0.15294117f, 0);
    private Color color2 = Color.BLACK;
//    private Color color2 = decodeColor("nimbusLightBackground", 0.0f, 0.0f, 0.0f, 0);

    //Array of current component colors, updated in each paint call
    private Object[] componentColors;

    public BankEditorPanePainter(Object painter, int state) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        super();
        this.state = state;
        Field ctx = painter.getClass().getDeclaredField("ctx");
        ctx.setAccessible(true);
        this.ctx = (PaintContext) ctx.get(painter);
    }

    @Override
    protected void doPaint(Graphics2D g, JComponent c, int width, int height, Object[] extendedCacheKeys) {
        //populate componentColors array with colors calculated in getExtendedCacheKeys call
        componentColors = extendedCacheKeys;
        //generate this entire method. Each state/bg/fg/border combo that has
        //been painted gets its own KEY and paint method.
        switch (state) {
            case BACKGROUND_DISABLED:
                paintBackgroundDisabled(g);
                break;
            case BACKGROUND_ENABLED:
                paintBackgroundEnabled(g);
                break;
            case BACKGROUND_SELECTED:
                paintBackgroundSelected(g);
                break;

        }
    }

    @Override
    protected final AbstractRegionPainter.PaintContext getPaintContext() {
        return ctx;
    }

    private void paintBackgroundDisabled(Graphics2D g) {
        rect = decodeRect1();
        g.setPaint(color1);
        g.fill(rect);
        System.out.println("~1~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*****************************");
    }

    private void paintBackgroundEnabled(Graphics2D g) {
        rect = decodeRect1();
        g.setPaint(color2);
        g.fill(rect);
        System.out.println("~~~2~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*****************************");
    }

    private void paintBackgroundSelected(Graphics2D g) {
        rect = decodeRect1();
        g.setPaint(color2);
        g.fill(rect);
        System.out.println("~~~~3~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*****************************");
    }

    private Rectangle2D decodeRect1() {
        rect.setRect(decodeX(0.0f), //x
                decodeY(0.0f), //y
                decodeX(3.0f) - decodeX(0.0f), //width
                decodeY(3.0f) - decodeY(0.0f)); //height
        return rect;
    }

}
