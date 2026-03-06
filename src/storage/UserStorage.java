package storage;

import crypto.HashUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static final String FILE_NAME = "users.txt";

    public static boolean registerUser(String username, String password) throws Exception {
        Map<String, String> users = loadUsers();
        if (users.containsKey(username)) return false;

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String hashedPassword = HashUtil.sha256(password);
            bw.write(username + ":" + hashedPassword);
            bw.newLine();
        }
        return true;
    }

    public static boolean validateLogin(String username, String password) throws Exception {
        Map<String, String> users = loadUsers();
        String hashedPassword = HashUtil.sha256(password);
        return users.containsKey(username) && users.get(username).equals(hashedPassword);
    }

    private static Map<String, String> loadUsers() throws IOException {
        Map<String, String> users = new HashMap<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return users;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    users.put(parts[0], parts[1]);
                }
            }
        }
        return users;
    }
}
