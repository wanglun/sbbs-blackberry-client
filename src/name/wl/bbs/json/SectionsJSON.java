package name.wl.bbs.json;

import java.util.Hashtable;
import java.util.Vector;
import org.json.me.JSONArray;
import org.json.me.JSONObject;

import name.wl.bbs.http.HTTPRequestThread;
import name.wl.bbs.json.ApiJSON;
import name.wl.bbs.util.*;

public class SectionsJSON extends ApiJSON
{
    private static String API = "/sections.json";

    /* 可选 自动增加..作为上层目录 */
    private boolean up = false;

    private Vector boards;

    public SectionsJSON()
    {
    }

    public void load()
    {
        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API));
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
                Logger.debug("sections parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}
