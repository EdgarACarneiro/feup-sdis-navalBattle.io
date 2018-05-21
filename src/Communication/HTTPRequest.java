package Communication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.net.ssl.SSLSocket;

import org.omg.CORBA_2_3.portable.OutputStream;

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

	private static void getMethod(SSLSocket socket, String path, String ip, String port) {

		InputStream inputStream = socket.getInputStream(); 
		OutputStream outputStream = (OutputStream) socket.getOutputStream(); 
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); 
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
		//PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream));
		wr.write("GET " + "/" + path + " HTTP/1.1\r\n");
		wr.write("Host: "+  ip + ":" + port + "\r\n");		 
		wr.write("\r\n");
		wr.flush(); 				
	}

	private static void postMethod(SSLSocket socket, String path, String ip, String port, String data) {

		InputStream inputStream = socket.getInputStream(); 
		OutputStream outputStream = (OutputStream) socket.getOutputStream(); 
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); 

		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
		
		wr.write("POST " + "/" + path + " HTTP/1.1\r\n");

		wr.write("Content-Length: " + data.length() + "\r\n");
		wr.write("Content-Type: application/x-www-form-urlencoded\r\n");

		wr.write("\r\n");
		wr.write(data);
		wr.flush(); 				
	}
	
	private static void putMethod(SSLSocket socket, String path, String ip, String port, String data) {

		InputStream inputStream = socket.getInputStream(); 
		OutputStream outputStream = (OutputStream) socket.getOutputStream(); 
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); 

		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
		
		wr.write("PUT " + "/" + path + " HTTP/1.1\r\n");

		wr.write("Content-Length: " + data.length() + "\r\n");
		wr.write("Content-Type: application/x-www-form-urlencoded\r\n");

		wr.write("\r\n");
		wr.write(data);
		wr.flush(); 				
	}
	
	private static void patchtMethod(SSLSocket socket, String path, String ip, String port, String data) {

		InputStream inputStream = socket.getInputStream(); 
		OutputStream outputStream = (OutputStream) socket.getOutputStream(); 
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); 

		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
		
		wr.write("PATCH " + "/" + path + " HTTP/1.1\r\n");
		
		wr.write("Content-Length: " + data.length() + "\r\n");
		wr.write("Content-Type: application/x-www-form-urlencoded\r\n");

		wr.write("\r\n");
		wr.write(data);
		wr.flush(); 				
	}
	
	private static void deleteMethod(SSLSocket socket, String path, String ip, String port, String data) {

		InputStream inputStream = socket.getInputStream(); 
		OutputStream outputStream = (OutputStream) socket.getOutputStream(); 
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); 

		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
		
		wr.write("DELETE " + "/" + path + " HTTP/1.1\r\n");
		wr.write("Host: "+  ip + ":" + port + "\r\n");	 //not sure about HOST here

		wr.write("\r\n");
		wr.write(data);
		wr.flush(); 				
	}



}
