package name.wl.bbs.util;

import java.util.*;

public class GenTimeStr
{
    public static String standard(long time)
    {
        return (new timeObj(time * 1000).formatDate());
    }

    public static String pretty(long time)
    {
        timeObj now = new timeObj(new Date().getTime());
        timeObj t = new timeObj(time * 1000);

        if (now.isInMinute(t)) {
            return "刚刚";
        } else if (now.isInHour(t)) {
            long minutes = (now.getTime().getTime() - t.getTime().getTime())/1000/60;
            if (minutes > 0) {
                return minutes + "分钟前";
            } else {
                return "约几分钟前";
            }
        } else if (now.isSameDay(t)) {
            return "今天" + t.formatDay();
        }

        return t.formatDate();
    }
}

class timeObj {
    private Calendar cal;

    public timeObj(long time) {
        cal = Calendar.getInstance();
        cal.setTime(new Date(time));
    }

    public int getYear() {
        return cal.get(Calendar.YEAR);
    }
    public int getMonth() {
        return cal.get(Calendar.MONTH) + 1;
    }
    public int getDay() {
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    public int getHour() {
        return cal.get(Calendar.HOUR_OF_DAY);
    }
    public int getMinute() {
        return cal.get(Calendar.MINUTE);
    }
    public int getSecond() {
        return cal.get(Calendar.SECOND);
    }
    public Date getTime() {
        return cal.getTime();
    }

    public boolean isSameDay(timeObj t) {
        if (getYear() == t.getYear() &&
                getMonth() == t.getMonth() &&
                getDay() == t.getDay())
            return true;
        else
            return false;
    }

    public boolean isInHour(timeObj t) {
        if (getTime().getTime() - t.getTime().getTime() < 3600000)
            return true;
        else
            return false;
    }

    public boolean isInMinute(timeObj t) {
        if (getTime().getTime() - t.getTime().getTime() < 60000)
            return true;
        else
            return false;
    }

    public String padding(int num) {
        if (num >= 0 && num < 10) {
            return "0" + num;
        }

        return Integer.toString(num);
    }

    public String formatDate() {
        return getYear() + "-" + padding(getMonth()) + "-" + padding(getDay()) + " "
            + padding(getHour()) + ":" + padding(getMinute()) + ":" + padding(getSecond());
    }

    public String formatDay() {
        return padding(getHour()) + ":" + padding(getMinute()) + ":" + padding(getSecond());
    }
}
