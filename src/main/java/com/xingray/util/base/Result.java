package com.xingray.util.base;

public class Result<T> {

    private T date;
    private boolean success;
    private String message;
    private Exception e;

    public static final Result<Object> OK = new Result<>(true);

    public Result() {
    }

    public Result(boolean success) {
        this(null, success, null, null);
    }

    public Result(Exception e) {
        this(null, false, e.getMessage(), e);
    }

    public Result(String message) {
        this(null, false, message, null);
    }

    public Result(T date, boolean success, String message, Exception e) {
        this.date = date;
        this.success = success;
        this.message = message;
        this.e = e;
    }

    public T getDate() {
        return date;
    }

    public void setDate(T date) {
        this.date = date;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "Result{" +
                "date=" + date +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", e=" + e +
                '}';
    }
}
