package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class HotSectionScreen extends BaseScreen
{
    private int index;

    private HotSectionJSON hotsectionJSON;
    private Vector topics;
    private TopicListField list;

    public HotSectionScreen(int index)
    {
        this.index = index;
        this.hotsectionJSON = new HotSectionJSON(index);
        alert("������", ALERT_WARNING);
        this.hotsectionJSON.load(loadListener);

        setStatusbarTitle("�����ȵ�");
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            HotSectionJSON obj = (HotSectionJSON)o;
            if (obj.getSuccess()) {
                topics = obj.getTopics();
                list = new TopicListField(topics, topicListener);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        HotSectionScreen.this.add(list);
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
            HotSectionJSON obj = (HotSectionJSON)o;
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
            bbs.pushScreen(new ThreadScreen(list));
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'r':
                if (list != null) {
                    alert("ˢ����", ALERT_WARNING);
                    hotsectionJSON.refresh(refreshListener);
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
