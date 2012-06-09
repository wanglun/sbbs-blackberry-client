package name.wl.bbs.app;

import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Keypad;

import name.wl.bbs.util.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.json.*;
import name.wl.bbs.hjlp.*;

public class BaseScreen extends MainScreen
{
    public static final int ALERT_INFO = 0; 
    public static final int ALERT_WARNING = 1;
    public static final int ALERT_ERROR = 2;
    public static final int ALERT_CONFIRM = 3;

    protected Bbs bbs;
    protected statusbarManager statusbar = null;
    protected int statusbarInfoCleanId = -1;

    protected boolean editable = false;

    public BaseScreen()
    {
        bbs = Bbs.getInstance();

        statusbar = new statusbarManager();
        setTitle(statusbar);

        addMenuItem(helpMenuItem);
        addMenuItem(exitMenuItem);
    }

    public void setStatusbarTitle(String title)
    {
        statusbar.setTitle(title);
    }

    public void setStatusbarInfo(String info, int level)
    {
        statusbar.setInfo(info, level);
        if (statusbarInfoCleanId != -1) {
            bbs.cancelInvokeLater(statusbarInfoCleanId);
        }
    }

    public void setStatusbarIndex(int current, int total)
    {
        statusbar.setIndex(current, total);
    }

    public void setStatusbarInfoCleanId(int id)
    {
        statusbarInfoCleanId = id;
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
                    active.setStatusbarInfo(info, level);
                    int id = bbs.invokeLater(new Runnable() {
                        public void run() {
                            active.setStatusbarInfo("", level);
                        }
                    }, 800, false);
                    active.setStatusbarInfoCleanId(id);
                    break;
                case ALERT_WARNING:
                    active.setStatusbarInfo(info, level);
                    break;
                case ALERT_ERROR:
                    active.setStatusbarInfo(info, level);
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
        if (!editable) {
            switch (key) {
                case '?':
                    bbs.pushScreen(new HelpScreen(HelpScreen.TYPE_BASE));
                    return true;
                case 'v':
                    bbs.pushScreen(new MailBoxScreen(MailboxJSON.INBOX));
                    return true;
                case 'x':
                    bbs.pushScreen(new NotificationMenuScreen());
                    return true;
                case 'w':
                    bbs.pushScreen(new FriendsScreen());
                    return true;
                case 'H':
                    bbs.pushScreen(new HotToptenScreen());
                    return true;
                    /* alt + 'h' */
                case ':':
                    bbs.pushScreen(new HotSectionMenuScreen());
                    return true;
                case 's':
                    bbs.pushModalScreen(new SelectBoardScreen(new Listener() {
                        public void callback(Object o) {
                            bbs.pushScreen(new BoardScreen(new Board((String)o)));
                        }
                    }));
                    return true;
                case 'u':
                    bbs.pushModalScreen(new SelectUserScreen(new Listener() {
                        public void callback(Object o) {
                            bbs.pushScreen(new UserScreen(((User)o)));
                        }
                    }));
                    return true;
            }
        }

        return super.keyChar(key, status, time);
    }

    protected boolean onSavePrompt()
    {
        return true;
    }

    public boolean trackwheelRoll(int amount, int status, int time)
    {
        return super.trackwheelRoll(amount, status, time);
    }

    final MenuItem helpMenuItem = new MenuItem("Help", 0, 0)
    {       
        public void run()
        { 
            bbs.pushScreen(new HelpScreen(HelpScreen.TYPE_BASE));
        }
    }; 

    final MenuItem exitMenuItem = new MenuItem("Exit", 0, 0)
    {       
        public void run()
        { 
            System.exit(0);
        }
    }; 
}
