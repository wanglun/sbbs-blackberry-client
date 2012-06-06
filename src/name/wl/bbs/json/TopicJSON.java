package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class TopicJSON extends ApiJSON
{
    /* "/topic/:board/:id.json?limit=2" */
    private static String API = "/topic/";

    /* --参数-- */
    private Topic topic;

    private int id;

    /* 可选 是否过滤引文 */
    private boolean raw = false;

    /* 可选 起始下标 */
    private int start;

    /* 可选 最多话题个数 */
    private int limit;

    /* --返回-- */
    private Vector topics;

    public TopicJSON(Topic topic)
    {
        this(topic, false, 0, 0);
    }
    public TopicJSON(Topic topic, boolean raw)
    {
        this(topic, raw, 0, 0);
    }
    public TopicJSON(Topic topic, boolean raw, int start)
    {
        this(topic, raw, start, 0);
    }
    public TopicJSON(Topic topic, boolean raw, int start, int limit)
    {
        this.topic = topic;
        this.raw = raw;
        this.start = start;

        if (limit > 0)
            this.limit = limit;
        else
            this.limit = bbs.getSettings().getLoadTopics();

        this.topics = null;
    }

    public void load(Listener listener)
    {
        Hashtable gets = new Hashtable();
        gets.put("raw", new Boolean(raw).toString());
        gets.put("start", Integer.toString(this.start));
        gets.put("limit", Integer.toString(this.limit));

        super.load(API + this.topic.getBoard() + "/" + this.topic.getId() + ".json", gets, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.topics = Topic.TopicsJSON(this.data.getString("topics"));
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
}
