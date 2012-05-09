package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class HotToptenScreen extends BaseScreen
{
    private HotToptenJSON hottopicsJSON;
    private Vector topics;
    private TopicListField list;

    public HotToptenScreen()
    {
        this.hottopicsJSON = new HotToptenJSON();
        this.hottopicsJSON.load(loadListener);
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            HotToptenJSON obj = (HotToptenJSON)o;
            if (obj.getSuccess()) {
                topics = obj.getTopics();
                list = new TopicListField(topics, topicListener);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        HotToptenScreen.this.add(list);
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

    public Listener topicListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new ThreadScreen((Topic)o));
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'p':
                break;
        }

        return super.keyChar(key, status, time);
    }
}