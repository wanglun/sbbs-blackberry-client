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

    public static Topic TopicJSON(String json) throws JSONException
    {
        Topic topic = new Topic();
        JSONObject data = new JSONObject(json);

        topic.setId(data.getInt("id"));
        topic.setGid(data.getInt("gid"));
        topic.setBoard(data.getString("board"));
        topic.setSize(data.getInt("size"));
        topic.setRead(data.getInt("read"));
        topic.setReplies(data.getInt("replies"));
        topic.setReid(data.getInt("reid"));
        topic.setUnread(data.getBoolean("unread"));
        topic.setTop(data.getBoolean("top"));
        topic.setMark(data.getBoolean("mark"));
        topic.setNorep(data.getBoolean("norep"));
        topic.setAuthor(data.getString("author"));
        topic.setTime(data.getLong("time"));
        topic.setTitle(data.getString("title"));
        topic.setContent(data.getString("content"));
        topic.setQuote(data.getString("quote"));
        topic.setQuoter(data.getString("quoter"));
        topic.setLastAuthor(data.getString("last_author"));
        topic.setLastTime(data.getLong("last_time"));

        return topic;
    }

    public static Vector TopicsJSON(String json) throws JSONException
    {
        Vector topics = new Vector();
        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            topics.addElement(TopicJSON(arr.getString(i)));
        }

        return topics;
    }

    public static Topic MailJSON(String json) throws JSONException
    {
        Topic topic = new Topic();
        JSONObject data = new JSONObject(json);

        topic.setId(data.getInt("id"));
        topic.setAuthor(data.getString("sender"));
        topic.setTitle(data.getString("title"));

        return topic;
    }

    public static Vector MailsJSON(String json) throws JSONException
    {
        Vector topics = new Vector();
        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            topics.addElement(MailJSON(arr.getString(i)));
        }

        return topics;
    }

    public static Topic NotificationJSON(String json) throws JSONException
    {
        Topic topic = new Topic();
        JSONObject data = new JSONObject(json);

        topic.setId(data.getInt("id"));
        topic.setAuthor(data.getString("user"));
        topic.setBoard(data.getString("board"));
        topic.setTitle(data.getString("title"));

        return topic;
    }

    public static Vector NotificationsJSON(String json) throws JSONException
    {
        Vector topics = new Vector();
        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            topics.addElement(NotificationJSON(arr.getString(i)));
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

    private void setId(int id)
    {
        this.id = id;
    }

    private void setGid(int gid)
    {
        this.gid = gid;
    }

    private void setBoard(String board)
    {
        this.board = board;
    }

    private void setSize(int size)
    {
        this.size = size;
    }

    private void setRead(int read)
    {
        this.read = read;
    }

    private void setReplies(int replies)
    {
        this.replies = replies;
    }

    private void setReid(int reid)
    {
        this.reid = reid;
    }

    private void setUnread(boolean unread)
    {
        this.unread = unread;
    }

    private void setTop(boolean top)
    {
        this.top = top;
    }

    private void setMark(boolean mark)
    {
        this.mark = mark;
    }

    private void setNorep(boolean norep)
    {
        this.norep = norep;
    }

    private void setAuthor(String author)
    {
        this.author = author;
    }

    private void setTime(long time)
    {
        this.time = time;
    }

    private void setTitle(String title)
    {
        this.title = title;
    }

    private void setContent(String content)
    {
        this.content = content;
    }

    private void setQuote(String quote)
    {
        this.quote = quote;
    }

    private void setQuoter(String quoter)
    {
        this.quoter = quoter;
    }

    private void setLastAuthor(String last_author)
    {
        this.last_author = last_author;
    }

    private void setLastTime(long last_time)
    {
        this.last_time = last_time;
    }
}
