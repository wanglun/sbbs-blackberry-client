package name.wl.bbs.json;

import java.util.Hashtable;
import java.util.Vector;
import org.json.me.JSONArray;
import org.json.me.JSONObject;

import name.wl.bbs.http.HTTPRequestThread;
import name.wl.bbs.json.ApiJSON;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class SearchBoardsJSON extends ApiJSON
{
    private static String API = "/search/boards.json";

    private String name;

    /* ���� */
    private Vector boards;

    public SearchBoardsJSON(String name)
    {
        this.name = name;
    }

    public void load()
    {
        Hashtable params = new Hashtable();
        params.put("name", this.name);

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
                // this.boards
                /* parse the json */
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}