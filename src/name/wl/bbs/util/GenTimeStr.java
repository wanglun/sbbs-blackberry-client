package name.wl.bbs.util;

import net.rim.device.api.i18n.SimpleDateFormat;
import java.util.Date;

public class GenTimeStr
{
    public static String standard(long time)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.formatLocal(time);
    }

    public static String pretty(long time)
    {
        // FIXME
        return standard(time);
    }
}
