package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class ThreadScreen extends BaseScreen
{
    private TopicListField topics;
    private Topic topic;
    private ThreadListField list;

    public ThreadScreen(TopicListField topics)
    {
        this.topics = topics;
        this.topic = this.topics.getSelectedTopic();

        alert("加载中", ALERT_WARNING);
        new TopicJSON(this.topic).load(loadListener);

        setStatusbarTitle(this.topic.getBoard());
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            TopicJSON obj = (TopicJSON)o;
            if (obj.getSuccess()) {
                final Vector topics = obj.getTopics();
                if (list == null) {
                    ThreadScreen.this.topics.setRead(ThreadScreen.this.topics.getSelectedIndex());
                    list = new ThreadListField(topics, moreListener);
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            ThreadScreen.this.add(list);
                        }
                    });
                    alert("加载完成");
                } else {
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            if (topics.size() == 0) {
                                alert("到底了:)");
                            } else {
                                list.appendTopics(topics);
                                alert("加载完成");
                            }
                            moreListener.setLoaded();
                        }
                    });
                }
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    public Listener moreListener = new Listener() {
        public void callback(Object obj)
        {
            if (!this.isLoading()) {
                alert("加载更多", ALERT_WARNING);
                new TopicJSON(topic, false, list.getSize()).load(loadListener);
                this.setLoading();
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case '?':
                bbs.pushScreen(new HelpScreen(HelpScreen.TYPE_THREAD));
                return true;
            case 'p':
                bbs.pushScreen(new PostScreen(new Board(topic.getBoard())));
                break;
        }

        return super.keyChar(key, status, time);
    }
}
