/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mobilepoc.service;

import mobilepoc.client.yahoo.dto.DailyYahooPriceData;
import mobilepoc.client.yahoo.dto.PriceBarData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Double.parseDouble;

/**
 * @author mohan
 */
public class DailyYahooPriceRowMapper {

    public static class RowMapperException extends Exception {

        public RowMapperException(String message, Throwable cause) {
            super(message, cause);

        }

        public RowMapperException(String message) {
            super(message);

        }

        public RowMapperException(Throwable cause) {
            super(cause);

        }
    }

    private static final int MAX_COLS = 8;
    private static final Pattern MM_DD_YYYY_PATTERN = Pattern.compile("([0-1]{0,1}[0-9]).([0-3]{0,1}[0-9]).([0-9]{4})");

    @SuppressWarnings("unchecked")
    public DailyYahooPriceData mapRow(String[] values) throws RowMapperException {
        try {
            String symbol = replaceDoubleQuote(values[0]);
            Date transDate = parseDateFromMMDDYYYY(replaceDoubleQuote(values[1]));
            Double open = parseDouble(replaceDoubleQuote(values[2]));
            Double high = parseDouble(replaceDoubleQuote(values[3]));
            Double low = parseDouble(replaceDoubleQuote(values[4]));
            Double close = parseDouble(replaceDoubleQuote(values[5]));
            Double volume = parseDouble(replaceDoubleQuote(values[6]));
            return new DailyYahooPriceData(symbol, new PriceBarData(transDate, open, high, low, close, volume));

        } catch (Exception ex) {
            throw new RowMapperException(ex);
        }

    }

    public static String replaceDoubleQuote(String string) {
        return string.replaceAll("\"", "");

    }


    public static Date parseDateFromMMDDYYYY(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        Matcher matcher = MM_DD_YYYY_PATTERN.matcher(date);
        if (!matcher.matches()) {
            throw new ParseException("Invalid date: Date should be of format YYYY{.}MM{.}DD: " + date, -1);
        }
        String yyyymmdd = matcher.group(1) + "-" + matcher.group(2) + "-" + matcher.group(3);
        return format.parse(yyyymmdd);
    }

    public Integer getMinimumNumberOfColumns() {
        return MAX_COLS;
    }

}
