package name.wl.bbs.ui;

import net.rim.device.api.ui.component.*;
import net.rim.device.api.ui.container.*;

public class statusbarManager extends HorizontalFieldManager
{
    private BbsLabelField title = null;
    private BbsLabelField info = null;

    public statusbarManager()
    {
        title = new BbsLabelField("");
        add(title);

        info = new BbsLabelField("");
        add(info);
    }

    public void setTitle(String str)
    {
        title.setText(str);
    }

    public void setInfo(String str)
    {
        info.setText(str);
    }
}
