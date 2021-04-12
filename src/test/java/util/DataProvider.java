package util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    public static Object[] read(String dataName) throws IOException {
        List<String> stringList = new ArrayList<>();

        Reader csvData = new FileReader("src/test/resources/testdata/testData.csv");
        CSVParser parser = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(csvData);

        for (CSVRecord csvRecord : parser) {
            stringList.add(csvRecord.get(dataName));
        }

        return stringList.toArray();
    }
}
