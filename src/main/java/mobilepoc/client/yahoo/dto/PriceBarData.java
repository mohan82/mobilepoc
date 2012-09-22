package mobilepoc.client.yahoo.dto;

import java.util.Date;

public class PriceBarData {

    private Date transDate;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;
    private Double adjClose;

    public PriceBarData(Date transDate, Double open, Double high, Double low,
            Double close, Double volume) {
        super();
        this.transDate = transDate;
        this.open = open;
        this.high = high;
        this.close = close;
        this.low = low;
        this.volume = volume;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getLow() {
        return low;
    }

    public Double getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(Double adjClose) {
        this.adjClose = adjClose;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PriceBarData other = (PriceBarData) obj;
        if (this.transDate != other.transDate && (this.transDate == null || !this.transDate.equals(other.transDate))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.transDate != null ? this.transDate.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "PriceBarData [close=" + close + ", high=" + high + ", low="
                + low + ", open=" + open + ", transDate=" + transDate
                + ", volume=" + volume + "]";
    }
}
