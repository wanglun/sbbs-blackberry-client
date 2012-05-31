package name.wl.bbs.ui;

import net.rim.device.api.ui.component.*;

public class BbsEditField extends EditField
{
    private int lines = 0;

    public BbsEditField(String label)
    {
        this(label, 0);
    }

    public BbsEditField(String label, int lines)
    {
        super(label, "");
        this.lines = lines;
    }

    protected void layout(int width, int height)
    {
        super.layout(width, height);
        if (lines > 0) {
            setExtent(width, lines * getFont().getHeight());
        }
    }
}
