package edu.upenn.cit594.datamanagement;

import java.io.File;
import java.io.FileReader;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import edu.upenn.cit594.logging.Logger;

//This class implements Reader interface. 
//It handles the reading of the population text file and exposes a method to be used by the Processor to get the data.
public class TextFileReader {
	protected String fileName;

	public TextFileReader(String name) {
		fileName = name;
	}

	public int getTotalPopulation() {
		int totalPopulation = 0;
		Scanner in = null;
		String line = "";

		try {
			File file = new File(fileName);
			assert (file.exists());
			in = new Scanner(file);
			Logger.getInstance().log(fileName);

			while (in.hasNextLine()) {
				line = in.nextLine();
				String[] info = line.split(" ");
				int population = Integer.parseInt(info[1]);
				totalPopulation += population;
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			in.close();
		}
		return totalPopulation;
	}

	public Map<String, Double> buildPopulationZipMap() throws Exception {
		Map<String, Double> populationEachZip = new HashMap<String, Double>();
		Scanner in = null;
		String line = "";

		try {
			File file = new File(fileName);
			assert (file.exists());
			in = new Scanner(file);
			Logger.getInstance().log(fileName);

			while (in.hasNextLine()) {
				line = in.nextLine();
				String[] info = line.split(" ");
				String zip = info[0];
				double population = Double.parseDouble(info[1]);

				if (populationEachZip.get(zip) == null) {
					populationEachZip.put(zip, population);
				} else {
					populationEachZip.put(zip, populationEachZip.get(zip) + population);
				}
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			in.close();
		}
		return populationEachZip;
	}
}
