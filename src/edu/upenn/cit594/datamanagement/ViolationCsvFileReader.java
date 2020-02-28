package edu.upenn.cit594.datamanagement;

import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.logging.Logger;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ViolationCsvFileReader extends ViolationFileReader {
    public List<Violation> parse(String filename) throws IOException, ParseException {
        Scanner in = null;
        List<Violation> result = new ArrayList<>();
        try {
            File file = new File(filename);
            log("file: " + filename + " opened");
            in = new Scanner(file);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] info = line.split(",");
                if (info.length != 7) {
                    continue;
                }
                Date date = dateFormat.parse(info[0]);
                int fine = Integer.parseInt(info[1]);
                String desc = info[2];
                String plateId = info[3];
                String state = info[4];
                int ticketId = Integer.parseInt(info[5]);
                String zip = info[6];
                Violation v = createViolation(ticketId, plateId, date, zip, desc, fine, state);
                result.add(v);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return dedupeViolations(result);
    }
}
