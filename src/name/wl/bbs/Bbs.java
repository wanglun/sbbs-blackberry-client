package name.wl.bbs;

import net.rim.device.api.ui.UiApplication;

import name.wl.bbs.screens.*;

/**
 * This class acts as an entry point to the Blackberry application RedditBB
 */
public class Bbs extends UiApplication 
{

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
