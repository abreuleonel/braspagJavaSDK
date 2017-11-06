package braspag.sdk.api.request;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URISyntaxException;

import braspag.sdk.Environment;
import braspag.sdk.Merchant;
import braspag.sdk.api.Sale;

/**
 * Capture or cancel a Sale
 */
public class UpdateSaleRequest extends AbstractSaleRequest<String> {
//	private final String type;
	private Integer amount;
	private Integer serviceTaxAmount;

	public UpdateSaleRequest(String type, Merchant merchant, Environment environment) {
		super(merchant, environment);

//		this.type = type;
	}

	@Override
	public Sale execute(String paymentId) throws IOException, BraspagRequestException {
		Sale sale = null;

		try {
			URIBuilder builder = new URIBuilder(environment.getApiQueryURL() + "v2/sales/" + paymentId);

			if (amount != null) {
				builder.addParameter("amount", amount.toString());
			}

			if (serviceTaxAmount != null) {
				builder.addParameter("serviceTaxAmount", serviceTaxAmount.toString());
			}

			HttpPut request = new HttpPut(builder.build().toString());
			HttpResponse response = sendRequest(request);

			sale = readResponse(response);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return sale;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setServiceTaxAmount(Integer serviceTaxAmount) {
		this.serviceTaxAmount = serviceTaxAmount;
	}
}
