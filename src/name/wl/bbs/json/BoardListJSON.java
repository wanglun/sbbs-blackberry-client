package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class BoardListJSON extends ApiJSON
{
    private static String API = "/board/list.json";

    private static boolean CACHE = true;
    private static String KEY = "sbbs_board_list";

    /* <String> */
    private Vector boards;

    public BoardListJSON()
    {
        this.boards = new Vector();
    }

    public void load(Listener listener)
    {
        super.load(API, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                JSONArray arr = this.data.getJSONArray("boards");
                for (int i = 0; i < arr.length(); i++) {
                    this.boards.addElement(arr.getString(i));
                }
            } catch (Exception e) {
                Logger.debug(e.toString());
                this.success = false;
            }
        }
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
