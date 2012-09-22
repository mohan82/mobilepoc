/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mobilepoc.client.yahoo.dto;

/**
 *
 * @author mohan
 */
public class DailyYahooPriceData implements java.io.Serializable {

    private String symbol;
    private PriceBarData barData;

    public DailyYahooPriceData(String symbol,PriceBarData barData) {
        
        this.symbol =symbol;
        this.barData =barData;
    }

    public PriceBarData getBarData() {
        return barData;
    }

    public void setBarData(PriceBarData barData) {
        this.barData = barData;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
