package name.wl.bbs.app;

import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.*;

public class EditScreen extends BaseScreen implements FieldChangeListener
{
    private Topic topic = null;
    
    private BbsEditField title;
    private BbsEditField content;

    private BbsButtonField edit;

    public EditScreen(Topic topic)
    {
        editable = true;

        this.topic = topic;

        title = new BbsEditField("���⣺");
        title.setText(topic.getTitle());
        add(title);

        content = new BbsEditField("���ݣ�", 3);
        content.setText(topic.getContent());
        add(content);

        edit = new BbsButtonField("�ύ");
        edit.setChangeListener(this);
        add(edit);

        setStatusbarTitle("�޸�����");
    }

    public void fieldChanged(Field field, int context) {
        if (field == edit) {
            String titleStr = title.getText();
            String contentStr = content.getText();
            if (titleStr.length() == 0 ||
                    contentStr.length() == 0) {
                alert("����/���ݲ���Ϊ��", ALERT_ERROR);
            } else {
                TopicEditJSON json;
                topic.setTitle(titleStr);
                topic.setContent(contentStr);
                json = new TopicEditJSON(topic);
                alert("�޸���", ALERT_WARNING);
                json.load(this.editListener);
            }
        }
    }

    public Listener editListener = new Listener() {
        public void callback(Object r)
        {
            TopicEditJSON obj = (TopicEditJSON) r;
            if (obj.getSuccess()) {
                alert("���޸�", true);
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
