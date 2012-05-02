package name.wl.bbs.hjlp;

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
    private int time;

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
    private int last_time;

    public Topic()
    {
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

    public int getTime()
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

    public int getLastTime()
    {
        return last_time;
    }
}
