package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Disease {
    private StringProperty icdCode = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();

    private transient MyCheckBox myCheckBox = new MyCheckBox();

    public Disease() {
    }

    public Disease(String icdCode, String name) {
        this.icdCode.set(icdCode);
        this.name.set(name);
    }

    public String getIcdCode() {
        return icdCode.get();
    }

    public StringProperty icdCodeProperty() {
        return icdCode;
    }

    public void setIcdCode(String icdCode) {
        this.icdCode.set(icdCode);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public MyCheckBox getMyCheckBox() {
        return myCheckBox;
    }
}
