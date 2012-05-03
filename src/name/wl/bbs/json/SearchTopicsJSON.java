package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class SearchTopicsJSON extends ApiJSON
{
    private static String API = "/search/topics.json";

    /* --参数-- */
    private String keys;

    /* 可选 起始下标 */
    private int start;

    private static int LIMIT = 10;
    /* 可选 最多话题个数 */
    private int limit;

    private static final String CHARSET = "UTF-8";
    /* 可选 关键词编码 默认UTF-8 */
    private String charset;

    /* --返回-- */
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

    public void load()
    {
        Hashtable params = new Hashtable();
        params.put("keys", this.keys);
        params.put("start", Integer.toString(this.start));
        params.put("limit", Integer.toString(this.limit));
        params.put("charset", this.charset);

        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API, params));
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        Logger.debug("loadContent");
        super.loadContent(json);

        if (this.success) {
            try {
                this.topics = Topic.SearchesJSON(this.data.getString("topics"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}
