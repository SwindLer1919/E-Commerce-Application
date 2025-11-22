import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager<T extends FileSerializable<T>> {
    
    private final String fileName;
    private final T prototype; // deserialize işlemi için boş örnek
    
    public FileManager(String fileName, T prototype) {
        this.fileName = System.getProperty("user.dir") + File.separator + fileName;
        this.prototype = prototype;
    }
    
    public void saveToFile(List<T> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (T item : data) {
                writer.write(item.serialize());
                writer.newLine();
            }
        } catch (IOException ex) {
            System.out.println("Yazma Hatası" + ex.getMessage()); // bu şekilde atılmayacak
        }
    }
    
    // kendi ex'i de var haberin olsun lo
    public List<T> loadFromFile() {
        
        List<T> data = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) return data;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null ) {
                data.add(prototype.deserialize(line));
            }
        } catch (IOException ex) {
            System.out.println("Okuma Hatası: " + ex.getMessage());
        }
        return data;
    }
}