package name.wl.bbs.ui;

import name.wl.bbs.util.Listener;

public class MenuListItem
{
    // bitmap
    private String label;

    private Listener listener;

    public MenuListItem(String label, Listener listener)
    {
        this.label = label;
        this.listener = listener;
    }

    public void setLabel(String label)
    {
        this.label = label;
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

    public void callback()
    {
        this.listener.callback(this);
    }
}
