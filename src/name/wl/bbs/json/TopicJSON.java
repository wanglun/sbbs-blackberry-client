package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class TopicJSON extends ApiJSON
{
    /* "/topic/:board/:id.json?limit=2" */
    private static String API = "/topic/";

    /* --参数-- */
    private Board board;

    private int id;

    /* 可选 是否过滤引文 */
    private boolean raw = false;

    /* 可选 起始下标 */
    private int start;

    private static int LIMIT = 10;
    /* 可选 最多话题个数 */
    private int limit;

    /* --返回-- */
    private Vector topics;

    public TopicJSON(Board board)
    {
        this(board, false, 0, LIMIT);
    }
    public TopicJSON(Board board, boolean raw)
    {
        this(board, raw, 0, LIMIT);
    }
    public TopicJSON(Board board, boolean raw, int start)
    {
        this(board, raw, start, LIMIT);
    }
    public TopicJSON(Board board, boolean raw, int start, int limit)
    {
        this.board = board;
        this.raw = raw;
        this.start = start;
        this.limit = limit;

        this.topics = null;
    }

    public void load(Listener listener)
    {
        Hashtable gets = new Hashtable();
        gets.put("raw", new Boolean(raw).toString());
        gets.put("start", Integer.toString(this.start));
        gets.put("limit", Integer.toString(this.limit));

        super.load(API + this.board.getName() + "/" + this.id + ".json", gets, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.topics = Topic.TopicsJSON(this.data.getString("topics"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }
    }
}
