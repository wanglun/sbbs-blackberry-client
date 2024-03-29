package name.wl.bbs.hjlp;

import org.json.me.*;
import java.util.*;

import name.wl.bbs.util.*;

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
    private int replies = 0;

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
        topic.setBoard(data.getString("board"));
        topic.setSize(data.getInt("size"));
        topic.setRead(data.getInt("read"));
        topic.setReid(data.getInt("reid"));
        topic.setUnread(data.getBoolean("unread"));
        topic.setTop(data.getBoolean("top"));
        topic.setMark(data.getBoolean("mark"));
        topic.setAuthor(data.getString("author"));
        topic.setTime(data.getLong("time"));
        topic.setTitle(data.getString("title"));

        if (data.has("content"))
            topic.setContent(data.getString("content"));
        if (data.has("gid"))
            topic.setGid(data.getInt("gid"));
        if (data.has("replies"))
            topic.setReplies(data.getInt("replies"));
        if (data.has("norep"))
            topic.setNorep(data.getBoolean("norep"));
        if (data.has("quote"))
            topic.setQuote(data.getString("quote"));
        if (data.has("quoter"))
            topic.setQuoter(data.getString("quoter"));
        if (data.has("last_author"))
            topic.setLastAuthor(data.getString("last_author"));
        if (data.has("last_time"))
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

    public static Topic SearchJSON(String json) throws JSONException
    {
        Topic topic = new Topic();
        JSONObject data = new JSONObject(json);

        // FIXME 目前返回的是String. API BUG 有时会返回错误过大的id
        try {
            topic.setId(Integer.parseInt(data.getString("id")));
        } catch (Exception e) {
            return null;
        }
        topic.setTitle(data.getString("title"));
        topic.setBoard(data.getString("board"));
        // FIXME API BUG 有时会返回 null
        try {
            topic.setAuthor(data.getString("author"));
        } catch (Exception e) {
            topic.setAuthor("-null-");
        }

        // API目前返回的是String FIXME
        String t = data.getString("time");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(t.substring(0, 4)));
        cal.set(Calendar.MONTH, Integer.parseInt(t.substring(4, 6)) - 1);
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(t.substring(6, 8)));
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        topic.setTime(new Double(cal.getTime().getTime()*0.001).longValue());

        topic.setMark(data.getBoolean("mark"));
        topic.setRead(data.getInt("read"));

        return topic;
    }

    public static Vector SearchesJSON(String json) throws JSONException
    {
        Vector topics = new Vector();
        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            Topic t = SearchJSON(arr.getString(i));
            if (t != null)
                topics.addElement(t);
        }

        return topics;
    }

    public static Topic HotJSON(String json) throws JSONException
    {
        Topic topic = new Topic();
        JSONObject data = new JSONObject(json);

        topic.setTitle(data.getString("title"));
        topic.setAuthor(data.getString("author"));
        topic.setBoard(data.getString("board"));
        // API目前返回的是String FIXME
        topic.setTime(Long.parseLong(data.getString("time")));
        topic.setId(data.getInt("id"));
        topic.setReplies(data.getInt("replies"));
        // API目前返回的是String FIXME
        topic.setRead(Integer.parseInt(data.getString("read")));

        return topic;
    }

    public static Vector HotsJSON(String json) throws JSONException
    {
        Vector topics = new Vector();
        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            topics.addElement(HotJSON(arr.getString(i)));
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

    public void setId(int id)
    {
        this.id = id;
    }

    public void setGid(int gid)
    {
        this.gid = gid;
    }

    public void setBoard(String board)
    {
        this.board = board;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public void setRead(int read)
    {
        this.read = read;
    }

    public void setReplies(int replies)
    {
        this.replies = replies;
    }

    public void setReid(int reid)
    {
        this.reid = reid;
    }

    public void setUnread(boolean unread)
    {
        this.unread = unread;
    }

    public void setTop(boolean top)
    {
        this.top = top;
    }

    public void setMark(boolean mark)
    {
        this.mark = mark;
    }

    public void setNorep(boolean norep)
    {
        this.norep = norep;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public void setTime(long time)
    {
        this.time = time;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public void setQuote(String quote)
    {
        this.quote = quote;
    }

    public void setQuoter(String quoter)
    {
        this.quoter = quoter;
    }

    public void setLastAuthor(String last_author)
    {
        this.last_author = last_author;
    }

    public void setLastTime(long last_time)
    {
        this.last_time = last_time;
    }
}
