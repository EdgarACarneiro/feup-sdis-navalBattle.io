package Authentication;

import java.util.HashMap;

public class AuthManager implements Runnable {

    private HashMap<String, Double> users = new HashMap<>();
    private HashMap<String, Double> loggedUsers = new HashMap<>();

    public AuthManager() {

    }

    public void authentication(String username) {

        if (!users.containsKey(username))
            users.put(username, 0.0);

        double score = users.get(username);
        loggedUsers.put(username, score);

    }

    public HashMap<String, Double> getUsers(){
        return users;
    }

    public HashMap<String, Double> loggedUsers(){
        return loggedUsers;
    }

    @Override
    public void run() {

    }
}
