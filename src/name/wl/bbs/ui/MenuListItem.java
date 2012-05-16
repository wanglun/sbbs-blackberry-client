package name.wl.bbs.ui;

import net.rim.device.api.system.Bitmap;
import name.wl.bbs.util.Listener;

public class MenuListItem
{
    private Bitmap icon = null;
    private String label;
    private int index = 0;

    private Listener listener;

    public MenuListItem(String label, Listener listener)
    {
        this(label, listener, 0, null);
    }

    public MenuListItem(String label, Listener listener, Bitmap icon)
    {
        this(label, listener, 0, icon);
    }

    public MenuListItem(String label, Listener listener, int index)
    {
        this(label, listener, index, null);
    }

    public MenuListItem(String label, Listener listener, int index, Bitmap icon)
    {
        this.label = label;
        this.listener = listener;
        this.index = index;
        this.icon = icon;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public Bitmap getIcon()
    {
        return this.icon;
    }

    public String getLabel()
    {
        return this.label;
    }

    public void setListener(Listener listener)
    {
        this.listener = listener;
    }

    public Listener getListener()
    {
        return this.listener;
    }

    public int getIndex()
    {
        return this.index;
    }

    public void callback()
    {
        this.listener.callback(this);
    }
}
