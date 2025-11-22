
public interface FileSerializable<T> {
    
    String serialize(); // Object to String
    T deserialize(String line); // String to Object
}
