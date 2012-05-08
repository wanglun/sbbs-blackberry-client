package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.UiApplication;

import name.wl.bbs.util.*;

public class Bbs extends UiApplication 
{
    public static final long APP_GUID = 0xe757c870033b1010L;
    public static final String APP_NAME = "SBBS Client";

    private String user = null;
    private String token = null;
    private boolean loggenIn = false;

    private Vector sections = null;
    private Vector HotTopics = null;
    private Vector HotBoards = null;

    private static Bbs bbs = null;

    public static void main(String[] args)
    {
        Bbs instance = new Bbs();
        instance.enterEventDispatcher();
    } 

    public Bbs() 
    {
        bbs = this;

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

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getUser()
    {
        return this.user;
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
}
