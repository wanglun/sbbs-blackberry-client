/* 版面内容 */
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
    private boolean needRefresh = false;
    private static int mode = BoardJSON.NORMAL;

    public BoardScreen(Board board)
    {
        this.board = board;

        new BoardJSON(board, mode).load(loadListener);

        setStatusbarTitle(board.getName());
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
                } else if (needRefresh) {
                    needRefresh = false;
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            list.setTopics(topics);
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
            switch (mode) {
                case BoardJSON.NORMAL:
                case BoardJSON.DIGEST:
                case BoardJSON.MARK:
                    bbs.pushScreen(new ArticleScreen(list));
                    break;
                case BoardJSON.THREAD:
                case BoardJSON.FORUM:
                    bbs.pushScreen(new ThreadScreen((Topic)o));
                    break;
            }
        }
    };

    public Listener markreadListener = new Listener() {
        public void callback(Object o)
        {
            BoardMarkreadJSON obj = (BoardMarkreadJSON)o;
            if (obj.getSuccess()) {
                if (obj.getResult() == 0) {
                    alert("已清除全部未读");
                } else {
                    alert("发生错误: " + obj.getResult());
                }
            } else {
                alert("清除未读失败");
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'p':
                bbs.pushScreen(new PostScreen(board));
                return true;
                /* normal mode */
            case 'c':
                new BoardMarkreadJSON(board).load(markreadListener);
                return true;
            case 'r':
                needRefresh = true;
                new BoardJSON(board, mode).load(loadListener);
                return true;
            case 'N':
                if (mode != BoardJSON.NORMAL) {
                    mode = BoardJSON.NORMAL;
                    needRefresh = true;
                    new BoardJSON(board, mode).load(loadListener);
                }
                return true;
                /* thread mode */
            case 'T':
                if (mode != BoardJSON.THREAD) {
                    mode = BoardJSON.THREAD;
                    needRefresh = true;
                    new BoardJSON(board, mode).load(loadListener);
                }
                return true;
                /* forum mode */
            case 'F':
                if (mode != BoardJSON.FORUM) {
                    mode = BoardJSON.FORUM;
                    needRefresh = true;
                    new BoardJSON(board, mode).load(loadListener);
                }
                return true;
                /* digest mode */
            case 'D':
                if (mode != BoardJSON.DIGEST) {
                    mode = BoardJSON.DIGEST;
                    needRefresh = true;
                    new BoardJSON(board, mode).load(loadListener);
                }
                return true;
                /* mark mode */
            case 'M':
                if (mode != BoardJSON.MARK) {
                    mode = BoardJSON.MARK;
                    needRefresh = true;
                    new BoardJSON(board, mode).load(loadListener);
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
