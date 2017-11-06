package braspag.sdk;

/**
 * Merchant identification on Braspag
 */
public class Merchant {
    /**
     * {@link Merchant#getId()}
     */
    private final String id;

    /**
     * {@link Merchant#getKey()}
     */
    private final String key;

    public Merchant(String id, String key) {
        this.id = id;
        this.key = key;
    }

    /**
     * Gets the merchant identification number
     *
     * @return the merchant identification number on Braspag
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the merchant identification key
     *
     * @return the merchant identification key on Braspag
     */
    public String getKey() {
        return key;
    }
}
