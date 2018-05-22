package REST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import Communication.HttpMethod;

public class RestServerHandler implements HttpHandler {

	private static final int PARAM_NAME_IDX = 0;
	private static final int PARAM_VALUE_IDX = 1;

	private static final int HTTP_OK_STATUS = 200;
	private static final int HTTP_NOTFOUND_STATUS = 404;

	private static final String AND_DELIMITER = "&";
	private static final String EQUAL_DELIMITER = "=";

	public void handle(HttpExchange t) throws IOException {

		String path = t.getRequestURI().getPath();
		String[] paths = path.split("/"); // path[0] é "" e path[1] é o context do servidor http eg:url /app/createUser

		String params_string = new String();
		if (t.getRequestMethod().equals(HttpMethod.GET))
			params_string = t.getRequestURI().getQuery();
		else
			params_string = this.readRequestBody(t);

		HashMap<String, String> params = getParams(params_string);

		/*
		 * ISto ou passamos um handler do path como argumento e esse handler retorna a
		 * string e o status a responder
		 */
		switch (paths[2]) {
		case "attack":
			// chama função relativa ao url no rest_api com os parâmetros obtidos
			// anteriormente
			// o retorno da função do rest_api pode ser a resposta ao "pedido" nesse caso
			// chamar sendResponse(HttpExchange t, int statusCode, String response)
			break;
		default:
			sendResponse(t, HTTP_NOTFOUND_STATUS, "Not Found");
		}
	}

	private HashMap<String, String> getParams(String query) {
		HashMap<String, String> params = new HashMap<String, String>();

		if (query != null) {
			String[] queryParams = query.split(AND_DELIMITER);
			if (queryParams.length > 0) {
				for (String qParam : queryParams) {
					String[] param = qParam.split(EQUAL_DELIMITER);
					params.put(param[PARAM_NAME_IDX].toUpperCase(), param[PARAM_VALUE_IDX].toUpperCase());
				}
			}
		}

		return params;
	}

	private void sendResponse(HttpExchange t, int statusCode, String response) {
		try {
			t.sendResponseHeaders(statusCode, response.getBytes().length);
			OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.flush();
			os.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private String readRequestBody(HttpExchange t) throws IOException {
		InputStreamReader isr = new InputStreamReader(t.getRequestBody(), "utf-8");
		BufferedReader br = new BufferedReader(isr);

		int b;
		StringBuilder buf = new StringBuilder();
		while ((b = br.read()) != -1) {
			buf.append((char) b);
		}

		br.close();
		isr.close();
		return buf.toString();
	}

}