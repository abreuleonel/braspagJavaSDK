package braspag.sdk.api;

import org.apache.http.client.HttpClient;

import java.io.IOException;

import braspag.sdk.Merchant;
import braspag.sdk.api.request.BraspagRequestException;
import braspag.sdk.api.request.CreateSaleRequest;
import braspag.sdk.api.request.QuerySaleRequest;
import braspag.sdk.api.request.UpdateSaleRequest;

/**
 * The Braspag Payer SDK;
 */
public class Braspag {
	private final Merchant merchant;
	private final Environment environment;
	private HttpClient httpClient;

	/**
	 * Create an instance of Braspag choosing the environment where the requests
	 * will be send
	 *
	 * @param merchant
	 *            The merchant credentials
	 * @param environment
	 *            The environment: {@link Environment#PRODUCTION} or
	 *            {@link Environment#SANDBOX}
	 */
	public Braspag(Merchant merchant, Environment environment) {
		this.merchant = merchant;
		this.environment = environment;
	}

	/**
	 * Create an instance of Braspag to work on production environment
	 *
	 * @param merchant
	 *            The merchant credentials
	 */
	public Braspag(Merchant merchant) {
		this(merchant, Environment.PRODUCTION);
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * Send the Sale to be created and return the Sale with tid and the status
	 * returned by Braspag.
	 *
	 * @param sale
	 *            The preconfigured Sale
	 * @return The Sale with authorization, tid, etc. returned by Braspag.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 */
	public Sale createSale(Sale sale) throws IOException, BraspagRequestException {
		CreateSaleRequest createSaleRequest = new CreateSaleRequest(merchant, environment);

		createSaleRequest.setHttpClient(httpClient);

		sale = createSaleRequest.execute(sale);

		return sale;
	}

	/**
	 * Query a Sale on Braspag by paymentId
	 *
	 * @param paymentId
	 *            The paymentId to be queried
	 * @return The Sale with authorization, tid, etc. returned by Braspag.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 */
	public Sale querySale(String paymentId) throws IOException, BraspagRequestException {
		QuerySaleRequest querySaleRequest = new QuerySaleRequest(merchant, environment);

		querySaleRequest.setHttpClient(httpClient);

		Sale sale = querySaleRequest.execute(paymentId);

		return sale;
	}

	/**
	 * Cancel a Sale on Braspag by paymentId and speficying the amount
	 *
	 * @param paymentId
	 *            The paymentId to be queried
	 * @param amount
	 *            Order value in cents
	 * @return The Sale with authorization, tid, etc. returned by Braspag.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 */
	public Sale cancelSale(String paymentId, Integer amount) throws IOException, BraspagRequestException {
		UpdateSaleRequest updateSaleRequest = new UpdateSaleRequest("void", merchant, environment);

		updateSaleRequest.setHttpClient(httpClient);
		updateSaleRequest.setAmount(amount);

		Sale sale = updateSaleRequest.execute(paymentId);

		return sale;
	}

	/**
	 * Cancel a Sale on Braspag by paymentId
	 *
	 * @param paymentId
	 *            The paymentId to be queried
	 * @return The Sale with authorization, tid, etc. returned by Braspag.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 */
	public Sale cancelSale(String paymentId) throws IOException, BraspagRequestException {
		return cancelSale(paymentId, null);
	}

	/**
	 * Capture a Sale on Braspag by paymentId and specifying the amount and the
	 * serviceTaxAmount
	 *
	 * @param paymentId
	 *            The paymentId to be captured
	 * @param amount
	 *            Amount of the authorization to be captured
	 * @param serviceTaxAmount
	 *            Amount of the authorization should be destined for the service
	 *            charge
	 * @return The Sale with authorization, tid, etc. returned by Braspag.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 */
	public Sale captureSale(String paymentId, Integer amount, Integer serviceTaxAmount)
			throws IOException, BraspagRequestException {
		UpdateSaleRequest updateSaleRequest = new UpdateSaleRequest("capture", merchant, environment);

		updateSaleRequest.setHttpClient(httpClient);
		updateSaleRequest.setAmount(amount);
		updateSaleRequest.setServiceTaxAmount(serviceTaxAmount);

		Sale sale = updateSaleRequest.execute(paymentId);

		return sale;
	}

	/**
	 * Capture a Sale on Braspag by paymentId and specifying the amount
	 *
	 * @param paymentId
	 *            The paymentId to be captured
	 * @param amount
	 *            Amount of the authorization to be captured
	 * @return The Sale with authorization, tid, etc. returned by Braspag.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 */
	public Sale captureSale(String paymentId, Integer amount) throws IOException, BraspagRequestException {
		return captureSale(paymentId, amount, null);
	}

	/**
	 * Capture a Sale on Braspag by paymentId
	 *
	 * @param paymentId
	 *            The paymentId to be captured
	 * @return The Sale with authorization, tid, etc. returned by Braspag.
	 * @throws IOException
	 * @throws CieloRequestException
	 *             if anything gets wrong.
	 */
	public Sale captureSale(String paymentId) throws IOException, BraspagRequestException {
		return captureSale(paymentId, null, null);
	}
}
