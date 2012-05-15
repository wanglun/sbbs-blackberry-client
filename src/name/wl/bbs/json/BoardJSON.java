package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class BoardJSON extends ApiJSON
{
    /* 
     * "/board/test.json" 版面话题
     * "/board/SBBS_PT.json" 目录版面
     * */
    private static String API = "/board/";

    /* --参数-- */
    private Board board;

    /* 普通模式 */
    public static final int NORMAL = 0;
    /* 主题模式 */
    public static final int THREAD = 1;
    /* 论坛模式 */
    public static final int FORUM = 2;
    /* 置顶 */
    public static final int TOP = 3;
    /* 文摘 */
    public static final int DIGEST = 4;
    /* 保留 */
    public static final int MARK = 5;

    /* 可选 返回模式 */
    private int mode;

    /* 可选 起始下标 */
    private int start;

    private static int LIMIT = 10;
    /* 可选 最多话题个数 */
    private int limit;

    /* --返回-- */

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

        this.total = 0;
        this.topics = null;
        this.boards = null;
    }

    public void load(Listener listener)
    {
        Hashtable gets = new Hashtable();
        gets.put("mode", Integer.toString(this.mode));
        gets.put("start", Integer.toString(this.start));
        gets.put("limit", Integer.toString(this.limit));

        super.load(API + this.board.getName() + ".json", gets, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                /* 版面 */
                if (this.board.isLeaf()) {
                    this.total = this.data.getInt("total");
                    this.topics = Topic.TopicsJSON(this.data.getString("topics"));
                } else {
                    /* 目录 */
                    this.boards = Board.BoardsJSON(this.data.getString("boards"));
                }
                /* parse the json */
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }
    }

    public Vector getTopics()
    {
        return this.topics;
    }

    public int getTotal()
    {
        return this.total;
    }
}
