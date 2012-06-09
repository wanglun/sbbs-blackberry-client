package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.UiApplication;

import name.wl.bbs.util.*;

public class Bbs extends UiApplication 
{
    public static final long APP_GUID = 0xe757c870033b1010L;
    public static final String APP_NAME = "SBBS Client";

    private Settings settings = null;

    private String id = null;
    private String name = null;
    private String token = null;
    private boolean loggenIn = false;

    private Vector sections = null;
    private Vector hotTopics = null;
    private Vector hotBoards = null;

    // auto-complete
    private SelectList boards = null;
    private SelectList friends = null;

    private Timer tasksTimer = null;

    private static Bbs bbs = null;

    public static void main(String[] args)
    {
        Bbs instance = new Bbs();
        instance.enterEventDispatcher();
    } 

    public Bbs() 
    {
        bbs = this;

        tasksTimer = new Timer();

        pushScreen(new SplashScreen());

        if (isLoggedIn()) {
        } else {
            //pushScreen(new LoginScreen());
        }
    }

    public static Bbs getInstance()
    {
        return bbs;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return this.id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return this.token;
    }

    public void setLoggedIn(boolean loggenIn)
    {
        this.loggenIn = loggenIn;
    }

    public boolean isLoggedIn()
    {
        return this.loggenIn;
    }

    public void setSections(Vector sections)
    {
        this.sections = sections;
    }

    public Vector getSections()
    {
        return this.sections;
    }

    public void setSettings(Settings i)
    {
        this.settings = i;
    }

    public Settings getSettings()
    {
        return this.settings;
    }

    public void setHotTopics(Vector hotTopics)
    {
        this.hotTopics = hotTopics;
    }

    public Vector getHotTopics()
    {
        return this.hotTopics;
    }

    public void setHotBoards(Vector hotBoards)
    {
        this.hotBoards = hotBoards;
    }

    public Vector getHotBoards()
    {
        return this.hotBoards;
    }

    public void setBoards(SelectList boards)
    {
        this.boards = boards;
    }

    public SelectList getBoards()
    {
        return this.boards;
    }

    public void setFriends(SelectList friends)
    {
        this.friends = friends;
    }

    public SelectList getFriends()
    {
        return this.friends;
    }
    
    public Timer getTasksTimer()
    {
        return this.tasksTimer;
    }

    public void scheduleNotificationsTask()
    {
        NotificationsTask.getInstance().cancel();
        this.tasksTimer.schedule(NotificationsTask.getInstance(), new Date(), this.settings.getUpdateDelay() * 60 * 1000);
    }
}
