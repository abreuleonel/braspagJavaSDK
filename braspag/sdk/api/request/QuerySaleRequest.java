package braspag.sdk.api.request;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.IOException;

import braspag.sdk.Environment;
import braspag.sdk.Merchant;
import braspag.sdk.api.Sale;

/**
 * Query a Sale by it's paymentId
 */
public class QuerySaleRequest extends AbstractSaleRequest<String> {
	public QuerySaleRequest(Merchant merchant, Environment environment) {
		super(merchant, environment);
	}

	@Override
	public Sale execute(String paymentId) throws IOException, BraspagRequestException {
		Sale sale = null;
		String url = environment.getApiQueryURL() + "v2/sales/" + paymentId;

		HttpGet request = new HttpGet(url);
		HttpResponse response = sendRequest(request);

		sale = readResponse(response);

		return sale;
	}
}