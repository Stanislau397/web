package edu.epam.web.util;

public class PasswordEncryption {

    public String encryptPassword(String password) {
        int key = 5;
        char[] chars = password.toCharArray();
        String encryptedPassword = "";
        for (char c : chars) {
            c += key;
            encryptedPassword = encryptedPassword + c;
        }
        return encryptedPassword;
    }
}
