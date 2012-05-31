package name.wl.bbs.ui;

import name.wl.bbs.util.*;
import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.system.Display;

public class BbsNullField extends NullField
{
    private static int rowHeight;
    private static final int rowMarginTD = 2;
    private static final int rowMarginLR = 2;

    private int rows;
    private Listener paintListener = null;
    private Graphics graphics = null;

    public BbsNullField(int rows, Listener listener)
    {
        super(NON_FOCUSABLE);

        rowHeight = getFont().getHeight() + rowMarginTD * 2;
        this.rows = rows;
        this.paintListener = listener;
    }

    public int getRowHeight()
    {
        return rowHeight;
    }
    
    public int getRowMarginTD()
    {
        return rowMarginTD;
    }

    public Graphics getGraphics()
    {
        return graphics;
    }

    protected void layout(int width, int height)
    {
        setExtent(Display.getWidth(), rowHeight * rows);
    }

    protected void paint(Graphics graphics)
    {
        if (paintListener != null) {
            this.graphics = graphics;
            paintListener.callback(this);
        }
    }

    public void paintBg()
    {
        paintBg(0xEEFFEE);
    }

    public void paintBg(int color)
    {
        int old_color = graphics.getColor();
        graphics.setColor(0xEEFFEE);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.setColor(old_color);
    }

    public void invalidate()
    {
        super.invalidate();
    }
}
