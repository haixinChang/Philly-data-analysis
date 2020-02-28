package edu.upenn.cit594.datamanagement;

import java.util.*;

import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.logging.Logger;

public abstract class ViolationFileReader {
    public ViolationFileReader() {
    }


    protected boolean validateViolation(int ticketNumber, String plateId, Date date, String zip, String description, int fine, String state) {
        return ticketNumber > 0 && plateId.length() > 0 && date != null && zip.length() > 0 && state.length() == 2;
    }


    protected Violation createViolation(int ticketNumber, String plateId, Date date, String zip, String description, int fine, String state) {
        if (validateViolation(ticketNumber, plateId, date, zip, description, fine ,state )) {
            return new Violation(ticketNumber, date, fine, description, plateId, state, zip);
        }
        return null;
    }

    protected void log(String msg) {
        Logger.getInstance().log(msg);
    }

    /**
     * @return copy of param list with duplicate Violations (by id) removed
     */
    protected List<Violation> dedupeViolations(List<Violation> violations) {
        Set<Violation> uniques = new HashSet<>(violations);
        return new ArrayList<>(uniques);
    }

    public abstract List<Violation> parse(String filename) throws Exception;
}
