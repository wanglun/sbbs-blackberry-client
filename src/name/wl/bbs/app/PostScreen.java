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
    private BbsCheckboxField anony;

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

        title = new BbsEditField("����:");
        if (topic != null) {
            title.setText(topic.getTitle().indexOf("Re: ") == 0 ? topic.getTitle() : "Re: " + topic.getTitle());
        }
        add(title);

        content = new BbsEditField("����:", 3);
        add(content);

        if (topic == null) {
            notopten = new BbsCheckboxField("����ʮ��", false);
            add(notopten);
        }

        if (topic != null) {
            noquote = new BbsCheckboxField("����ԭ��", false);
            add(noquote);

            content.setFocus();
        }

        anony = new BbsCheckboxField("��������", false);
        add(anony);

        post = new BbsButtonField("����");
        post.setChangeListener(this);
        add(post);

        setStatusbarTitle(topic == null ? "������" : "�ظ�����");
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
            boolean anonyBool = anony.getChecked();
            if (titleStr.length() == 0 ||
                    contentStr.length() == 0) {
                alert("����/���ݲ���Ϊ��", ALERT_ERROR);
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
                alert("������", ALERT_WARNING);
                json.load(this.postListener);
            }
        }
    }

    public Listener postListener = new Listener() {
        public void callback(Object r)
        {
            TopicPostJSON obj = (TopicPostJSON) r;
            if (obj.getSuccess()) {
                alert("�ѷ���", true);
            } else {
                alert("����:" + obj.getError(), ALERT_ERROR);
            }
        }
    };
}
