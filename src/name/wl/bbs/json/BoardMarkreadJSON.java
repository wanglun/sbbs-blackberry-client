package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class BoardMarkreadJSON extends ApiJSON
{
    /* "/board/test/markread.json" */
    private static String API = "/board/";

    /* --²ÎÊý-- */
    private Board board;

    /* --·µ»Ø-- */
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
