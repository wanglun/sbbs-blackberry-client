package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class HotSectionJSON extends ApiJSON
{
    private static String API = "/hot/section.json";

    /* --²ÎÊý-- */
    private int section;

    /* --·µ»Ø-- */
    private Vector topics;

    public HotSectionJSON(int section)
    {
        this.section = section;
        this.topics = null;
    }

    public void load()
    {
        Hashtable params = new Hashtable();
        params.put("section", Integer.toString(this.section));

        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API, params));
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        super.loadContent(json);

        if (this.success) {
            try {
                this.topics = Topic.TopicsJSON(this.data.getString("topics"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}
