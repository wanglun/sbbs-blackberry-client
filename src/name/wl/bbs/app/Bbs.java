package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.UiApplication;

import name.wl.bbs.util.*;

public class Bbs extends UiApplication 
{
    public static final long APP_GUID = 0xe757c870033b1010L;
    public static final String APP_NAME = "SBBS Client";

    public Session session;
    private Vector sections = null;
    private Vector HotTopics = null;
    private Vector HotBoards = null;

    public static void main(String[] args)
    {
        Bbs instance = new Bbs();
        instance.enterEventDispatcher();
    } 

    public Bbs() 
    {
        session = Session.getInstance();

        pushScreen(new SplashScreen());

        if (session.isLoggedIn()) {
        } else {
            //pushScreen(new LoginScreen());
        }
    }
}
