package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class SearchTopicsJSON extends ApiJSON
{
    private static String API = "/search/topics.json";

    /* --����-- */
    private String keys;

    /* ��ѡ ��ʼ�±� */
    private int start;

    private static int LIMIT = 10;
    /* ��ѡ ��໰����� */
    private int limit;

    private static final String CHARSET = "UTF-8";
    /* ��ѡ �ؼ��ʱ��� Ĭ��UTF-8 */
    private String charset;

    /* --����-- */
    private Vector topics;

    public SearchTopicsJSON(String keys)
    {
        this(keys, 0, LIMIT, CHARSET);
    }
    public SearchTopicsJSON(String keys, int start)
    {
        this(keys, start, LIMIT, CHARSET);
    }
    public SearchTopicsJSON(String keys, int start, int limit)
    {
        this(keys, start, limit, CHARSET);
    }
    public SearchTopicsJSON(String keys, int start, int limit, String charset)
    {
        this.keys = keys;
        this.start = start;
        this.limit = limit;
        this.charset = charset;

        this.topics = null;
    }

    public void load(Listener listener)
    {
        Hashtable gets = new Hashtable();
        gets.put("keys", this.keys);
        gets.put("start", Integer.toString(this.start));
        gets.put("limit", Integer.toString(this.limit));
        gets.put("charset", this.charset);

        super.load(API, gets, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.topics = Topic.SearchesJSON(this.data.getString("topics"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }
    }
}
