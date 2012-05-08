package name.wl.bbs.app;

import name.wl.bbs.ui.*;

public class SplashScreen extends BaseScreen
{
    public SplashScreen()
    {
        add(new BbsProgressField("test", 0, 100, 30));

        bbs.invokeLater(new load());
    }

    class load implements Runnable
    {
        public void run()
        {
            loadSections();
            loadSections();
            loadUserdata();

            destroy();
        }
    }

    private void loadSections()
    {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

    private void loadSettings()
    {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }

    private void loadUserdata()
    {
    }

    private void destroy()
    {
        bbs.popScreen(this);

        if (bbs.isLoggedIn()) {
            //bbs.pushScreen(new LoginScreen());
        } else {
            bbs.pushScreen(new LoginScreen());
        }
    }
}
