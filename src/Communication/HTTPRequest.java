package Communication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HTTPRequest {

	private static final String CHARSET = "UTF-8";
	private static final String PROTOCOL = "http";

	private URL url;

	public HTTPRequest(String host, String file, int port) throws MalformedURLException {
		this.url = new URL(PROTOCOL, host, port, file);
	}

	public String sendRequest(Map<String, String> parameters,String requestMethod) throws IOException {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod(requestMethod);
		
		con.setRequestProperty("Content-Type", "application/json");
		con.setInstanceFollowRedirects(false);
		
		if (parameters != null && !parameters.isEmpty()) {
			con.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(parameterStringBuilder(parameters));
			out.flush();
			out.close();
		}	
		
		return handleResponse(con);
	}
	
	private String handleResponse(HttpURLConnection con) throws IOException {
		if (con.getResponseCode() != 200) {
			System.out.println("Response code: " + con.getResponseCode());
			throw new IOException(con.getResponseMessage());
		}

		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
				    content.append(inputLine);
				}
				in.close();
		if (con != null)
			con.disconnect();

		return content.toString();
	}
	
	private static String parameterStringBuilder(Map<String, String> params) throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), CHARSET));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), CHARSET));
			result.append("&");
		}

		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}
}
