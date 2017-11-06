package braspag.sdk.api.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.Gson;

import braspag.sdk.Environment;
import braspag.sdk.Merchant;
import braspag.sdk.api.Sale;

/**
 * Abstraction to reuse most of the code that send and receive the HTTP
 * messages.
 *
 * @param <T>
 *            the AsyncTask expects 3 params and we can only anticipate 2 of
 *            them.
 */
public abstract class AbstractSaleRequest<T> {
	final Environment environment;
	private final Merchant merchant;
	private BraspagRequestException exception;
	private HttpClient httpClient;

	AbstractSaleRequest(Merchant merchant, Environment environment) {
		this.merchant = merchant;
		this.environment = environment;
	}

	/**
	 * This is an async task, so we can't deal with exceptions as usual.
	 *
	 * @return true if we've any exception thrown
	 */
	public boolean hasException() {
		return exception != null;
	}

	/**
	 * If any exception was thrown during the async task, this will return that
	 * exception
	 *
	 * @return the exception, if any
	 */
	public BraspagRequestException getException() {
		return exception;
	}

	public abstract Sale execute(T param) throws IOException, BraspagRequestException;

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * Send the HTTP request to Braspag with the mandatory HTTP Headers set
	 *
	 * @param request
	 *            The POST, PUT, GET request and its content is defined by the
	 *            derivations
	 * @return the HTTP response returned by Braspag
	 * @throws IOException
	 *             yeah, deal with it
	 */
	HttpResponse sendRequest(HttpUriRequest request) throws IOException {
		if (httpClient == null) {
			httpClient = HttpClientBuilder.create().build();
		}

		request.addHeader("Accept", "application/json");
		request.addHeader("Accept-Encoding", "gzip");
		request.addHeader("Content-Type", "application/json");
		request.addHeader("User-Agent", "Braspag/2.0 Android SDK");
		request.addHeader("MerchantId", merchant.getId());
		request.addHeader("MerchantKey", merchant.getKey());
		request.addHeader("RequestId", UUID.randomUUID().toString());

		return httpClient.execute(request);
	}

	/**
	 * Read the response body sent by Braspag
	 *
	 * @param response
	 *            HttpResponse by Braspag, with headers, status code, etc.
	 * @return An instance of Sale with the response entity sent by Braspag.
	 * @throws IOException
	 *             yeah, deal with it
	 */
	Sale readResponse(HttpResponse response) throws IOException {
		HttpEntity responseEntity = response.getEntity();
		InputStream responseEntityContent = responseEntity.getContent();

		Header contentEncoding = response.getFirstHeader("Content-Encoding");

		if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
			responseEntityContent = new GZIPInputStream(responseEntityContent);
		}

		BufferedReader responseReader = new BufferedReader(new InputStreamReader(responseEntityContent));
		StringBuilder responseBuilder = new StringBuilder();
		String line;

		while ((line = responseReader.readLine()) != null) {
			responseBuilder.append(line);
		}

		return parseResponse(response.getStatusLine().getStatusCode(), responseBuilder.toString());
	}

	/**
	 * Just decode the JSON into a Sale or create the exception chain to be
	 * thrown
	 *
	 * @param statusCode
	 *            The status code of response
	 * @param responseBody
	 *            The response sent by Braspag
	 * @return An instance of Sale or null
	 */
	private Sale parseResponse(int statusCode, String responseBody) {
		Sale sale = null;
		Gson gson = new Gson();

		switch (statusCode) {
		case 200:
		case 201:
			sale = gson.fromJson(responseBody, Sale.class);
			break;
		case 400:
			BraspagError[] errors = gson.fromJson(responseBody, BraspagError[].class);

			for (BraspagError error : errors) {
				System.out.printf("%s: %s", "Braspag Error [" + error.getCode() + "]", error.getMessage());

				exception = new BraspagRequestException(error.getMessage(), error, exception);
			}

			break;
		case 404:
			exception = new BraspagRequestException("Not found", new BraspagError(404, "Not found"), exception);
			break;
		default:
			System.out.printf("%s: %s", "Braspag", "Unknown status: " + statusCode);
		}

		return sale;
	}
}
