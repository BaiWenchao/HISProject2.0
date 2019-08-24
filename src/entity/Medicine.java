package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Medicine {
    private StringProperty med_name = new SimpleStringProperty();
    private StringProperty med_spec = new SimpleStringProperty();
    private StringProperty med_use = new SimpleStringProperty();
    private StringProperty med_amount = new SimpleStringProperty();
    private StringProperty med_time = new SimpleStringProperty();
    private StringProperty med_price = new SimpleStringProperty();
    private StringProperty med_num = new SimpleStringProperty();

    private transient MyCheckBox myCheckBox = new MyCheckBox();

    public Medicine() {
    }

    public Medicine(String med_name, String med_spec, String med_price) {
        this.med_name.set(med_name);
        this.med_spec.set(med_spec);
        this.med_price.set(med_price);
    }

    public Medicine(String med_name, String med_spec, String med_use, String med_amount, String med_time, String med_price, String med_num) {
        this.med_name.set(med_name);
        this.med_spec.set(med_spec);
        this.med_use.set(med_use);
        this.med_amount.set(med_amount);
        this.med_time.set(med_time);
        this.med_price.set(med_price);
        this.med_num.set(med_num);
    }

    public String getMed_name() {
        return med_name.get();
    }

    public StringProperty med_nameProperty() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name.set(med_name);
    }

    public String getMed_spec() {
        return med_spec.get();
    }

    public StringProperty med_specProperty() {
        return med_spec;
    }

    public void setMed_spec(String med_spec) {
        this.med_spec.set(med_spec);
    }

    public String getMed_use() {
        return med_use.get();
    }

    public StringProperty med_useProperty() {
        return med_use;
    }

    public void setMed_use(String med_use) {
        this.med_use.set(med_use);
    }

    public String getMed_amount() {
        return med_amount.get();
    }

    public StringProperty med_amountProperty() {
        return med_amount;
    }

    public void setMed_amount(String med_amount) {
        this.med_amount.set(med_amount);
    }

    public String getMed_time() {
        return med_time.get();
    }

    public StringProperty med_timeProperty() {
        return med_time;
    }

    public void setMed_time(String med_time) {
        this.med_time.set(med_time);
    }

    public String getMed_price() {
        return med_price.get();
    }

    public StringProperty med_priceProperty() {
        return med_price;
    }

    public void setMed_price(String med_price) {
        this.med_price.set(med_price);
    }

    public String getMed_num() {
        return med_num.get();
    }

    public StringProperty med_numProperty() {
        return med_num;
    }

    public void setMed_num(String med_num) {
        this.med_num.set(med_num);
    }

    public MyCheckBox getMyCheckBox() {
        return myCheckBox;
    }
}
