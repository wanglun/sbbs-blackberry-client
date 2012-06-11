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

        title = new BbsEditField("标题：");
        title.setText(topic.getTitle());
        add(title);

        content = new BbsEditField("内容：", 3);
        content.setText(topic.getContent());
        add(content);

        edit = new BbsButtonField("提交");
        edit.setChangeListener(this);
        add(edit);

        setStatusbarTitle("修改文章");
    }

    public void fieldChanged(Field field, int context) {
        if (field == edit) {
            String titleStr = title.getText();
            String contentStr = content.getText();
            if (titleStr.length() == 0 ||
                    contentStr.length() == 0) {
                alert("标题/内容不能为空", ALERT_ERROR);
            } else {
                TopicEditJSON json;
                topic.setTitle(titleStr);
                topic.setContent(contentStr);
                json = new TopicEditJSON(topic);
                alert("修改中", ALERT_WARNING);
                json.load(this.editListener);
            }
        }
    }

    public Listener editListener = new Listener() {
        public void callback(Object r)
        {
            TopicEditJSON obj = (TopicEditJSON) r;
            if (obj.getSuccess()) {
                alert("已修改", true);
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
