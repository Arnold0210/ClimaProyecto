package services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


//import java.util.Scanner;
@ManagedBean
public class RESTEasyClientGet {
	public List<Double> Temp(String ciudad) {
		HttpURLConnection connection = null;
		String inputLine;
		StringBuffer response = new StringBuffer();
		List<Double> weather = new ArrayList<Double>();
		try {
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + ciudad
					+ ",co&units=metric&APPID=4ac2181cb5721fe5b4dc274eb6cc54b3");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setUseCaches(false);
			connection.setDoOutput(true);
			if (connection.getResponseCode() == 200) {
				// get response stream
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				// feed response into the StringBuilder
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				JSONParser parser1 = new JSONParser();
				try {
					Object obj = parser1.parse(response.toString());
					JSONObject jsonObject = (JSONObject) obj;
					JSONObject obj1 = (JSONObject) jsonObject.get("main");
					Double temp = Double.parseDouble(obj1.get("temp").toString());
					Double humidity = Double.parseDouble(obj1.get("humidity").toString());
					weather.add(humidity);
					weather.add(temp);
					//System.out.println("Humedad: "+weather.get(0)+",Temperatura: "+weather.get(1));
					return weather;
				} catch (ParseException e) {
					System.out.println("Can't get data");
					System.out.print(" from this city: "+ciudad);
					e.printStackTrace();
					weather.add(0.0);
					weather.add(0.0);
					return weather;
				}
			} else {
				System.out.println("Can't get data");
				weather.add(0.0);
				weather.add(0.0);
				return weather;
			}
		} catch (IOException e) {
			System.out.println("Can't get data");
			e.printStackTrace();
			weather.add(0.0);
			weather.add(0.0);
			return weather;
		}
	}

	/*
	 * public static void main(String[] argvs) { RESTEasyClientGet r = new
	 * RESTEasyClientGet(); Scanner s = new Scanner(System.in); String a; while
	 * (true) { a = s.nextLine(); System.out.println(a);
	 * System.out.println(r.Temp(a)); }
	 * 
	 * }
	 */
}
