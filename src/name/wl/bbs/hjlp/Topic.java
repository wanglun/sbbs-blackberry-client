package name.wl.bbs.hjlp;

import org.json.me.*;
import java.util.*;

public class Topic
{
    /* 帖子ID */
    private int id;

    /* 主题ID */
    private int gid;

    /* 所在版面 */
    private String board;

    /* 大小 */
    private int size;

    /* 阅读数 */
    private int read;

    /* 回复数 */
    private int replies;

    /* 回复帖子的id */
    private int reid;

    /* 是否未读 */
    private boolean unread;

    /* 是否置顶 */
    private boolean top;

    /* 是否标记 */
    private boolean mark;

    /* 不可回复 */
    private boolean norep;

    /* 作者 */
    private String author;
    
    /* 发表时间戳 */
    private long time;

    /* 标题 */
    private String title;

    /* 内容 */
    private String content;

    /* 引用内容 */
    private String quote;

    /* 所引用内容的作者 */
    private String quoter;

    /* 最后回复的作者 */
    private String last_author;

    /* 最后回复时间 */
    private long last_time;

    public Topic()
    {
    }

    public Topic(String json) throws JSONException
    {
        JSONObject data = new JSONObject(json);

        this.id = data.getInt("id");
        this.gid = data.getInt("gid");
        this.board = data.getString("board");
        this.size = data.getInt("size");
        this.read = data.getInt("read");
        this.replies = data.getInt("replies");
        this.reid = data.getInt("reid");
        this.unread = data.getBoolean("unread");
        this.top = data.getBoolean("top");
        this.mark = data.getBoolean("mark");
        this.norep = data.getBoolean("norep");
        this.author = data.getString("author");
        this.time = data.getLong("time");
        this.title = data.getString("title");
        this.content = data.getString("content");
        this.quote = data.getString("quote");
        this.quoter = data.getString("quoter");
        this.last_author = data.getString("last_author");
        this.last_time = data.getLong("last_time");
    }

    public static Vector Topics(String json) throws JSONException
    {
        Vector topics = new Vector();
        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            topics.addElement(new Topic(arr.getString(i)));
        }

        return topics;
    }

    public int getId()
    {
        return id;
    }

    public int getGid()
    {
        return gid;
    }

    public String getBoard()
    {
        return board;
    }

    public int getSize()
    {
        return size;
    }

    public int getRead()
    {
        return read;
    }

    public int getReplies()
    {
        return replies;
    }

    public int getReid()
    {
        return reid;
    }

    public boolean isUnread()
    {
        return unread;
    }

    public boolean isTop()
    {
        return top;
    }

    public boolean isMark()
    {
        return mark;
    }

    public boolean isNorep()
    {
        return norep;
    }

    public String getAuthor()
    {
        return author;
    }

    public long getTime()
    {
        return time;
    }

    public String getTitle()
    {
        return title;
    }

    public String getContent()
    {
        return content;
    }

    public String getQuote()
    {
        return quote;
    }

    public String getQuoter()
    {
        return quoter;
    }

    public String getLastAuthor()
    {
        return last_author;
    }

    public long getLastTime()
    {
        return last_time;
    }
}
