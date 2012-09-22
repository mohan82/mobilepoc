package mobilepoc.client.yahoo;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class RestUtil {

    public static List<String[]> parseCSV(InputStream inputStream)
            throws IOException {
        LineNumberReader lineReader = null;
        try {
            lineReader = new LineNumberReader(new BufferedReader(
                    new InputStreamReader(inputStream)));
            String line = null;
            List<String[]> valueList = new ArrayList<String[]>();
            while ((line = lineReader.readLine()) != null) {
                valueList.add(line.split(","));
            }
            return valueList;
        } finally {
            if (lineReader != null) {
                lineReader.close();
            }
        }
    }

    public static String getListAsCSV(List<String> values) {
        boolean first = true;
        StringBuilder csvBuilder = new StringBuilder(values.size());
        for (String value : values) {
            if (first) {
                csvBuilder.append(value);
                first = false;
            } else {
                csvBuilder.append(",").append(value);
            }

        }
        return csvBuilder.toString();

    }

    public static String getListAsString(List<String> values) {
        StringBuilder stringBuilder = new StringBuilder(values.size());
        for (String value : values) {
            stringBuilder.append(value);
        }
        return stringBuilder.toString();

    }
    private static final Pattern DD_MM_YYYY_PATTERN = Pattern.compile("([0-3]{0,1}[0-9]).([0-1]{0,1}[0-9]).([0-9]{4})");
    private static Matcher ddMMYYYYMatcher = DD_MM_YYYY_PATTERN.matcher("");
    private static final SimpleDateFormat DD_MM_YYYY_FORMAT = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    public static Date parseDateFromYYYYMMDD(String date) throws ParseException {
        ddMMYYYYMatcher.reset(date);
        if (!ddMMYYYYMatcher.matches()) {
            throw new ParseException("Invalid date: Date should be of format YYYY{.}MM{.}DD: " + date, -1);
        }
        String yyyymmdd = ddMMYYYYMatcher.group(1) + "-" + ddMMYYYYMatcher.group(2) + "-" + ddMMYYYYMatcher.group(3);
        return DD_MM_YYYY_FORMAT.parse(yyyymmdd);
    }
}
