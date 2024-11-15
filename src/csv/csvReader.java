package csv;

import main.utils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class csvReader {
	public static List<List<String>> parse(String path) {
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<String> row = new ArrayList<>();
                for (String value : values) {
                    row.add(value.trim());
                }
                records.add(row);
            }
		}
		catch (Exception e) {
			utils.error("Error reading the file: " + e.getMessage());
		}

		return records;
	}
}
