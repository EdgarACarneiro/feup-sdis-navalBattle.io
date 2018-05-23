package Messages;

import Communication.HttpMethod;

import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class RESTMessage {

    private static final int PARAM_NAME_IDX = 0;
    private static final int PARAM_VALUE_IDX = 1;

    private static final int HTTP_OK_STATUS = 200;
    private static final int HTTP_NOTFOUND_STATUS = 404;

    private static final String AND_DELIMITER = "&";
    private static final String EQUAL_DELIMITER = "=";

    // TODO : provavelmente posso ter uma Class comum entre ambas as mensagens, com metodos comuns como:
    // TODO (continuar): getContent, getSender, fields comuns relacionados, etc

    private String method;

    private String content;

    public RESTMessage(HttpExchange exchange) {
        String path = exchange.getRequestURI().getPath();
        String[] paths = path.split("/"); // path[0] é "" e path[1] é o context do servidor http eg:url /app/createUser

        String params_string = new String();
        if (exchange.getRequestMethod().equals(HttpMethod.GET))
            params_string = exchange.getRequestURI().getQuery();
        else try {
            params_string = this.readRequestBody(exchange);
        } catch (java.io.IOException e) {
            // TODO STH e avaliar se isto vai aqui
        }

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
                //sendResponse(exchange, HTTP_NOTFOUND_STATUS, "Not Found");
        }
    }

    private HashMap<String, String> getParams(String query) {
        HashMap<String, String> params = new HashMap<String, String>();

        if (query != null && !query.equals("")) {
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

    private String readRequestBody(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
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
