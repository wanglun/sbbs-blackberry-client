package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;
import name.wl.bbs.hjlp.*;

public class ArticleScreen extends BaseScreen
{
    private BbsLabelField author;
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
        if (topic != null)
            this.topic = topic;
        else if (thread != null) {
            this.thread = thread;
            this.topic = (Topic)this.thread.getTopics().elementAt(this.thread.getSelectedIndex());
        } else if (topics != null) {
            this.topics = topics;
            this.topic = (Topic)this.topics.getTopics().elementAt(this.topics.getSelectedIndex());
            loadingAlert();
            new TopicJSON(this.topic, false, 0, 1).load(loadListener);
        }

        author = new BbsLabelField(this.topic.getAuthor());
        add(author);

        content = new BbsRichTextField(this.topic.getContent());
        add(content);
    }

    public void update()
    {
        author.setText(topic.getAuthor());
        content.setText(topic.getContent());
    }

    private void loadingAlert()
    {
        alert("加载文章内容");
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            TopicJSON obj = (TopicJSON)o;
            if (obj.getSuccess()) {
                alert("加载完成");
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
                    }
                }
            } else {
                alert("加载文章失败");
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
                if (this.thread != null) {
                    this.thread.setSelectedIndex(0);
                    this.topic = (Topic)this.thread.getTopics().elementAt(0);
                    update();
                } else if (this.topics != null) {
                    this.topics.setSelectedIndex(0);
                    this.topic = (Topic)this.topics.getTopics().elementAt(0);
                    update();
                    loadingAlert();
                    new TopicJSON(this.topic, false, 0, 1).load(loadListener);
                }
                return true;
            case 'b':
                if (this.thread != null) {
                    int size = this.thread.getSize();
                    this.thread.setSelectedIndex(size - 1);
                    this.topic = (Topic)this.thread.getTopics().elementAt(size - 1);
                    update();
                } else if (this.topics != null) {
                    int size = this.topics.getSize();
                    this.topics.setSelectedIndex(size - 1);
                    this.topic = (Topic)this.topics.getTopics().elementAt(size - 1);
                    update();
                    loadingAlert();
                    new TopicJSON(this.topic, false, 0, 1).load(loadListener);
                }
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
