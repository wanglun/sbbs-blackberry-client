package name.wl.bbs.hjlp;

public class User
{
    /* ID */
    private String id;

    /* �ǳ� */
    private String name;

    /* ͷ��URI */
    private String avatar;

    /* �ϴε�¼ʱ�� */
    private int lastlogin;
    
    /* �ȼ� */
    private String level;

    /* ������ */
    private int posts;

    /* ����ֵ */
    private int perform;

    /* ����ֵ */
    private int experience;

    /* ѫ���� */
    private int medals;

    /* ��վ���� */
    private int logins;

    /* ������ */
    private int life;

    /* �Ա� 'M'�� 'F'Ů */
    private char gender;

    /* ���� */
    private String astro;

    public User()
    {
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getAvatar()
    {
        return avatar;
    }

    public int getLastlogin()
    {
        return lastlogin;
    }

    public String getLevel()
    {
        return level;
    }

    public int getPosts()
    {
        return posts;
    }

    public int getPerform()
    {
        return perform;
    }

    public int getExperience()
    {
        return experience;
    }

    public int getMedals()
    {
        return medals;
    }

    public int getLogins()
    {
        return logins;
    }

    public int getLife()
    {
        return life;
    }

    public char getGender()
    {
        return gender;
    }

    public String getAstro()
    {
        return astro;
    }
}
