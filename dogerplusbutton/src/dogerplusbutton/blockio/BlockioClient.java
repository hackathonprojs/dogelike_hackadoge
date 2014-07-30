package dogerplusbutton.blockio;

import org.json.JSONObject;

import dogerplusbutton.http.SimpleHttps;

/**
 * simple block.io client
 * @author sol wu
 *
 */
public class BlockioClient {
	/** can be obtained from block.io account */
	String apiKey = null;
	
	public BlockioClient(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public JSONObject getNewAddress() {
		String url = "https://block.io/api/v1/get_new_address/?api_key={api_key}";
		url = url.replace("{api_key}", this.apiKey);
		SimpleHttps https = new SimpleHttps();
		return https.returnJson(url);
	}
	
	public JSONObject getAddressBalance(String address) {
		String url = "https://block.io/api/v1/get_address_balance/?api_key={api_key}&address={address}";
		url = url.replace("{api_key}", this.apiKey);
		url = url.replace("{address}", address);
		SimpleHttps https = new SimpleHttps();
		return https.returnJson(url);
	}
	
	public JSONObject withdraw(double amount, String paymentAddress, String pin) {
		String url = "https://block.io/api/v1/withdraw/?api_key={api_key}&amount={amount}&payment_address={payment_address}&pin={pin}";
		url = url.replace("{api_key}", this.apiKey);
		url = url.replace("{amount}", String.valueOf(amount));
		url = url.replace("{payment_address}", paymentAddress);
		url = url.replace("{pin}", pin);
		SimpleHttps https = new SimpleHttps();
		return https.returnJson(url);
	}
}
