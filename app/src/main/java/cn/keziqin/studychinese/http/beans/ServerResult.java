package cn.keziqin.studychinese.http.beans;

/**
 * Created by Dozen on 2017/11/14.
 */

public class ServerResult<T> {
    private int result;
    private String resultDesp;
    private T content;

    public ServerResult(int result, String resultDesp, T content) {
        this.result = result;
        this.resultDesp = resultDesp;
        this.content = content;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getResultDesp() {
        return resultDesp;
    }

    public void setResultDesp(String resultDesp) {
        this.resultDesp = resultDesp;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
