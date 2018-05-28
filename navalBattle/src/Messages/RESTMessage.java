package Messages;

import Communication.REST.HTTPMethod;
import Communication.REST.RestServerHandler;

import com.sun.net.httpserver.HttpExchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.util.HashMap;

/**
 * The Class RESTMessage.
 */
public class RESTMessage implements Message {

    /** The Constant PARAM_NAME_IDX. */
    private static final int PARAM_NAME_IDX = 0;
    
    /** The Constant PARAM_VALUE_IDX. */
    private static final int PARAM_VALUE_IDX = 1;

    /** The Constant AND_DELIMITER. */
    private static final String AND_DELIMITER = "&";
    
    /** The Constant EQUAL_DELIMITER. */
    private static final String EQUAL_DELIMITER = "=";

    // TODO : provavelmente posso ter uma Class comum entre ambas as mensagens, com metodos comuns como:
    // TODO (continuar): getContent, getSender, fields comuns relacionados, etc

    /** The method. */
    private String method;

    /** The content. */
    private String content;

    /** The context. */
    private String context = null;

    /** The status code. */
    private int statusCode;

    /** The params. */
    private HashMap<String, String> params;

    /** The exchange. */
    private HttpExchange exchange;

    /**
     * Instantiates a new REST message.
     *
     * @param exchange the HttpExchange
     */
    public RESTMessage(HttpExchange exchange) {

        this.exchange = exchange;
        method = exchange.getRequestMethod();

        String path = exchange.getRequestURI().getPath();
        String[] paths = path.split("/"); // path[0] é "" e path[1] é o context do servidor http eg:url /app/createUser

        String params_string = new String();
        if (method.equals(HTTPMethod.GET))
            params_string = exchange.getRequestURI().getQuery();
        else
            try {
            params_string = this.readRequestBody(exchange);
        } catch (java.io.IOException e) {
            // TODO sth e avaliar se isto vai aqui
        }
        params = extractParams(params_string);

        if (paths.length > 2 )
            context = paths[2];
    }

    /**
     * Instantiates a new REST message.
     *
     * @param firstMessage the first message
     * @param statusCode the status code
     * @param content the content
     */
    public RESTMessage(RESTMessage firstMessage, int statusCode, String content) {
        this.exchange = firstMessage.getExchange();
        this.statusCode = statusCode;
        this.content = content;
    }

    /**
     * Send a reply to the player.
     */
    public void sendAsReply() {
        RestServerHandler.sendResponse(exchange, statusCode, content);
    }

    /**
     * Extract parameters.
     *
     * @param query the query
     * @return the hash map
     */
    private HashMap<String, String> extractParams(String query) {
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

    /**
     * Read request body.
     *
     * @param exchange the exchange
     * @return the string
     * @throws IOException Signals that an I/O exception has occurred.
     */
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

    /**
     * Gets the parameters.
     *
     * @return the parameters
     */
    public HashMap<String, String> getParams() {
        return params;
    }

    /**
     * Gets the exchange.
     *
     * @return the exchange
     */
    public HttpExchange getExchange() {
        return exchange;
    }

    /**
     * Gets the context.
     *
     * @return the context
     */
    public String getContext() {
        return context;
    }

    /**
     * Gets the method.
     *
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    public InetSocketAddress getAddress() {
        return exchange.getRemoteAddress();
    }

}
