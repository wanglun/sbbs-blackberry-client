package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class HotBoardsJSON extends ApiJSON
{
    private static String API = "/hot/boards.json";

    private static boolean CACHE = true;
    private static String KEY = "sbbs_hottopics";

    /* --·µ»Ø-- */
    private static Vector boards;

    public HotBoardsJSON()
    {
    }

    public void load(Listener listener)
    {
        super.load(API, listener);
    }

    public void refresh(Listener listener)
    {
        this.needRefresh = true;
        load(listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.boards = Board.BoardsJSON(this.data.getString("boards"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }
    }

    public boolean isParsed()
    {
        if (this.boards != null) {
            this.success = true;
            return true;
        }

        return false;
    }

    public String getKey()
    {
        return KEY;
    }

    public Vector getBoards()
    {
        return this.boards;
    }
}
