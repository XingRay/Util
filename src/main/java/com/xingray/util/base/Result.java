package com.xingray.util.base;

public class Result<T> {

    private T date;
    private boolean success;
    private String message;
    private Exception exception;

    public static final Result<Object> OK = new Result<>(true);

    public Result() {
    }

    public Result(boolean success) {
        this(null, success, null, null);
    }

    public Result(Exception exception) {
        this(null, false, exception.getMessage(), exception);
    }

    public Result(String message) {
        this(null, false, message, null);
    }

    public Result(T date, boolean success, String message, Exception exception) {
        this.date = date;
        this.success = success;
        this.message = message;
        this.exception = exception;
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

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "Result{" +
                "date=" + date +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", e=" + exception +
                '}';
    }
}
