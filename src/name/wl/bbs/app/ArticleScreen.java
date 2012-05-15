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

    private Topic topic;

    public ArticleScreen(Topic topic)
    {
        this.topic = topic;

        author = new BbsLabelField(topic.getAuthor());
        add(author);

        content = new BbsRichTextField(topic.getContent());
        add(content);
    }

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'q':
                bbs.popScreen(this);
                return true;
            case 'p':
                bbs.pushScreen(new PostScreen(new Board(topic.getBoard())));
                return true;
            case 'r':
                bbs.pushScreen(new PostScreen(topic));
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
