package name.wl.bbs.app;

import name.wl.bbs.json.*;

import name.wl.bbs.util.*;

public class TestScreen extends BaseScreen
{
    public TestScreen()
    {
        testSections();
    }

    public void testSections()
    {
        SectionsJSON json = new SectionsJSON();
        json.load(null);
    }
}
