package name.wl.bbs.json;

import java.util.Hashtable;
import java.util.Vector;
import org.json.me.JSONArray;
import org.json.me.JSONObject;

import name.wl.bbs.http.HTTPRequestThread;
import name.wl.bbs.json.ApiJSON;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.Board;

public class BoardMarkreadJSON extends ApiJSON
{
    /* "/board/test/markread.json" */
    private static String API = "/board/";

    private Board board;
    private int result;

    public BoardMarkreadJSON(Board board)
    {
        this.board = board;
    }

    public void load()
    {
        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API + this.board.getName() + "/markread.json", AUTH));
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        Logger.debug("loadContent");
        super.loadContent(json);

        if (this.success) {
            try {
                this.result = this.data.getInt("result");
            } catch (Exception e) {
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }

    public int getResult()
    {
        return this.result;
    }
}
