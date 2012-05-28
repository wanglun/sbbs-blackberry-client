package name.wl.bbs.ui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.system.Display;

public class statusbarManager extends Field
{
    private String title = "";
    private String info = "";

    public statusbarManager()
    {
    }

    public void setTitle(String str)
    {
        title = str;
        invalidate();
    }

    public void setInfo(String str)
    {
        info = str;
        invalidate();
    }

    protected void paint(Graphics graphics)
    {
        int width = Display.getWidth();

        graphics.drawText(title, 2, 2);

        graphics.setColor(Color.GREEN);
        graphics.drawText(info, (int)(width*0.7), 2, DrawStyle.RIGHT, (int)(width*0.3));

        //FIXME status icon
    }
    
    protected void layout(int width, int height)
    {
        setExtent(Display.getWidth(), getFont().getHeight() + 4);
    }
}
