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

    public void load(Listener listener)
    {
        Hashtable gets = new Hashtable();
        gets.put("name", this.name);

        super.load(API, gets, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.boards = Board.SearchesJSON(this.data.getString("boards"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }
    }
}
