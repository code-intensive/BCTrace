package codeaggressive.com.bctrace;

public class Datas {
    private String batch,mixer,sku,keg,date,time,operator,bin;

    public Datas() {
    }

    public Datas(String batch, String mixer, String sku, String keg, String date, String time, String operator, String bin) {
        this.batch = batch;
        this.mixer = mixer;
        this.sku = sku;
        this.keg = keg;
        this.date = date;
        this.time = time;
        this.operator = operator;
        this.bin = bin;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getMixer() {
        return mixer;
    }

    public void setMixer(String mixer) {
        this.mixer = mixer;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getKeg() {
        return keg;
    }

    public void setKeg(String keg) {
        this.keg = keg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }
}
