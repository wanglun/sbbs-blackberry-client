package name.wl.bbs.app;

import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.*;

public class PostScreen extends BaseScreen implements FieldChangeListener
{
    private Topic topic = null;
    private Board board = null;
    
    private BbsEditField title;
    private BbsEditField content;

    private BbsCheckboxField notopten;
    private BbsCheckboxField noquote;
    private BbsCheckboxField anony = null;

    private BbsButtonField post;

    public PostScreen(Board board)
    {
        this(board, null);
    }

    public PostScreen(Topic topic)
    {
        this(null, topic);
    }

    public PostScreen(Board board, Topic topic)
    {
        editable = true;

        this.board = board;
        this.topic = topic;

        title = new BbsEditField("标题：");
        if (topic != null) {
            title.setText(topic.getTitle().indexOf("Re: ") == 0 ? topic.getTitle() : "Re: " + topic.getTitle());
        }
        add(title);

        content = new BbsEditField("内容：", 3);
        add(content);

        String boardName = "";
        if (topic == null) {
            boardName = board.getName();

            notopten = new BbsCheckboxField("不上十大", false);
            add(notopten);
        }

        if (topic != null) {
            boardName = topic.getBoard();

            noquote = new BbsCheckboxField("不引原文", false);
            add(noquote);

            content.setFocus();
        }

        // FIXME 当前只有心理健康版是匿名版
        if (boardName.equals("Psychology")) {
            anony = new BbsCheckboxField("匿名发文", true);
            add(anony);
        }

        post = new BbsButtonField("发表");
        post.setChangeListener(this);
        add(post);

        setStatusbarTitle(topic == null ? "发表话题" : "回复话题");
    }

    public void fieldChanged(Field field, int context) {
        if (field == post) {
            String titleStr = title.getText();
            String contentStr = content.getText();
            boolean notoptenBool = false;
            if (topic == null) {
                notoptenBool = notopten.getChecked();
            }
            boolean noquoteBool = false;
            if (topic != null) {
                noquoteBool = noquote.getChecked();
            }
            boolean anonyBool = anony != null ? anony.getChecked() : false;
            if (titleStr.length() == 0 ||
                    contentStr.length() == 0) {
                alert("标题/内容不能为空", ALERT_ERROR);
            } else {
                TopicPostJSON json;
                if (board != null) {
                    if (topic != null)
                        json = new TopicPostJSON(board, titleStr, contentStr, topic.getId(),
                                notoptenBool, noquoteBool, anonyBool);
                    else
                        json = new TopicPostJSON(board, titleStr, contentStr, 0,
                                notoptenBool, noquoteBool, anonyBool);
                } else {
                    json = new TopicPostJSON(new Board(topic.getBoard()), titleStr, contentStr, topic.getId(),
                            notoptenBool, noquoteBool, anonyBool);
                }
                alert("发表中", ALERT_WARNING);
                json.load(this.postListener);
            }
        }
    }

    public Listener postListener = new Listener() {
        public void callback(Object r)
        {
            TopicPostJSON obj = (TopicPostJSON) r;
            if (obj.getSuccess()) {
                alert("已发表", true);
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case '@':
                if (getFieldWithFocus() == content) {
                    content.insert("@");
                    bbs.pushModalScreen(new SelectUserScreen(new Listener() {
                        public void callback(Object o) {
                            content.insert(((User)o).getId());
                        }
                    }));
                    return true;
                }
                break;
        }

        return super.keyChar(key, status, time);
    }
}
