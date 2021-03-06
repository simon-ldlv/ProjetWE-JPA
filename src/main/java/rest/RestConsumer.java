package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RestConsumer {
		
		
		public static void restWeather (String city_p) {

		  try {
			ResourceBundle bundle = ResourceBundle.getBundle("rest.resources.properties.ressource");
			String apiUrl_l= bundle.getString("rest.url");
			String apiKey_l= bundle.getString("rest.api.password");
			String urlFull_l = "http://"+apiUrl_l+city_p+"&units=metric&appid="+apiKey_l;
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
			JSONObject objFull_l;
			try {
				objFull_l = new JSONObject(jsonStr_l);
			JSONArray objList_l = objFull_l.getJSONArray("list");
			
			System.out.println("moyenTemp= "+getMoyenTemp(objList_l));
			//objList_l.get(0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		  } catch (MalformedURLException e) {
			  

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

		}
		
		public static Double getMoyenTemp(JSONArray array) {
			Double Temp =0.0;
			try {
				for (int i = 0; i < array.length(); i++) {
				Long currentTime = array.getJSONObject(i).getLong("dt");
				if(isSaturdayFever(currentTime)) {
					Temp+=array.getJSONObject(i).getJSONObject("main").getDouble("temp");
				}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Double moyen=0.0;
			if(Temp!=0.0) {
			 moyen= Temp / 4;
			}
			return moyen;
		}
		
		public static boolean isSaturdayFever(long unix) {
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTimeInMillis(unix*1000);
		    //SSystem.out.println(calendar.getTime().toString());
		    if((calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
		    		&&((calendar.get(Calendar.HOUR_OF_DAY)>=9)
		    				&&(calendar.get(Calendar.HOUR_OF_DAY)<=21))) {
		    	return true;
		    }
		    else return false;
		}
		
		public static void main(String[] args0) {
			System.out.println(isSaturdayFever(1506797765));
			restWeather("London");
		}

	}