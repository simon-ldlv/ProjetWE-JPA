package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

public class RestConsumer {
		
		
		public static void restWeather (String city_p) {

		  try {
			ResourceBundle bundle = ResourceBundle.getBundle("rest.resources.properties.ressource");
			String apiUrl_l= bundle.getString("rest.url");
			String apiKey_l= bundle.getString("rest.api.password");
			String urlFull_l = "http://"+apiUrl_l+city_p+"&appid="+apiKey_l;
			URL url = new URL(urlFull_l);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String jsonStr_l="";
			String output;
			System.out.println("Output from Server .... \n"); //FIXME Utiliser log4j
			while ((output = br.readLine()) != null) {
				jsonStr_l+=output;
			}
			System.out.println(jsonStr_l);
			conn.disconnect();
			JSONObject objFull_l = new JSONObject(jsonStr_l);
			JSONArray objList_l = objFull_l.getJSONArray("list");
			//objList_l.get(0);
			
		  } catch (MalformedURLException e) {
			  

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

		}
		
		public static void main(String[] args0) {
			restWeather("London,us");
		}

	}