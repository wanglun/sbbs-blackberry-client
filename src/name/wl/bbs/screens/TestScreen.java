package name.wl.bbs.screens;

import name.wl.bbs.json.LoginJSON;
import name.wl.bbs.Session;

public class TestScreen extends BaseScreen
{
    public TestScreen()
    {
        Session s = Session.getInstance();
        s.login("irun", "wxhsbbsxbj");
    }
}
