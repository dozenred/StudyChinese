package cn.keziqin.studychinese.beans;

public class CourseMessageEvent {
    private String message;

    public CourseMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
