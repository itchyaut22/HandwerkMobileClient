package se.jku.at.handwerkmobileclient.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Datum muss in folgendem Format übergeben werden: yyyy-MM-dd
 */
public class DateParam {
    private final Date date;

    public DateParam(String dateStr) throws WebApplicationException {
        if (dateStr == null || dateStr == "") {
            this.date = null;
            return;
        }
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new WebApplicationException(Response.status(Status.BAD_REQUEST)
                    .entity("Couldn't parse date string: " + e.getMessage())
                    .build());
        }
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {

        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd");
        return formate.format(getDate());
    }

}
