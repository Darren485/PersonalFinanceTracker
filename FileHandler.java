
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class FileHandler {

    private static final long serialVersionUID = 1L;
    public static final Path FILE_PATH = Paths.get("data", "transaction.dat");

    public void save(FinanceManager manager) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("transaction.dat"))) {
            oos.writeObject(manager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FinanceManager load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("transaction.dat"))) {
            return (FinanceManager) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new FinanceManager();
        }
    }
}
