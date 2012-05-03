package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class HotBoardsJSON extends ApiJSON
{
    private static String API = "/hot/boards.json";

    /* --·µ»Ø-- */
    private Vector boards;

    public HotBoardsJSON()
    {
        this.boards = null;
    }

    public void load()
    {
        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API));
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        super.loadContent(json);

        if (this.success) {
            try {
                this.boards = Board.BoardsJSON(this.data.getString("boards"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}
