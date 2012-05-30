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
    protected statusbarManager statusbar = null;

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

    private class showAlert implements Runnable {
        private String info;
        private int level;

        public showAlert(String info, int level)
        {
            this.info = info;
            this.level = level;
        }
        public void run()
        {
            final BaseScreen active = (BaseScreen)bbs.getActiveScreen();
            switch (level) {
                case ALERT_INFO:
                    active.setStatusbarInfo(info);
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            active.setStatusbarInfo("");
                        }
                    }, 800, false);
                    break;
                case ALERT_WARNING:
                    active.setStatusbarInfo(info);
                    break;
                case ALERT_ERROR:
                    active.setStatusbarInfo(info);
                    break;
                case ALERT_CONFIRM:
                    Dialog.alert(info);
                    break;
            }
        }
    }
    public void alert(final String info, int level, boolean close)
    {
        showAlert show = new showAlert(info, level);
        bbs.invokeLater(show);

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
