package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class SearchScreen extends BaseScreen implements FieldChangeListener
{
    private BbsEditField board;
    private BbsEditField keyword;
    private BbsButtonField search;

    private String keys = null;
    private TopicListField list = null;

    public SearchScreen()
    {
        this(null);
    }

    public SearchScreen(String boardName)
    {
        editable = true;

        board = new BbsEditField("版面:");
        add(board);

        keyword = new BbsEditField("关键字:");
        add(keyword);

        search = new BbsButtonField("搜索:");
        search.setChangeListener(this);
        add(search);

        if (boardName != null) {
            board.setText(boardName);
            keyword.setFocus();
        }

        setStatusbarTitle("搜索");
    }

    public void fieldChanged(Field field, int context) {
        if (field == search) {
            if (keyword.getText().length() == 0) {
                alert("关键字不能为空", ALERT_ERROR);
            } else {
                alert("搜索中", ALERT_WARNING);
                if (board.getText().length() > 0) {
                    keys = "board:" + board.getText() + " " + keyword.getText();
                } else {
                    keys = keyword.getText();
                }
                new SearchTopicsJSON(keys).load(this.loadListener);
            }
        }
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            SearchTopicsJSON obj = (SearchTopicsJSON)o;
            if (obj.getSuccess()) {
                final Vector topics = obj.getTopics();
                if (list == null) {
                    list = new TopicListField(topics, topicListener, moreListener);
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            editable = false;
                            SearchScreen.this.delete(board);
                            SearchScreen.this.delete(keyword);
                            SearchScreen.this.delete(search);
                            SearchScreen.this.add(list);
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
                new SearchTopicsJSON(keys, list.getSize()).load(loadListener);
                this.setLoading();
            }
        }
    };

    public Listener topicListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new ArticleScreen(list));
        }
    };
}
