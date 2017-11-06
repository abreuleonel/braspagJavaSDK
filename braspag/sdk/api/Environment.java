package braspag.sdk.api;

/**
 * API Environment URLs
 * <p/>
 * <li>{@link #PRODUCTION}</li>
 * <li>{@link #SANDBOX}</li>
 */
public enum Environment implements braspag.sdk.Environment {
    /**
     * Environment.PRODUCTION
     * <p/>
     * <li>apiUrl = https://apisandbox.braspag.com.br/</li>
     * <li>apiQueryUrl = https://apiquerysandbox.braspag.com.br//</li>
     */
    SANDBOX("https://apihomolog.braspag.com.br/",
            "https://apiqueryhomolog.braspag.com.br/"),

    /**
     * Environment.SANDBOX
     * <p/>
     * <li>apiUrl = https://api.braspag.com.br/</li>
     * <li>apiQueryUrl = https://apiquery.braspag.com.br/</li>
     */
    PRODUCTION("https://api.braspag.com.br/",
               "https://apiquery.braspag.com.br/");

    private final String apiUrl;
    private final String apiQueryUrl;

    Environment(String apiUrl, String apiQueryUrl) {
        this.apiUrl = apiUrl;
        this.apiQueryUrl = apiQueryUrl;
    }

    public String getApiUrl() {
        return this.apiUrl;
    }

    public String getApiQueryURL() {
        return this.apiQueryUrl;
    }
}
