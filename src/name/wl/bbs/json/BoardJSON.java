package name.wl.bbs.json;

import java.util.Hashtable;
import java.util.Vector;
import org.json.me.JSONArray;
import org.json.me.JSONObject;

import name.wl.bbs.http.HTTPRequestThread;
import name.wl.bbs.json.ApiJSON;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class BoardJSON extends ApiJSON
{
    /* 
     * "/board/test.json" 版面话题
     * "/board/SBBS_PT.json" 目录版面
     * */
    private static String API = "/board/";

    private Board board;

    /* 普通模式 */
    private static final int NORMAL = 0;
    /* 主题模式 */
    private static final int THREAD = 1;
    /* 论坛模式 */
    private static final int FORUM = 2;
    /* 置顶 */
    private static final int TOP = 3;
    /* 文摘 */
    private static final int DIGEST = 4;
    /* 保留 */
    private static final int MARK = 5;

    /* 可选 返回模式 */
    private int mode;

    /* 可选 起始下标 */
    private int start;

    private static int LIMIT = 10;
    /* 可选 最多话题个数 */
    private int limit;

    /* 返回的文章数目 */
    private int total;

    /* 版面话题 */
    private Vector topics;

    /* 目录展开版面 */
    private Vector boards;

    public BoardJSON(Board board)
    {
        this(board, NORMAL, 0, LIMIT);
    }
    public BoardJSON(Board board, int mode)
    {
        this(board, mode, 0, LIMIT);
    }
    public BoardJSON(Board board, int mode, int start)
    {
        this(board, mode, start, LIMIT);
    }
    public BoardJSON(Board board, int mode, int start, int limit)
    {
        this.board = board;
        this.mode = mode;
        this.start = start;
        this.limit = limit;
    }

    public void load()
    {
        Hashtable params = new Hashtable();
        params.put("mode", Integer.toString(this.mode));
        params.put("start", Integer.toString(this.start));
        params.put("limit", Integer.toString(this.limit));

        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API + this.board.getName() + ".json", params));
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        Logger.debug("loadContent");
        super.loadContent(json);

        if (this.success) {
            try {
                /* 版面 */
                if (!this.board.isLeaf()) {
                    this.total = this.data.getInt("total");
                    // this.topics
                } else {
                    /* 目录 */
                    // this.boards
                }
                /* parse the json */
            } catch (Exception e) {
                Logger.debug("sections parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}
