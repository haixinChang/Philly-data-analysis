package edu.upenn.cit594.datamanagement;

import java.util.*;
import java.util.concurrent.Callable;

import edu.upenn.cit594.data.ZipPropertyData;
import edu.upenn.cit594.logging.Logger;

import java.io.*;

public class PropertyValueCSVFileReader {
	protected String fileName;

	public PropertyValueCSVFileReader(String name) {
		fileName = name;
	}

	// Strategy Pattern: this function creates an object based on user's choice and
	// uses the ZipPropertyData class which is part of a larger algorithm.
	// This allows for a more general algorithm with details filled in by strategy
	// objects.
	public Map<String, ZipPropertyData> buildPropertyDataForEachZipMap(String type)
			throws FileNotFoundException, IOException {
		Map<String, ZipPropertyData> propertyDataMap = new HashMap<>();
		Scanner in = null;
		String line = "";
		String zip = "";
		ZipPropertyData zipData;
		int zipIndex = 0;
		int typeIndex = 0;

		try {
			File file = new File(fileName);
			assert (file.exists());
			in = new Scanner(file);
			// call log
			Logger.getInstance().log(fileName);
			String[] header = in.nextLine().split(",");

			for (int i = 0; i < header.length; i++) {
				if (header[i].equalsIgnoreCase("zip_code")) {
					zipIndex = i;
				}
				if (header[i].equalsIgnoreCase(type)) {
					typeIndex = i;
				}
			}

			while (in.hasNextLine()) {
				line = in.nextLine();

				try {
					String[] info = line.split(",");
					zip = info[zipIndex].substring(0, 5);

					if (!propertyDataMap.containsKey(zip)) {
						zipData = new ZipPropertyData();
						propertyDataMap.put(zip, zipData);
					} else {
						zipData = propertyDataMap.get(zip);
					}

					try {
						double typeValue = Double.parseDouble(info[typeIndex]);
						zipData.addTotal(typeValue);
					} catch (Exception e) {
						// skip the invalid market value, but process the rest of the row
					}
				} catch (Exception e) {
					// skip the whole row if zip input is invalid
					continue;
				}
			}

		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return propertyDataMap;
	}
}
