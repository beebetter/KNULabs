import java.util.Set;
import java.util.HashSet;
import java.nio.file.*;
//Task N 5
//Знайти лише ті слова, які мають здвоєні приголосні літери.
public class SP15_Lab1 {
    public static void main(String[] args) {
        try
        {
            Path pathToRead = FileSystems.getDefault().getPath("input.txt");
            if (Files.notExists(pathToRead)){
                System.out.println("There is no input.txt file in current directory");
            }
            else{

                String text = new String(Files.readAllBytes(pathToRead));
                String[] words = text.split("[^a-zA-Z]");
                Set result = new HashSet<String>();

                for (String word : words){
                    if (word.matches("[a-zA-Z]*[^eEaAuUiIoO][^eEaAuUiIoO][a-zA-Z]*"))
                        result.add(word);
                }

                Path pathToWrite = FileSystems.getDefault().getPath("output.txt");
                if (Files.notExists(pathToWrite)){
                    Files.createFile(pathToWrite);
                }
                Files.write(pathToWrite, result);

                System.out.println("All things are done.");
            }
        }
        catch(Exception e)
        {
            System.err.println(e.toString());
        }
    }
}