package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.Graphics;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;
import name.wl.bbs.hjlp.*;

public class ArticleScreen extends BaseScreen
{
    private BbsNullField header;
    private BbsRichTextField content;

    private Topic topic = null;
    private ThreadListField thread = null;
    private TopicListField topics = null;

    public ArticleScreen(ThreadListField thread)
    {
        this(thread, null, null);
    }
    public ArticleScreen(TopicListField topics)
    {
        this(null, null, topics);
    }
    public ArticleScreen(Topic topic)
    {
        this(null, topic, null);
    }
    public ArticleScreen(ThreadListField thread, Topic topic, TopicListField topics)
    {
        if (topic != null) {
            this.topic = topic;
            loadingAlert();
            new TopicJSON(this.topic, false, 0, 1).load(loadListener);
        } else if (thread != null) {
            this.thread = thread;
            this.topic = (Topic)this.thread.getTopics().elementAt(this.thread.getSelectedIndex());
        } else if (topics != null) {
            this.topics = topics;
            this.topic = (Topic)this.topics.getTopics().elementAt(this.topics.getSelectedIndex());
            loadingAlert();
            new TopicJSON(this.topic, false, 0, 1).load(loadListener);
        }

        header = new BbsNullField(2, headerPaintListener);
        add(header);

        content = new BbsRichTextField(this.topic.getContent());
        add(content);

        setStatusbarTitle(this.topic.getBoard());
    }

    public void update()
    {
        header.invalidate();
        content.setText(topic.getContent());
        // FIXME
        try {
            scroll(Manager.TOPMOST);
        } catch (Exception e) {
        }
    }

    private void loadingAlert()
    {
        alert("��������", ALERT_WARNING);
    }

    public Listener headerPaintListener = new Listener() {
        public void callback(Object o)
        {
            BbsNullField obj = (BbsNullField)o;
            int marginTD = obj.getRowMarginTD();
            int width = obj.getWidth();
            int height = obj.getHeight();
            Graphics graphics = obj.getGraphics();

            obj.paintBg();

            graphics.drawText(topic.getAuthor(), 10, marginTD,
                    DrawStyle.ELLIPSIS, (int)(width*0.3));
            graphics.drawText(GenTimeStr.standard(topic.getTime()), (int)(width*0.3), marginTD,
                    DrawStyle.RIGHT, (int)(width*0.7) - 10);
            graphics.drawText(topic.getTitle(), 10, obj.getRowHeight() + marginTD,
                    DrawStyle.ELLIPSIS, width - 10);
        }
    };

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            TopicJSON obj = (TopicJSON)o;
            if (obj.getSuccess()) {
                final Vector topics = obj.getTopics();
                if (topics.size() == 1) {
                    Topic t = (Topic)topics.elementAt(0);
                    if (t.getId() == topic.getId()) {
                        topic = t;
                        bbs.invokeLater(new Runnable() {
                            public void run() {
                                ArticleScreen.this.update();
                            }
                        });
                        alert("�������");
                    }
                }
            } else {
                alert("����:" + obj.getError(), ALERT_ERROR);
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'q':
                bbs.popScreen(this);
                return true;
            case 'r':
                bbs.pushScreen(new PostScreen(topic));
                return true;
            case 't':
                scroll(Manager.TOPMOST);
                return true;
            case 'b':
                scroll(Manager.BOTTOMMOST);
                return true;
            case 'n':
                if (this.thread != null) {
                    int idx = this.thread.getSelectedIndex();
                    int size = this.thread.getSize();
                    if (idx < size - 1) {
                        this.thread.setSelectedIndex(idx + 1);
                        this.topic = (Topic)this.thread.getTopics().elementAt(idx + 1);
                        update();
                    } else if (idx == size - 1) {
                        this.thread.loadMore();
                    }
                } else if (this.topics != null) {
                    int idx = this.topics.getSelectedIndex();
                    int size = this.topics.getSize();
                    if (idx < size - 1) {
                        this.topics.setSelectedIndex(idx + 1);
                        this.topic = (Topic)this.topics.getTopics().elementAt(idx + 1);
                        update();
                        loadingAlert();
                        new TopicJSON(this.topic, false, 0, 1).load(loadListener);
                    } else if (idx == size - 1) {
                        this.topics.loadMore();
                    }
                }
                return true;
            case 'p':
                if (this.thread != null) {
                    int idx = this.thread.getSelectedIndex();
                    if (idx > 0) {
                        this.thread.setSelectedIndex(idx - 1);
                        this.topic = (Topic)this.thread.getTopics().elementAt(idx - 1);
                        update();
                    }
                } else if (this.topics != null) {
                    int idx = this.topics.getSelectedIndex();
                    if (idx > 0) {
                        this.topics.setSelectedIndex(idx - 1);
                        this.topic = (Topic)this.topics.getTopics().elementAt(idx - 1);
                        update();
                        loadingAlert();
                        new TopicJSON(this.topic, false, 0, 1).load(loadListener);
                    }
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
