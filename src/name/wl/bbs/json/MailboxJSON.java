package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class MailboxJSON extends ApiJSON
{
    private static String API = "/mailbox/get.json";

    /* --����-- */
    public static int INBOX = 0;
    public static int SENT = 1;
    public static int DELETED = 2;
    /* ���� */
    private int type;

    /* ��ѡ ��ʼ�±� */
    private int start;

    private static int LIMIT = 10;
    /* ��ѡ ��໰����� */
    private int limit;

    /* --����-- */
    /* �ż� */
    private Vector mails;

    public MailboxJSON(int type)
    {
        this(type, 0, LIMIT);
    }
    public MailboxJSON(int type, int start)
    {
        this(type, start, LIMIT);
    }
    public MailboxJSON(int type, int start, int limit)
    {
        this.type = type;
        this.start = start;
        this.limit = limit;

        this.mails = null;
    }

    public void load(Listener listener)
    {
        Hashtable gets = new Hashtable();
        gets.put("type", Integer.toString(this.type));
        gets.put("start", Integer.toString(this.start));
        gets.put("limit", Integer.toString(this.limit));

        super.load(API, gets, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.mails = Mail.TopicsJSON(type, this.data.getString("mails"));
            } catch (Exception e) {
                Logger.debug("sections parse error");
                this.success = false;
            }
        }
    }

    public Vector getMails()
    {
        return this.mails;
    }
}
