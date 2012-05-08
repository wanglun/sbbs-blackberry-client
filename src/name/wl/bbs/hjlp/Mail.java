package name.wl.bbs.hjlp;

import org.json.me.*;
import java.util.*;

public class Mail extends Topic
{
    private int type;

    public static Mail TopicJSON(int type, String json) throws JSONException
    {
        Mail mail = new Mail();
        JSONObject data = new JSONObject(json);

        mail.setId(data.getInt("id"));
        mail.setSize(data.getInt("size"));
        mail.setUnread(data.getBoolean("unread"));
        mail.setAuthor(data.getString("author"));
        mail.setTime(data.getLong("time"));
        mail.setTitle(data.getString("title"));

        try {
            mail.setContent(data.getString("content"));
        } catch (JSONException e) {
            mail.setContent("");
        }
        try {
            mail.setQuote(data.getString("quote"));
        } catch (JSONException e) {
            mail.setQuote("");
        }

        mail.setType(type);

        return mail;
    }

    public static Vector TopicsJSON(int type, String json) throws JSONException
    {
        Vector topics = new Vector();
        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            topics.addElement(TopicJSON(type, arr.getString(i)));
        }

        return topics;
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

    public void setType(int type)
    {
        this.type = type;
    }

    public int getType()
    {
        return this.type;
    }
}
