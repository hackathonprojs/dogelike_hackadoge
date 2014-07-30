package dogerplusbutton.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;
import org.json.JSONException;

public class SimpleHttps {

//	public void access() {
//      String https_url = "https://block.io/api/v1/get_balance/?api_key=66a4-6516-629c-c298";
//      URL url;
//      try {
// 
//	     url = new URL(https_url);
//	     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
// 
//	     //dumpl all cert info
//	     //print_https_cert(con);
// 
//	     //dump all the content
//	     //print_content(con);
//	     
//	     // convert content to json and print out.
//	     JSONObject json = jsonContent(con);
//	     System.out.println("---------content--------\n");
//	     System.out.println(json.toString(2));
// 
//      } catch (MalformedURLException e) {
//	     e.printStackTrace();
//      } catch (IOException e) {
//	     e.printStackTrace();
//      }
// 
//	}
	
	public JSONObject returnJson(String httpsUrl) {
		JSONObject result = null;
      URL url;
      try {
 
	     url = new URL(httpsUrl);
	     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
 
	     //dumpl all cert info
	     //print_https_cert(con);
 
	     //dump all the content
	     //print_content(con);
	     
	     // convert content to json and print out.
	     result = jsonContent(con);
 
      } catch (MalformedURLException e) {
	     e.printStackTrace();
      } catch (IOException e) {
	     e.printStackTrace();
      } catch (JSONException ex) {
    	  ex.printStackTrace();
      }
      
      return result;
	}
	
	   private void print_content(HttpsURLConnection con){
		if(con!=null){
	 
			try {
		 
			   System.out.println("****** Content of the URL ********");			
			   BufferedReader br = 
				new BufferedReader(
					new InputStreamReader(con.getInputStream()));
		 
			   String input;
		 
			   while ((input = br.readLine()) != null){
			      System.out.println(input);
			   }
			   br.close();
		 
			} catch (IOException e) {
			   e.printStackTrace();
			}
	 
	       }
	 
	   }
		   
	   private String content(HttpsURLConnection conn) {
		   StringBuffer sb = new StringBuffer();
		   
		   if (conn != null) {
			   try {
				   BufferedReader br = 
							new BufferedReader(
								new InputStreamReader(conn.getInputStream()));
					 
				   String input;
			 
				   while ((input = br.readLine()) != null){
				      sb.append(input);
				   }
				   
				   br.close();
					 
			   } catch (IOException ex) {
				   ex.printStackTrace();
			   }
		   }
		   
		   return sb.toString();
	   }
	   
	   private JSONObject jsonContent(HttpsURLConnection conn) {
		   String sContent = content(conn);
		   return toJson(sContent);
	   }
	   
	   private JSONObject toJson(String str) {
		   JSONObject json = new JSONObject(str);
		   return json;
	   }
}
