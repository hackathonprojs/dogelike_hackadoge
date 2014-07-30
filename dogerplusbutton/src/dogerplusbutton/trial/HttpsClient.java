package dogerplusbutton.trial;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

import org.json.JSONObject;
 
public class HttpsClient{
 
   public static void main(String[] args)
   {
        new HttpsClient().testIt();
   }
 
   private void testIt(){
 
      String https_url = "https://block.io/api/v1/get_balance/?api_key=66a4-6516-629c-c298";
      URL url;
      try {
 
	     url = new URL(https_url);
	     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
 
	     //dumpl all cert info
	     //print_https_cert(con);
 
	     //dump all the content
	     //print_content(con);
	     
	     // convert content to json and print out.
	     JSONObject json = jsonContent(con);
	     System.out.println("---------content--------\n");
	     System.out.println(json.toString(2));
 
      } catch (MalformedURLException e) {
	     e.printStackTrace();
      } catch (IOException e) {
	     e.printStackTrace();
      }
 
   }
 
   private void print_https_cert(HttpsURLConnection con){
 
    if(con!=null){
 
      try {
 
	System.out.println("Response Code : " + con.getResponseCode());
	System.out.println("Cipher Suite : " + con.getCipherSuite());
	System.out.println("\n");
 
	Certificate[] certs = con.getServerCertificates();
	for(Certificate cert : certs){
	   System.out.println("Cert Type : " + cert.getType());
	   System.out.println("Cert Hash Code : " + cert.hashCode());
	   System.out.println("Cert Public Key Algorithm : " 
                                    + cert.getPublicKey().getAlgorithm());
	   System.out.println("Cert Public Key Format : " 
                                    + cert.getPublicKey().getFormat());
	   System.out.println("\n");
	}
 
	} catch (SSLPeerUnverifiedException e) {
		e.printStackTrace();
	} catch (IOException e){
		e.printStackTrace();
	}
 
     }
 
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