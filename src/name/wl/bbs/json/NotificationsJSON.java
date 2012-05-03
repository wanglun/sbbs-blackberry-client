package name.wl.bbs.json;

import java.util.Hashtable;
import java.util.Vector;
import org.json.me.JSONArray;
import org.json.me.JSONObject;

import name.wl.bbs.http.HTTPRequestThread;
import name.wl.bbs.json.ApiJSON;
import name.wl.bbs.util.*;

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
