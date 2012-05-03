package name.wl.bbs.hjlp;

import org.json.me.*;
import java.util.*;

public class Topic
{
    /* ����ID */
    private int id;

    /* ����ID */
    private int gid;

    /* ���ڰ��� */
    private String board;

    /* ��С */
    private int size;

    /* �Ķ��� */
    private int read;

    /* �ظ��� */
    private int replies;

    /* �ظ����ӵ�id */
    private int reid;

    /* �Ƿ�δ�� */
    private boolean unread;

    /* �Ƿ��ö� */
    private boolean top;

    /* �Ƿ��� */
    private boolean mark;

    /* ���ɻظ� */
    private boolean norep;

    /* ���� */
    private String author;
    
    /* ����ʱ��� */
    private long time;

    /* ���� */
    private String title;

    /* ���� */
    private String content;

    /* �������� */
    private String quote;

    /* ���������ݵ����� */
    private String quoter;

    /* ���ظ������� */
    private String last_author;

    /* ���ظ�ʱ�� */
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
