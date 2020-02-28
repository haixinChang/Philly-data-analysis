package edu.upenn.cit594.data;

// This class is used as part of a larger algorithm (strategy design pattern)
public class ZipPropertyData {
	private int count = 0;
	private double total = 0;

	public ZipPropertyData() {

	}

	public int getCount() {
		return count;
	}

	public double getTotal() {
		return total;
	}

	public void addTotal(double value) {
		total += value;
		count++;
	}
}
