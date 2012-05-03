package name.wl.bbs.hjlp;

import org.json.me.*;
import java.util.*;

public class Mail extends Topic
{
    public static Topic TopicJSON(String json) throws JSONException
    {
        Topic topic = new Topic();
        JSONObject data = new JSONObject(json);

        topic.setId(data.getInt("id"));
        topic.setSize(data.getInt("size"));
        topic.setUnread(data.getBoolean("unread"));
        topic.setAuthor(data.getString("author"));
        topic.setTime(data.getLong("time"));
        topic.setTitle(data.getString("title"));

        return topic;
    }

    public static Topic NotificationJSON(String json) throws JSONException
    {
        Topic topic = new Topic();
        JSONObject data = new JSONObject(json);

        topic.setId(data.getInt("id"));
        topic.setAuthor(data.getString("sender"));
        topic.setTitle(data.getString("title"));

        return topic;
    }
}
