package Communication.REST;


import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import Security.SecurityAPI;


/**
 * The Class HTTPRequest.
 */
public class HTTPRequest {
	
	/** The Constant CHARSET. */
	private static final String CHARSET = "UTF-8";
	
	/** The Constant MESSAGE_SIZE. */
	private static final int MESSAGE_SIZE = 1024;

	/** The host. */
	private String host;
	
	/** The port. */
	private int port;
	
	/** The ssl socket. */
	private SSLSocket sslSocket;

	/**
	 * Instantiates a new HTTP request.
	 *
	 * @param host the host
	 * @param port the port
	 * @throws UnknownHostException the unknown host exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public HTTPRequest(String host, int port) throws UnknownHostException, IOException {
		this.host = host;
		this.port = port;

		SSLContext sslContext = SecurityAPI.getSSLContext();
		// Create socket factory
		SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
		// Create socket
		this.sslSocket = (SSLSocket) sslSocketFactory.createSocket(this.host, this.port);

		/**
		 * This controls the TCP_NODELAY socket option.
		 * TCP_NODELAY disables/enables the use of Nagle's Algorithm to control the amount of buffering used when transferring data.
		 * Nagle's algorithm tries to send full data segments by waiting, if necessary, for enough writes to come through to fill up the segment
		 */
		sslSocket.setTcpNoDelay(true);
	}

	/**
	 * Handshake.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void handshake() throws IOException {
		sslSocket.setEnabledCipherSuites(sslSocket.getSupportedCipherSuites());
		sslSocket.startHandshake();
	}

	
	/**
	 * Make Http request.
	 *
	 * @param requestMethod the request method
	 * @param path the path
	 * @param params the params
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public int makeRequest(String requestMethod,String path, Map<String, String> params) throws IOException {
		this.handshake();

		String paramaters = parameterStringBuilder(params);
		InputStream inputStream = this.sslSocket.getInputStream();
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(this.sslSocket.getOutputStream(), CHARSET));
		
		if(requestMethod.equals(HTTPMethod.GET))
			wr.write(requestMethod + " /" + path + "?" + paramaters + " HTTP/1.1\r\n");
		else
			wr.write(requestMethod + " /" + path + " HTTP/1.1\r\n");
		
		wr.write("Connection: close\r\n");
		wr.write("User-Agent: Mozilla/5.0 (Macintosh; U; Intel Mac OS X; de-de) AppleWebKit/523.10.3 (KHTML, like Gecko) Version/3.0.4 Safari/523.10\r\n");
		
		if(!requestMethod.equals(HTTPMethod.GET)) {
			wr.write("Content-Length: " + paramaters.length() + "\r\n");
			wr.write("Content-Type: application/x-www-form-urlencoded\r\n");
		}
		
		wr.write("\r\n");
		
		if(!requestMethod.equals(HTTPMethod.GET))
			wr.write(paramaters);
		
		wr.flush();

        return this.handleResponse(inputStream);
	}

	/**
	 * Converts map into a string.
	 *
	 * @param params the parameters
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
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

	/**
	 * Handles the server response.
	 *
	 * @param inputStream the input stream
	 * @return the int
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private int handleResponse(InputStream inputStream) throws IOException {
		
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[MESSAGE_SIZE];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
		    result.write(buffer, 0, length);
		}
		sslSocket.close();
		String temp[]=result.toString(CHARSET).split("\r\n\r\n");

		// Content not needed in our application, but if it were, it would be in temp[1]
		String statusCode = temp[0].split(" ")[1];
		return Integer.parseInt(statusCode);
	}
}
