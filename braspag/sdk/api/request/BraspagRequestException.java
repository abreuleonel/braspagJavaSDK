package braspag.sdk.api.request;

/**
 * Just an exception that can be catch by the app
 */
public class BraspagRequestException extends Exception {
	private static final long serialVersionUID = -3886151912081950401L;
	private final BraspagError error;

    public BraspagRequestException(String message, BraspagError error, Throwable cause) {
        super(message, cause);

        this.error = error;
    }

    /**
     * The error related with this exception
     *
     * @return The {@link BraspagError} with the error codes and message.
     */
    public BraspagError getError() {
        return error;
    }
}
