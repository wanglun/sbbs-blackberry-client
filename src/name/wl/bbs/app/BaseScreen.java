package name.wl.bbs.app;

import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;

import name.wl.bbs.util.*;
import name.wl.bbs.ui.*;

public class BaseScreen extends MainScreen
{
    protected static final int ALERT_INFO = 0; 
    protected static final int ALERT_WARNING = 1;
    protected static final int ALERT_ERROR = 2;
    protected static final int ALERT_CONFIRM = 3;

    protected Bbs bbs;
    protected static statusbarManager statusbar = null;

    public BaseScreen()
    {
        bbs = Bbs.getInstance();

        statusbar = new statusbarManager();
        setTitle(statusbar);
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
        alert(info, ALERT_INFO, false);
    }

    public void alert(final String info, boolean close)
    {
        alert(info, ALERT_INFO, close);
    }

    public void alert(final String info, int level)
    {
        alert(info, level, false);
    }

    public void alert(final String info, int level, boolean close)
    {
        switch (level) {
            case ALERT_INFO:
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        setStatusbarInfo(info);
                    }
                });
                break;
            case ALERT_WARNING:
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        setStatusbarInfo(info);
                    }
                });
                break;
            case ALERT_ERROR:
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        setStatusbarInfo(info);
                    }
                });
                break;
            case ALERT_CONFIRM:
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        Dialog.alert(info);
                    }
                });
                break;
        }

        if (close) {
            bbs.invokeLater(new Runnable() {
                public void run() {
                    BaseScreen.this.close();
                }
            }, 1000, false);
        }
    }

    protected boolean keyChar(char key, int status, int time)
    {
        return super.keyChar(key, status, time);
    }
}
