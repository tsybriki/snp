package ru.mipt.snp.web.forms;

import ru.mipt.snp.web.common.ResponseStatus;

import java.io.Serializable;

/**
 * <p>Save user preferences form result that will be parsed into JSON object</p>
 *
 * @author Maxim Galushka
 * @since 24.11.2009
 */
public class SaveProfileResult implements Serializable{

    private static final long serialVersionUID = -4218832450027705857L;

    private ResponseStatus status;
    private String message;

    private String errorMessage;

    public SaveProfileResult() {
    }

    public SaveProfileResult(ResponseStatus status, String message, String errorMessage) {
        this.status = status;
        this.message = message;
        this.errorMessage = errorMessage;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
