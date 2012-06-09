package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class MailScreen extends BaseScreen
{
    private Mail mail = null;
    private MailListField mails = null;
    private MailJSON mailJSON;

    private BbsNullField header;
    private BbsRichTextField content;

    public MailScreen(Mail mail)
    {
        this(mail, null);
    }

    public MailScreen(MailListField mails)
    {
        this(null, mails);
    }

    public MailScreen(Mail mail, MailListField mails)
    {
        if (mail != null) {
            this.mail = mail;
            new MailJSON(this.mail).load(loadListener);
        } else if (mails != null) {
            this.mails = mails;
            this.mail = (Mail)this.mails.getMails().elementAt(this.mails.getSelectedIndex());
            setStatusbarIndex(1, mails.getSize());
            new MailJSON(this.mail).load(loadListener);
        }

        header = new BbsNullField(2, headerPaintListener);
        add(header);

        content = new BbsRichTextField(this.mail.getContent());
        add(content);

        switch (this.mail.getType()) {
            case MailboxJSON.INBOX:
                setStatusbarTitle("收件箱");
                break;
            case MailboxJSON.SENT:
                setStatusbarTitle("发件箱");
                break;
            case MailboxJSON.DELETED:
                setStatusbarTitle("垃圾箱");
                break;
        }

        alert("加载内容", ALERT_WARNING);
    }

    public void update()
    {
        if (mails != null) {
            setStatusbarIndex(mails.getSelectedIndex() + 1, mails.getSize());
        }

        header.invalidate();
        content.setText(mail.getContent());
        // FIXME
        try {
            scroll(Manager.TOPMOST);
        } catch (Exception e) {
        }
    }

    public Listener headerPaintListener = new Listener() {
        public void callback(Object o)
        {
            BbsNullField obj = (BbsNullField)o;
            int marginTD = obj.getRowMarginTD();
            int width = obj.getWidth();
            int height = obj.getHeight();
            Graphics graphics = obj.getGraphics();

            obj.paintBg();

            graphics.drawText(mail.getAuthor(), 10, marginTD,
                    DrawStyle.ELLIPSIS, (int)(width*0.3));
            graphics.drawText(GenTimeStr.standard(mail.getTime()), (int)(width*0.3), marginTD,
                    DrawStyle.RIGHT, (int)(width*0.7) - 10);
            graphics.drawText(mail.getTitle(), 10, obj.getRowHeight() + marginTD,
                    DrawStyle.ELLIPSIS, width - 10);
        }
    };

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            MailJSON obj = (MailJSON)o;
            if (obj.getSuccess()) {
                if (mail.getId() == obj.getMail().getId()) {
                    mail = obj.getMail();
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            MailScreen.this.update();
                        }
                    });
                    alert("加载完成");
                }
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case '?':
                bbs.pushScreen(new HelpScreen(HelpScreen.TYPE_MAIL));
                return true;
            case 'q':
                bbs.popScreen(this);
                return true;
            case 'r':
                bbs.pushScreen(new MailSendScreen(mail));
                return true;
            case 'n':
                if (this.mails != null) {
                    int idx = this.mails.getSelectedIndex();
                    int size = this.mails.getSize();
                    if (idx < size - 1) {
                        this.mails.setSelectedIndex(idx + 1);
                        this.mail = (Mail)this.mails.getMails().elementAt(idx + 1);
                        update();
                        alert("加载内容", ALERT_WARNING);
                        new MailJSON(this.mail).load(loadListener);
                    } else if (idx == size - 1) {
                        alert("加载更多", ALERT_WARNING);
                        this.mails.loadMore();
                    }
                }
                return true;
            case 'p':
                if (this.mails != null) {
                    int idx = this.mails.getSelectedIndex();
                    if (idx > 0) {
                        this.mails.setSelectedIndex(idx - 1);
                        this.mail = (Mail)this.mails.getMails().elementAt(idx - 1);
                        update();
                        alert("加载内容", ALERT_WARNING);
                        new MailJSON(this.mail).load(loadListener);
                    }
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
