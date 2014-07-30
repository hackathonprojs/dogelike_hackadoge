package dogerplusbutton.blockio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import dogerplusbutton.http.SimpleHttps;

/**
 * simple block.io client for appengine
 * @author sol wu
 *
 */
public class BlockioClientAppengine {
	/** can be obtained from block.io account */
	String apiKey = null;
	
	public BlockioClientAppengine(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public JSONObject getNewAddress() {
		String url = "https://block.io/api/v1/get_new_address/?api_key={api_key}";
		url = url.replace("{api_key}", this.apiKey);
		return fetchJson(url);
	}
	
	public JSONObject getAddressBalance(String address) {
		String url = "https://block.io/api/v1/get_address_balance/?api_key={api_key}&address={address}";
		url = url.replace("{api_key}", this.apiKey);
		url = url.replace("{address}", address);
		return fetchJson(url);
	}
	
	public JSONObject withdraw(double amount, String paymentAddress, String pin) {
		String url = "https://block.io/api/v1/withdraw/?api_key={api_key}&amount={amount}&payment_address={payment_address}&pin={pin}";
		url = url.replace("{api_key}", this.apiKey);
		url = url.replace("{amount}", String.valueOf(amount));
		url = url.replace("{payment_address}", paymentAddress);
		url = url.replace("{pin}", pin);
		return fetchJson(url);
	}
	
	/**
	 * eg output:

{
  "status": "success",
  "data": {
    "network": "DOGE",
    "user_id": 5,
    "address": "DPBL82vVEcaVdNqnzS3d3cBwvA63UrEywx",
    "label": "test2"
  }
}

	 * @param label
	 * @return
	 */
	public JSONObject createUser(String label) {
		String url = "https://block.io/api/v1/create_user/?api_key={api_key}&label={label}";
		url = url.replace("{api_key}", this.apiKey);
		url = url.replace("{label}", label);
		return fetchJson(url);
	}
	
	/**
	 * this will also give you balance and user_id (if address is associated with user_id).  
	 * eg output:
{
  "status": "success",
  "data": {
    "network": "DOGE",
    "user_id": 4,
    "address": "DDjZMKhWXBk3Vqwh2ZGK958byV39RvLC38",
    "label": "test1",
    "confirmed_balance": "0.00000000",
    "unconfirmed_balance": "0.00000000"
  }
}
	 * @param label
	 * @return
	 */
	public JSONObject getAddressByLabel(String label) {
		String url = "https://block.io/api/v1/get_address_by_label/?api_key={api_key}&label={label}";
		url = url.replace("{api_key}", this.apiKey);
		url = url.replace("{label}", label);
		return fetchJson(url);
	}
	
	
	private String fetchContent(String sUrl) {
		StringBuffer sb = new StringBuffer();
		try {
		    URL url = new URL(sUrl);
		    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
		    String line;
	
		    while ((line = reader.readLine()) != null) {
		        sb.append(line + System.lineSeparator());
		    }
		    reader.close();
	
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	private JSONObject fetchJson(String sUrl) throws JSONException {
		JSONObject result = null;
		String content = fetchContent(sUrl);
		if (content != null) {
			content = content.trim();
			if (!"".equals(content)) {
				result = new JSONObject(content);
			}
		}
		
		return result;
	}
}
