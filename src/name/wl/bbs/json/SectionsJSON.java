package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class SectionsJSON extends ApiJSON
{
    private static String API = "/sections.json";

    /* --参数-- */
    /* 可选 自动增加..作为上层目录 */
    private boolean up = false;

    /* --返回-- */
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
                Logger.debug("parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}
