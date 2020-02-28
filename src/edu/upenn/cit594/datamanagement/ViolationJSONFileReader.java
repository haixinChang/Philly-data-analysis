package edu.upenn.cit594.datamanagement;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import edu.upenn.cit594.data.Violation;
import edu.upenn.cit594.logging.Logger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ViolationJSONFileReader extends ViolationFileReader {
    public List<Violation> parse(String filename) throws IOException, ParseException, java.text.ParseException {
        JSONParser parser = new JSONParser();
        FileReader fr = new FileReader(filename);
        log("file: " + filename + " opened");
        JSONArray violations = (JSONArray)parser.parse(fr);
        Iterator it = violations.iterator();
            List<Violation> result = new ArrayList<>();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            while (it.hasNext()) {
                JSONObject violationJSON = (JSONObject)it.next();
                int ticketNumber = Math.toIntExact((Long)violationJSON.get("ticket_number"));
                String plateId = (String)violationJSON.get("plate_id");
                Date date = dateFormat.parse((String)violationJSON.get("date"));
                String zip = (String)violationJSON.get("zip_code");
                String description = (String)violationJSON.get("violation");
                int fine = Math.toIntExact((Long)violationJSON.get("fine"));
                String state = (String)violationJSON.get("state");
                Violation violation = createViolation(ticketNumber, plateId, date, zip, description, fine, state);
                if (violation != null) {
                    result.add(violation);
                }
            }
            fr.close();
            return dedupeViolations(result);
    }
}
