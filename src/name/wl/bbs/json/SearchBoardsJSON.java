package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class SearchBoardsJSON extends ApiJSON
{
    private static String API = "/search/boards.json";

    /* --²ÎÊý-- */
    private String name;

    /* --·µ»Ø-- */
    private Vector boards;

    public SearchBoardsJSON(String name)
    {
        this.name = name;

        this.boards = null;
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
                this.boards = Board.SearchesJSON(this.data.getString("boards"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}
