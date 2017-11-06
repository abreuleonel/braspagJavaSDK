package braspag.sdk.api.request;

import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.io.IOException;

import braspag.sdk.Environment;
import braspag.sdk.Merchant;
import braspag.sdk.api.Sale;

/**
 * Create any kind of sale
 */
public class CreateSaleRequest extends AbstractSaleRequest<Sale> {
	public CreateSaleRequest(Merchant merchant, Environment environment) {
		super(merchant, environment);
	}

	@Override
	public Sale execute(Sale param) throws IOException, BraspagRequestException {
		Sale sale = null;

		String url = environment.getApiUrl() + "v2/sales/";
		HttpPost request = new HttpPost(url);
		
		String json = new GsonBuilder().create().toJson(param);
		 
		System.out.println(url);
		System.out.println(json);

		request.setEntity(new StringEntity(new GsonBuilder().create().toJson(param), ContentType.create("text/plain", "UTF-8")));

		HttpResponse response = sendRequest(request);

		sale = readResponse(response);

		return sale;
	}
}
