package edu.upenn.cit594.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.Collections;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.Processor;

// User interface tier: interact with the user and process the data
// Only depends on the processor tier, not the data tier
public class CommandLineUserInterface {
	protected Processor processor;
	protected Scanner in;

	public CommandLineUserInterface(Processor processor) {
		this.processor = processor;
	}

	public void start() {
		try {
			in = new Scanner(System.in);
			recordUserChoice();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			in.close();
		}
		Logger.getInstance().close();
		System.out.println("Program ends");
		System.exit(0);
	}

	private void recordUserChoice() throws Exception {
		prompt();

		try {
			int choice = in.nextInt();
			Logger.getInstance().log("user choice: " + choice);
			if (choice == 0) {
				return; // Break out of input loop
			} else if (choice == 1) {
				totalPopulation();
			} else if (choice == 2) {
				totalParkingFinesPerCapita();
			} else if (choice == 3) {
				averageMarketValueForResidences();
			} else if (choice == 4) {
				averageLivableAreaForResidences();
			} else if (choice == 5) {
				totalMarketValuePerCapita();
			} else if (choice == 6) {
				totalParkingFinesPerCapitaThreshold();
			} else if (choice != 0) {
				System.out.println("Invalid input");
			}
			System.out.println();
		} catch (NoDataForZipException e) {
			System.out.println(e.getMessage());
			System.out.println();
		} catch (InputMismatchException e) {
			// if user types in a non-integer value
			System.out.println("Invalid input");
			// clears buffer
			in.nextLine();
			System.out.println();
		}

		recordUserChoice();
	}

	private void prompt() {
		System.out.println("Enter 0 to exit the program,\n1 to the total population for all ZIP Codes,\n"
				+ "2 to show the total parking fines per capita for each ZIP Code,\n"
				+ "3 to show the average market value for residences in a specified ZIP Code,\n"
				+ "4 to show the average total livable area for residences in a specified ZIP Code,\n"
				+ "5 to show the total residential market value per capita for a specified ZIP Code,\n"
				+ "6 to show the total parking fines per capita for each ZIP Code filtered by threshold market value.");
	}

	public void totalParkingFinesPerCapita() throws Exception {
		List<String> lines = processor.totalFinesPerCapita();
		for (String line : lines) {
			System.out.println(line);
		}
	}

	public void totalParkingFinesPerCapitaThreshold() {
		System.out.println(
				"We will display only parking fines per capita for zip codes that meet your threshold average market value.");
		System.out.println(
				"This feature can be used to query if average market value of housing affects the amount of parking fines given per capita.");
		System.out.println("Please enter a dollar amount for the average market value threshold: ");
		double threshold;
		try {
			String dollarAmount = in.next();
			threshold = Double.parseDouble(dollarAmount);
			List<String> lines = processor.totalFinesPerCapitaThreshold(threshold);
			for (String line : lines) {
				System.out.println(line);
			}
		} catch (Exception e) {
			System.out.println("Invalid threshold amount entered. Please try again.");
			totalParkingFinesPerCapitaThreshold();
		}
	}

	public void totalPopulation() throws Exception {
		System.out.println("The total population for all zip codes are " + processor.totalPopulationAllZips());
	}

	public void averageMarketValueForResidences() throws FileNotFoundException, IOException {
		// passing market value
		System.out.println("Please enter the zip: ");
		String zip = in.next();
		Logger.getInstance().log(zip);
		int result = processor.averageMarketValueInOneZip(zip);

		if (result != 0) {
			System.out.println("The average market value for residences in " + zip + " is " + result);
		} else {
			System.out.println(0);
		}
	}

	public void averageLivableAreaForResidences() throws FileNotFoundException, IOException {
		// passing livable area
		System.out.println("Please enter the zip: ");
		String zip = in.next();
		Logger.getInstance().log(zip);
		int result = processor.averageLivableAreaInOneZip(zip);

		if (result != 0) {
			System.out.println("The average total livable area for residences in " + zip + " is " + result);
		} else {
			System.out.println(0);
		}
	}

	public void totalMarketValuePerCapita() throws Exception {
		// passing market value
		System.out.println("Please enter the zip: ");
		String zip = in.next();
		Logger.getInstance().log(zip);
		int result = processor.marketValuePerCapitaInOneZip(zip);

		if (result != 0) {
			System.out.println("The total residential market value per capita in " + zip + " is " + result);
		} else {
			System.out.println(0);
		}
	}
}
