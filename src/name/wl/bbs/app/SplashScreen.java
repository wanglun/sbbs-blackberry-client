package name.wl.bbs.app;

import name.wl.bbs.ui.*;
import name.wl.bbs.json.*;

import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Graphics;

public class SplashScreen extends BaseScreen
{
    public SplashScreen()
    {
        setStatusbarTitle("虎踞龙蟠BBS客户端");

        bbs.invokeLater(new load());
    }

    class load implements Runnable
    {
        public void run()
        {
            // get the cached token
            LoginJSON login = new LoginJSON();
            login.loadCached();
            if (login.getSuccess()) {
                bbs.setId(login.getId());
                bbs.setName(login.getName());
                bbs.setToken(login.getToken());
                bbs.setLoggedIn(true);
            }

            // get the cached sections
            SectionsJSON sections = new SectionsJSON();
            sections.loadCached();
            if (sections.getSuccess()) {
                bbs.setSections(sections.getBoards());
            }

            destroy();
        }
    }

    private void destroy()
    {
        bbs.popScreen(this);

        if (bbs.isLoggedIn()) {
            bbs.pushScreen(new MenuScreen());
        } else {
            bbs.pushScreen(new LoginScreen());
        }
    }

    protected void paint(Graphics graphics)
    {
        int height = Display.getHeight();
        int width = Display.getWidth();

        graphics.setColor(Color.GREEN);
        graphics.drawText("启动中...", 0, (int)(height*0.8), DrawStyle.HCENTER, width);
    }
}
