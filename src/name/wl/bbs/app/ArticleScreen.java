package name.wl.bbs.app;

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

    public ArticleScreen(ThreadListField thread)
    {
        this(thread, null);
    }
    public ArticleScreen(Topic topic)
    {
        this(null, topic);
    }
    public ArticleScreen(ThreadListField thread, Topic topic)
    {
        if (topic != null)
            this.topic = topic;
        else if (thread != null) {
            this.thread = thread;
            this.topic = (Topic)this.thread.getTopics().elementAt(this.thread.getSelectedIndex());
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
                }
                return true;
            case 'b':
                if (this.thread != null) {
                    int size = this.thread.getSize();
                    this.thread.setSelectedIndex(size - 1);
                    this.topic = (Topic)this.thread.getTopics().elementAt(size - 1);
                    update();
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
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
