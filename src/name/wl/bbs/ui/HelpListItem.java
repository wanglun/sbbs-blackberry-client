package name.wl.bbs.ui;

public class HelpListItem
{
    private String key = null;
    private String label;

    public HelpListItem(String label)
    {
        this(null, label);
    }

    public HelpListItem(String key, String label)
    {
        this.key = key;
        this.label = label;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getKey()
    {
        return this.key;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getLabel()
    {
        return this.label;
    }
}
