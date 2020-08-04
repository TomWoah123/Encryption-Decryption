import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileReader {
    public static void main(String[] args) {
        File file = new File("./src/dataset_91065.txt");
        try {
            Scanner scan = new Scanner(file);
            int result = 0;
            while (scan.hasNext()) {
                int num = scan.nextInt();
                if (num % 2 == 0) {
                    result++;
                }
            }
            System.out.println(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
