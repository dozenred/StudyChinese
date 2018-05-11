package cn.keziqin.studychinese.beans;

public class ExamMessageEvent {
    private String message;

    public ExamMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
