package mobilepoc.service;

import mobilepoc.client.yahoo.dto.DailyYahooPriceData;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mohan
 * Date: 22/09/12
 * Time: 7:11 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StockService {
        List<DailyYahooPriceData> getDailyYahooPriceData(String symbol)throws FileParseException;
}
