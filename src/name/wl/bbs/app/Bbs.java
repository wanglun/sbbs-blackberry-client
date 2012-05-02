package name.wl.bbs.app;

import net.rim.device.api.ui.UiApplication;

import name.wl.bbs.util.*;

public class Bbs extends UiApplication 
{
    public static final long APP_GUID = 0xe757c870033b1010L;
    public static final String APP_NAME = "SBBS Client";

    public static void main(String[] args)
    {
        Bbs instance = new Bbs();
        instance.enterEventDispatcher();
    }  
    public Bbs() 
    {
        pushScreen(new TestScreen());
    }
}
