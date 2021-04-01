package util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomDataProvider {
    public static Object[] readCsv(String fileName) throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/testdata/" + fileName));

        List<String[]> csvData = csvReader.readAll();

        Object[] csvDataObject = new Object[csvData.size()][2];

        for (int i = 0; i < csvData.size(); i++) {
            csvDataObject[i] = csvData.get(i);
        }

        return csvDataObject;
    }
}
