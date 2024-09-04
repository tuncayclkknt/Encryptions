package com.example.encryptions;

import java.util.ArrayList;
import java.util.List;

public class EncMethods {
    static ArrayList<Integer> mods = new ArrayList<>();

    public ArrayList<Character> lowerCases = new ArrayList<>();
    public ArrayList<Character> upperCases = new ArrayList<>();

    public void initializeAlphabet() {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            lowerCases.add(ch);
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            upperCases.add(ch);
        }
    }

    public static ArrayList<Integer> modsOfNumber(int encFark){

        while (encFark < 0)
            encFark += 26;

        for (int i = 0; i < 50; i++){
            mods.add(encFark + (26 * i));
        }
        return mods;
    }

    public static int modInverse(int num) {

        num = num % 26;
        for (int i = 1; i < 26; i++) {

            if ((num * i) % 26 == 1) {
                return i;
            }
        }
        return 1;
    }

    public String shiftCipher(String message, int shifting) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char yeniH = ' ';
            char currentChar = message.charAt(i);

            if (lowerCases.contains(currentChar)) {
                int eskiHI = lowerCases.indexOf(currentChar);
                yeniH = lowerCases.get(((eskiHI + shifting) + 26) % 26);
            } else if (upperCases.contains(currentChar)) {
                int eskiHI = upperCases.indexOf(currentChar);
                yeniH = upperCases.get(((eskiHI + shifting) + 26) % 26);
            } else if (currentChar == ' ') {
                yeniH = ' ';
            } else if (currentChar == '\n')
                yeniH = '\n';
            encryptedMessage.append(yeniH);
        }
        return encryptedMessage.toString();
    }

    public List<String> dencShiftCipher(String message) {
        List<String> decryptedMessages = new ArrayList<>();

        for (int j = 1; j < 26; j++) {
            StringBuilder decryptedMessage = new StringBuilder();
            for (int i = 0; i < message.length(); i++) {
                char a = message.charAt(i);

                if (lowerCases.contains(a)) {
                    a = lowerCases.get(((lowerCases.indexOf(a) - j) + 26) % 26);
                    decryptedMessage.append(a);
                } else if (upperCases.contains(a)) {
                    a = upperCases.get(((upperCases.indexOf(a) - j) + 26) % 26);
                    decryptedMessage.append(a);
                } else if (a == ' ') {
                    decryptedMessage.append(' ');
                }
            }
            decryptedMessages.add("The message for shift " + j + ": " + decryptedMessage);
        }
        return decryptedMessages;
    }


    public String affineCipher(String message) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char yeniH = ' ';

            if (lowerCases.contains(message.charAt(i))) {
                int eskiHI = lowerCases.indexOf(message.charAt(i));
                yeniH = lowerCases.get(((eskiHI * 5 + 8) + 26) % 26);
            } else {
                int eskiHI = upperCases.indexOf(message.charAt(i));
                if (message.charAt(i) != ' ') {
                    yeniH = upperCases.get(((eskiHI * 5 + 8) + 26) % 26);
                }
            }
            encryptedMessage.append(yeniH);
        }
        return encryptedMessage.toString();
    }

    public String dencAffineCipher(String message, int key1, int key2) {
        StringBuilder decryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char yeniH = ' ';
            if (lowerCases.contains(message.charAt(i))) {
                int eskiHI = lowerCases.indexOf(message.charAt(i));
                yeniH = lowerCases.get((modInverse(key1) * ((eskiHI - key2) + 26)) % 26);
            } else if (upperCases.contains(message.charAt(i))) {
                int eskiHI = upperCases.indexOf(message.charAt(i));
                yeniH = upperCases.get((modInverse(key1) * ((eskiHI - key2) + 26)) % 26);
            } else if (message.charAt(i) == ' ') {
                yeniH = ' ';
            }
            decryptedMessage.append(yeniH);
        }
        return decryptedMessage.toString();
    }

    public String findKeysForAffineCipher(String orjMess, String encMess) {
        int a = -1, b = -1;
        int mesaj1, mesaj2, encmesaj1, encmesaj2;

        if (upperCases.contains(orjMess.charAt(0)))
            mesaj1 = upperCases.indexOf(orjMess.charAt(0));
        else
            mesaj1 = lowerCases.indexOf(orjMess.charAt(0));

        if (upperCases.contains(orjMess.charAt(1)))
            mesaj2 = upperCases.indexOf(orjMess.charAt(1));
        else
            mesaj2 = lowerCases.indexOf(orjMess.charAt(1));

        if (upperCases.contains(encMess.charAt(0)))
            encmesaj1 = upperCases.indexOf(encMess.charAt(0));
        else
            encmesaj1 = lowerCases.indexOf(encMess.charAt(0));

        if (upperCases.contains(encMess.charAt(1)))
            encmesaj2 = upperCases.indexOf(encMess.charAt(1));
        else
            encmesaj2 = lowerCases.indexOf(encMess.charAt(1));

        int encFark = encmesaj1 - encmesaj2;
        int mesajFark = mesaj1 - mesaj2;

        int katsayiInverse = modInverse(mesajFark);
        encFark *= katsayiInverse;
        mesajFark *= katsayiInverse;

        while (mesajFark < 0) {
            mesajFark += 26;
        }

        StringBuilder result = new StringBuilder();
        modsOfNumber(encFark);

        for (int x : mods) {
            int reminder = x % mesajFark;
            if (reminder == 0) {
                a = x / mesajFark;
                b = ((encmesaj1 - (mesaj1 * a)) + 2600) % 26;
                break;
            }
            result.append("\n");
        }
        return "a = " + a + ", b = " + b;
    }

}
