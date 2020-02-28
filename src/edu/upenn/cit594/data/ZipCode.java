package edu.upenn.cit594.data;

public class ZipCode {
    private int population;
    private String zipString;

    public ZipCode(int population, String zipString) {
        this.population = population;
        this.zipString = zipString;
    }

    public int getPopulation() {
        return population;
    }

    public String getZipString() {
        return zipString;
    }
}
