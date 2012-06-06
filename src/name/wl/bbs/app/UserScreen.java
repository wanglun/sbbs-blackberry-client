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

        alert("查询中", ALERT_WARNING);
        this.userJSON.load(loadListener);

        items = new Vector();

        items.addElement(new InfoListItem("用户名：", this.user.getId()));

        list = new InfoListField(items);
        add(list);

        setStatusbarTitle("查看用户");
    }

    public void update()
    {
        items.removeAllElements();
        items.addElement(new InfoListItem("用户名：", this.user.getId()));
        items.addElement(new InfoListItem("昵称：", this.user.getName()));
        items.addElement(new InfoListItem("上次登录：", GenTimeStr.pretty(this.user.getLastlogin())));
        items.addElement(new InfoListItem("等级：", this.user.getLevel()));
        items.addElement(new InfoListItem("文章数：", this.user.getPosts()));
        items.addElement(new InfoListItem("表现值：", this.user.getPerform()));
        items.addElement(new InfoListItem("经验值：", this.user.getExperience()));
        items.addElement(new InfoListItem("勋章数：", this.user.getMedals()));
        items.addElement(new InfoListItem("上站次数：", this.user.getLogins()));
        items.addElement(new InfoListItem("生命力：", this.user.getLife()));
        items.addElement(new InfoListItem("性别：", this.user.getGender() == 'M' ? "男" : "女"));
        items.addElement(new InfoListItem("星座：", this.user.getAstro()));

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
                alert("加载完成");
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
