package name.wl.bbs.ui;

public class InfoListItem
{
    private String key = null;
    private String label;

    public InfoListItem(String label)
    {
        this(null, label);
    }

    public InfoListItem(String key, int i)
    {
        this(key, Integer.toString(i));
    }

    public InfoListItem(String key, boolean i)
    {
        this(key, i ? "ÊÇ" : "·ñ");
    }

    public InfoListItem(String key, String label)
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
