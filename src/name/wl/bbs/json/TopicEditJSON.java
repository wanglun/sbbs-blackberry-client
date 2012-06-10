package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class TopicEditJSON extends ApiJSON
{
    private static String API = "/topic/edit.json";

    /* --参数-- */
    /* 修改成功后 返回的话题 */
    private Topic topic;

    public TopicEditJSON(Topic topic)
    {
        this.topic = topic;
    }

    public void load(Listener listener)
    {
        Hashtable posts = new Hashtable();
        posts.put("board", this.topic.getBoard());
        posts.put("id", Integer.toString(this.topic.getId()));
        posts.put("title", this.topic.getTitle());
        posts.put("content", this.topic.getContent());

        super.load(API, null, posts, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.topic = Topic.TopicJSON(this.data.getString("topic"));
            } catch (Exception e) {
                Logger.debug("token parse error");
                this.success = false;
            }
        }
    }
}
