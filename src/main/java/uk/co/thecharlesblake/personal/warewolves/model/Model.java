package uk.co.thecharlesblake.personal.warewolves.model;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;

import static uk.co.thecharlesblake.personal.warewolves.model.Model.Time.DAY;

public class Model {
    public enum Time {
        DAY,
        NIGHT
    }

    public Time time;
}
