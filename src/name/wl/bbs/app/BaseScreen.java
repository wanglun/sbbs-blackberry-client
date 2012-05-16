package name.wl.bbs.app;

import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;

import name.wl.bbs.util.*;
import name.wl.bbs.ui.*;

public class BaseScreen extends MainScreen
{
    protected Bbs bbs;
    protected static statusbarManager statusbar = null;

    public BaseScreen()
    {
        bbs = Bbs.getInstance();

        statusbar = new statusbarManager();
        add(statusbar);
    }

    public void setStatusbarTitle(String title)
    {
        statusbar.setTitle(title);
    }

    public void setStatusbarInfo(String info)
    {
        statusbar.setInfo(info);
    }

    public void alert(final String info)
    {
        bbs.invokeLater(new Runnable() {
            public void run() {
                Dialog.alert(info);
            }
        });
    }

    protected boolean keyChar(char key, int status, int time)
    {
        return super.keyChar(key, status, time);
    }
}
