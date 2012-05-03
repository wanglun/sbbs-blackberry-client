package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class UserJSON extends ApiJSON
{
    /* "/user/:id.json" */
    private static String API = "/user/";

    /* --²ÎÊý-- */
    private String id;

    /* --·µ»Ø-- */
    private User user;

    public UserJSON(String id)
    {
        this.id = id;
    }

    public void load()
    {
        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API + this.id + ".json"));
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
                // this.user
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}

