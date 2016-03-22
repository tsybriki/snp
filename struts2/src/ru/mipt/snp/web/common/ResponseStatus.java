package ru.mipt.snp.web.common;

/**
 * <p>Statuses that can be received from server as ajax request result</p>
 *
 * @author Maxim Galushka
 * @since 18.11.2009
 */
public enum ResponseStatus {
    
    SUCCESS("success"),
    FAILED("failed");

    private String status;

    ResponseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
