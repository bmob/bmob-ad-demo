package cn.bmob.ad.bean;

/**
 * Created on 17/11/15 09:55
 * @author zhangchaozhou
 */

public class Back {
    private Integer code;
    private String error;
    private Data data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
