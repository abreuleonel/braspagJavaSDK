package braspag.sdk.api.request;

/**
 * Just represent an error sent by Braspag; most of time, client errors.
 */
public class BraspagError {
    private final Integer Code;
    private final String Message;

    public BraspagError(Integer code, String message) {
        this.Code = code;
        this.Message = message;
    }

    /**
     * @return The error code as specified on manual
     */
    public Integer getCode() {
        return Code;
    }

    /**
     * @return The error message as specified on manual
     */
    public String getMessage() {
        return Message;
    }
}
