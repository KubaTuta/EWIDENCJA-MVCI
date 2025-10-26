import utils.CsvReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Interactor {
    private Model model;
    private static final String FILE_PATH = "C:/JAVA/EWIDENCJA MVCI/src/resources/csv/csv.csv";

    public Interactor(Model model)  {
        this.model = model;
    }

    public List<Car> loadCarsFromCsv() {
        Path path = Paths.get(FILE_PATH);
        List<String[]> rows = CsvReader.readCsvFile(path);
        List<Car> cars = new ArrayList<>();

        for (String[] row : rows) {
            Car car = new Car(row);
            cars.add(car);
        }
        return cars;
    }

}