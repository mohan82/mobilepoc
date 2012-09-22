package mobilepoc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser<T> {

    private DailyYahooPriceRowMapper fileRowMapper;
    private String fileDelimeter;
    private boolean hasHeader = false;
    public static final String CSV = ",";
    private Logger logger = LoggerFactory.getLogger(Parser.class);

    public void setFileDelimeter(String fileDelimeter) {
        this.fileDelimeter = fileDelimeter;

    }

    public void setFileRowMapper(DailyYahooPriceRowMapper fileRowMapper) {
        this.fileRowMapper = fileRowMapper;
    }

    public void setHasHeader(final boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public List<T> processFile(final String fileName) throws FileParseException {
        try {
            return procesLineNumberReader(new LineNumberReader(new BufferedReader(new FileReader(fileName))));
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<T> processByteArray(final byte[] bytes) throws FileParseException {
        return procesLineNumberReader(new LineNumberReader(
                new BufferedReader(new InputStreamReader(
                        new ByteArrayInputStream(bytes)))));
    }


    @SuppressWarnings("unchecked")
    private List<T> procesLineNumberReader(LineNumberReader reader) throws FileParseException {

        List<T> valuesList = new ArrayList<T>();
        try {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                line = line.trim();

                if (hasHeader) {
                    logger.info("Has header at " + reader.getLineNumber());
                    hasHeader = false;

                } else if (!line.isEmpty()) {
                    String[] values = line.split(fileDelimeter);
                    valuesList.add((T) fileRowMapper.mapRow(values));
                }

            }
        } catch (IOException io) {
            throw new RuntimeException(io);
        } catch (Exception e) {
            throw new FileParseException("Cannot parse the file "
                    + " delimeted by " + fileDelimeter
                    + " at line: " + reader.getLineNumber(), e);

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException io) {
                    throw new RuntimeException(io);
                }
            }
        }
        return valuesList;
    }

    public void validateNumberOfColumns(Integer columnLength, Integer minimumColumns) throws DailyYahooPriceRowMapper.RowMapperException {
        if (columnLength < minimumColumns) {
            throw new DailyYahooPriceRowMapper.RowMapperException(
                    "Invalid row minimum columns expected :" + minimumColumns);
        }
    }
}
