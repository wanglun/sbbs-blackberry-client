package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;
import name.wl.bbs.hjlp.*;

public class SearchScreen extends BaseScreen implements FieldChangeListener
{
    private BbsEditField board;
    private BbsEditField author;
    private BbsEditField keyword;
    private BbsButtonField search;

    private Board boardArg = null;

    private String keys = "";
    private TopicListField list = null;

    public SearchScreen()
    {
        this(null);
    }

    public SearchScreen(Board b)
    {
        editable = true;

        this.boardArg = b;

        board = new BbsEditField("����(��ѡ)��");
        add(board);

        author = new BbsEditField("����(��ѡ)��");
        if (this.boardArg != null) {
            add(author);
        }

        keyword = new BbsEditField("�ؼ��֣�");
        add(keyword);

        search = new BbsButtonField("����");
        search.setChangeListener(this);
        add(search);

        if (this.boardArg != null) {
            board.setText(boardArg.getName());
            keyword.setFocus();
        }

        setStatusbarTitle("����");
    }

    public void fieldChanged(Field field, int context) {
        if (field == search) {
            if (keyword.getText().length() == 0) {
                alert("�ؼ��ֲ���Ϊ��", ALERT_ERROR);
            } else {
                alert("������", ALERT_WARNING);
                if (board.getText().length() > 0) {
                    keys += "board:" + board.getText() + " ";
                }
                if (author.getText().length() > 0) {
                    keys += "author:" + author.getText() + " ";
                }
                keys += keyword.getText();
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
                            if (boardArg != null)
                                SearchScreen.this.delete(author);
                            SearchScreen.this.delete(keyword);
                            SearchScreen.this.delete(search);
                            SearchScreen.this.add(list);
                        }
                    });
                    alert("�������");
                } else {
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            if (topics.size() == 0) {
                                alert("������:)");
                            } else {
                                list.appendTopics(topics);
                                alert("�������");
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
                alert("���ظ���", ALERT_WARNING);
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
