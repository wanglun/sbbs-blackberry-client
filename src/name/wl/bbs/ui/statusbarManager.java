package name.wl.bbs.ui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.system.Display;

import name.wl.bbs.app.BaseScreen;

public class statusbarManager extends Field
{
    private String title = "";
    private String info = "";
    private int total = 0;
    private int current = 0;

    private int info_color;

    public statusbarManager()
    {
    }

    public void setTitle(String str)
    {
        title = str;
        invalidate();
    }

    public void setIndex(int c, int t)
    {
        current = c;
        total = t;

        invalidate();
    }

    public void setInfo(String str, int level)
    {
        switch (level) {
            case BaseScreen.ALERT_INFO:
                this.info_color = Color.GREEN;
                break;
            case BaseScreen.ALERT_WARNING:
                this.info_color = Color.BLUE;
                break;
            case BaseScreen.ALERT_ERROR:
                this.info_color = Color.RED;
                break;
        }
        info = str;
        invalidate();
    }

    protected void paint(Graphics graphics)
    {
        int width = Display.getWidth();
        int old_color = graphics.getColor();

        graphics.drawText(title, 2, 2);

        graphics.setColor(info_color);
        graphics.drawText(info, (int)(width*0.6), 2, DrawStyle.RIGHT, (int)(width*0.4 - 30));

        if (info.length() == 0) {
            if (total > 0 && current > 0) {
                graphics.setColor(old_color);
                graphics.drawText(current + "/" + total, (int)(width*0.6), 2, DrawStyle.RIGHT, (int)(width*0.4 - 30));
            }
        }

        //FIXME status icon
    }
    
    protected void layout(int width, int height)
    {
        setExtent(Display.getWidth(), getFont().getHeight() + 4);
    }
}
