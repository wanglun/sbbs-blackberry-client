/* ∞Ê√Êƒ⁄»› */
package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class BoardScreen extends BaseScreen
{
    private static final int LIMIT = 10;

    private Board board;
    private TopicListField list = null;
    private int mode = BoardJSON.NORMAL;

    public BoardScreen(Board board)
    {
        this.board = board;

        new BoardJSON(board).load(loadListener);
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            BoardJSON obj = (BoardJSON)o;
            if (obj.getSuccess()) {
                final Vector topics = obj.getTopics();
                if (list == null) {
                    list = new TopicListField(topics, topicListener, moreListener);
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            BoardScreen.this.add(list);
                        }
                    });
                } else {
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            list.appendTopics(topics);
                            moreListener.setLoaded();
                        }
                    });
                }
            } else {
                alert("load board failed!");
            }
        }
    };

    public Listener moreListener = new Listener() {
        public void callback(Object obj)
        {
            if (!this.isLoading()) {
                new BoardJSON(board, mode, list.getSize(), LIMIT).load(loadListener);
                this.setLoading();
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
                bbs.pushScreen(new PostScreen(board));
                break;
        }

        return super.keyChar(key, status, time);
    }
}
