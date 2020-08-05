package encryptdecrypt;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
public class Main {
    public static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public static void main(String[] args) {
        String mode = "enc";
        int key = 0;
        String data = "";
        boolean out = false;
        String outFile = "";
        if (args.length % 2 == 1) {
            return;
        }
        try {
            for (int i = 0; i < args.length; i += 2) {
                if (args[i].equals("-mode") && args[i + 1].equals("dec")) {
                    mode = "dec";
                }
                if (args[i].equals("-key")) {
                    key = Integer.parseInt(args[i + 1]);
                }
                if (args[i].equals("-data")) {
                    data = args[i + 1];
                } else if (args[i].equals("-in")) {
                    File file = new File(args[i+1]);
                    Scanner scan = new Scanner(file);
                    while (scan.hasNext()) {
                        data += scan.nextLine();
                    }
                    scan.close();
                }
                if (args[i].equals("-out")) {
                    out = true;
                    outFile = args[i+1];
                }
            }
            String result = (mode.equals("enc")) ? encryptAscii(data, key) : decryptAscii(data, key);
            if (out) {
                File f = new File(outFile);
                FileWriter fileWriter = new FileWriter(f, true);
                fileWriter.write(result);
                fileWriter.close();
            } else {
                System.out.println(result);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String encrypt(String str){
        String result = "";
        str.toLowerCase();
        for ( int i = 0; i < str.length(); i++ ){
            if ( Character.isLetter(str.charAt(i))){
                int pos = alphabet.indexOf(str.charAt(i));
                result += alphabet.charAt( alphabet.length() - 1 - pos );
            }
            else{
                result += str.charAt(i);
            }
        }
        return result;
    }

    public static String encrypt(String str, int num) {
        String result = "";
        str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                int pos = alphabet.indexOf(str.charAt(i));
                result += alphabet.charAt((pos + num) % alphabet.length());
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

    public static String encryptAscii(String str, int num) {
        String result = "";
        for (int i = 0; i < str.length(); i++ ) {
            int n = str.charAt(i);
            char c = (char) (n + num);
            result += c;
        }
        return result;
    }

    public static String decryptAscii(String str, int num) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int n = str.charAt(i);
            char c = (char) (n - num);
            result += c;
        }
        return result;
    }
}
