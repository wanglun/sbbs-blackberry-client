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
        alert("������", ALERT_WARNING);
        this.hottopicsJSON.load(loadListener);

        setStatusbarTitle("ʮ����");
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
                alert("�������");
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    public Listener refreshListener = new Listener() {
        public void callback(Object o)
        {
            HotToptenJSON obj = (HotToptenJSON)o;
            if (obj.getSuccess()) {
                topics = obj.getTopics();
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        list.setTopics(topics);
                    }
                });
                alert("ˢ�����");
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    public Listener topicListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new ThreadScreen((TopicListField)o));
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case '?':
                bbs.pushScreen(new HelpScreen(HelpScreen.TYPE_HOT));
                return true;
            case 'r':
                if (list != null) {
                    alert("ˢ����", ALERT_WARNING);
                    hottopicsJSON.refresh(refreshListener);
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
