package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class UserScreen extends BaseScreen
{
    private User user;
    private UserJSON userJSON;

    private BbsLabelField id;
    private BbsLabelField username;

    private Vector items;
    private InfoListField list;

    public UserScreen(User user)
    {
        this.user = user;
        this.userJSON = new UserJSON(user);

        alert("��ѯ��", ALERT_WARNING);
        this.userJSON.load(loadListener);

        items = new Vector();

        items.addElement(new InfoListItem("�û�����", this.user.getId()));

        list = new InfoListField(items);
        add(list);

        setStatusbarTitle("�鿴�û�");
    }

    public void update()
    {
        items.removeAllElements();
        items.addElement(new InfoListItem("�û�����", this.user.getId()));
        items.addElement(new InfoListItem("�ǳƣ�", this.user.getName()));
        items.addElement(new InfoListItem("�ϴε�¼��", GenTimeStr.pretty(this.user.getLastlogin())));
        items.addElement(new InfoListItem("�ȼ���", this.user.getLevel()));
        items.addElement(new InfoListItem("��������", this.user.getPosts()));
        items.addElement(new InfoListItem("����ֵ��", this.user.getPerform()));
        items.addElement(new InfoListItem("����ֵ��", this.user.getExperience()));
        items.addElement(new InfoListItem("ѫ������", this.user.getMedals()));
        items.addElement(new InfoListItem("��վ������", this.user.getLogins()));
        items.addElement(new InfoListItem("��������", this.user.getLife()));
        items.addElement(new InfoListItem("�Ա�", this.user.getGender() == 'M' ? "��" : "Ů"));
        items.addElement(new InfoListItem("������", this.user.getAstro()));

        this.list.setItems(this.items);
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            UserJSON obj = (UserJSON)o;
            if (obj.getSuccess()) {
                UserScreen.this.user = obj.getUser();
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        UserScreen.this.update();
                    }
                });
                alert("�������");
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case '?':
                bbs.pushScreen(new HelpScreen(HelpScreen.TYPE_USER));
                return true;
            case 'q':
                bbs.popScreen(this);
                return true;
            case 'm':
                bbs.pushScreen(new MailSendScreen(user));
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
