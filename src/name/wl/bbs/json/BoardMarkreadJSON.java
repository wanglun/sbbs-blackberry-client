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

        this.result = 0;
    }

    public void load(Listener listener)
    {
        super.load(API + this.board.getName() + "/markread.json", listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            if (this.data.has("result")) {
                try {
                    this.result = this.data.getInt("result");
                } catch (JSONException e) {
                }
            }
        }
    }

    public int getResult()
    {
        return this.result;
    }
}
