package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class ThreadScreen extends BaseScreen
{
    private Topic topic;
    private TopicJSON topicJSON;
    private Vector topics;
    private ThreadListField list;

    public ThreadScreen(Topic topic)
    {
        this.topic = topic;
        this.topicJSON = new TopicJSON(topic);
        this.topicJSON.load(loadListener);
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            TopicJSON obj = (TopicJSON)o;
            if (obj.getSuccess()) {
                topics = obj.getTopics();
                list = new ThreadListField(topics);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        ThreadScreen.this.add(list);
                    }
                });
            } else {
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        alert("load board failed!");
                    }
                });
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'p':
                bbs.pushScreen(new PostScreen(new Board(topic.getBoard())));
                break;
        }

        return super.keyChar(key, status, time);
    }
}
