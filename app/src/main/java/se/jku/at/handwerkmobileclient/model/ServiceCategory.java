package se.jku.at.handwerkmobileclient.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("servicecategory")
public enum ServiceCategory implements Serializable{

	Fenster("Fenster"),
    Tischler("Tischler"),
    Sonstiges("Sonstiges"),
    Maurer("Maurer"),
    Installateur("Installateur"),
    KFZ("KFZ")
    ;
	
	private final String text;

    /**
     * @param text
     */
    private ServiceCategory(final String text) {
        this.text = text;
    }
    

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
	
}
