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
    private BoardListField boards = null;
    private Board board = null;
    private TopicListField list = null;
    private boolean needRefresh = false;
    private static int mode = BoardJSON.NORMAL;

    public BoardScreen(BoardListField boards)
    {
        this(boards, null);
    }

    public BoardScreen(Board board)
    {
        this(null, board);
    }

    public BoardScreen(BoardListField boards, Board board)
    {
        if (boards != null) {
            this.boards = boards;
            this.board = this.boards.getSelectedBoard();
        } else {
            this.board = board;
        }

        alert("加载中", ALERT_WARNING);
        new BoardJSON(this.board, mode).load(loadListener);

        setStatusbarTitle(this.board.getName() + " - " + BoardJSON.getModeString(mode));
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
                    alert("加载完成");
                } else if (needRefresh) {
                    needRefresh = false;
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            list.setTopics(topics);
                            setStatusbarTitle(board.getName() + " - " + BoardJSON.getModeString(mode));
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
                new BoardJSON(board, mode, list.getSize()).load(loadListener);
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
                    bbs.pushScreen(new ThreadScreen(list));
                    break;
            }
        }
    };

    public Listener markreadListener = new Listener() {
        public void callback(Object o)
        {
            BoardMarkreadJSON obj = (BoardMarkreadJSON)o;
            if (obj.getSuccess()) {
                if (boards != null) {
                    boards.setRead(boards.getSelectedIndex());
                }
                list.setAllRead();
                alert("已清除未读");
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case '?':
                bbs.pushScreen(new HelpScreen(HelpScreen.TYPE_BOARD));
                return true;
            case 'p':
                bbs.pushScreen(new PostScreen(board));
                return true;
            case 'c':
                new BoardMarkreadJSON(board).load(markreadListener);
                return true;
            case 'r':
                needRefresh = true;
                alert("刷新中", ALERT_WARNING);
                new BoardJSON(board, mode).load(loadListener);
                return true;
                /* normal mode */
            case 'N':
                if (mode != BoardJSON.NORMAL) {
                    mode = BoardJSON.NORMAL;
                    needRefresh = true;
                    alert("加载中", ALERT_WARNING);
                    new BoardJSON(board, mode).load(loadListener);
                }
                return true;
                /* thread mode */
            case 'T':
                if (mode != BoardJSON.THREAD) {
                    mode = BoardJSON.THREAD;
                    needRefresh = true;
                    alert("加载中", ALERT_WARNING);
                    new BoardJSON(board, mode).load(loadListener);
                }
                return true;
                /* forum mode */
            case 'F':
                if (mode != BoardJSON.FORUM) {
                    mode = BoardJSON.FORUM;
                    needRefresh = true;
                    alert("加载中", ALERT_WARNING);
                    new BoardJSON(board, mode).load(loadListener);
                }
                return true;
                /* digest mode */
            case 'D':
                if (mode != BoardJSON.DIGEST) {
                    mode = BoardJSON.DIGEST;
                    needRefresh = true;
                    alert("加载中", ALERT_WARNING);
                    new BoardJSON(board, mode).load(loadListener);
                }
                return true;
                /* mark mode */
            case 'M':
                if (mode != BoardJSON.MARK) {
                    mode = BoardJSON.MARK;
                    needRefresh = true;
                    alert("加载中", ALERT_WARNING);
                    new BoardJSON(board, mode).load(loadListener);
                }
                return true;
            case 'Z':
                if (mode != BoardJSON.TOP) {
                    mode = BoardJSON.TOP;
                    needRefresh = true;
                    alert("加载中", ALERT_WARNING);
                    new BoardJSON(board, mode).load(loadListener);
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
