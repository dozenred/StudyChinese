package cn.keziqin.studychinese.beans;

public class GroupBean {
    private int groupId;
    private int level;
    private String groupContent;

    private static GroupBean instance;

    public static GroupBean getInstance() {
        if (instance == null) instance = new GroupBean();
        return instance;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getGroupContent() {
        return groupContent;
    }

    public void setGroupContent(String groupContent) {
        this.groupContent = groupContent;
    }
}
