package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class NotificationsJSON extends ApiJSON
{
    private static String API = "/notifications.json";

    /* --·µ»Ø-- */
    private Vector mails;
    private Vector ats;
    private Vector replies;

    public NotificationsJSON()
    {
    }

    public void load()
    {
        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API, AUTH));
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        Logger.debug("loadContent");
        super.loadContent(json);

        if (this.success) {
            try {
                /* parse the json */
            } catch (Exception e) {
                Logger.debug("notifications parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }

    public Vector getMails()
    {
        return mails;
    }
    public Vector getAts()
    {
        return ats;
    }
    public Vector getReplies()
    {
        return replies;
    }
}
