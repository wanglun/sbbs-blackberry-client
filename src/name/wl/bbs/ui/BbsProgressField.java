package name.wl.bbs.ui;

import net.rim.device.api.ui.component.*;

public class BbsProgressField extends GaugeField
{
    public BbsProgressField(String label, int min, int max, int start)
    {
        super(label, min, max, start, NO_TEXT);
    }
}
