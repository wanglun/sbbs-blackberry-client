package name.wl.bbs.json;

import java.util.Hashtable;
import java.util.Vector;
import org.json.me.JSONArray;
import org.json.me.JSONObject;

import name.wl.bbs.http.HTTPRequestThread;
import name.wl.bbs.json.ApiJSON;
import name.wl.bbs.util.*;

public class ClearNotificationsJSON extends ApiJSON
{
    private static String API = "/clear_notifications.json";

    public ClearNotificationsJSON()
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

        Event.trigger(this, "LOADED");
    }
}

