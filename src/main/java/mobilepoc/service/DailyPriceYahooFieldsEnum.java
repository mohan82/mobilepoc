/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mobilepoc.service;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author mohan
 */
public enum DailyPriceYahooFieldsEnum {
    SYMBOL("s"),   
    TRANS_DATE("d1"),
    OPEN("o"),
    HIGH("h"),
    LOW("g"),
    CLOSE("l1"),
    VOLUME("v"),
    TRADE_TIME("t1");
    private String field;

    private DailyPriceYahooFieldsEnum(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    public static List<String> getEnumsFieldsAsList()
    {
        return Arrays.asList(new String[]{
                    SYMBOL.getField(), TRANS_DATE.getField(), OPEN.getField(),
                    HIGH.getField(), LOW.getField(), CLOSE.getField(),
                    VOLUME.getField(), TRADE_TIME.getField()});

    }

    @Override
    public String toString() {
        return "YahooFieldsEnum{" + "field=" + field + '}';
    }
    
}
