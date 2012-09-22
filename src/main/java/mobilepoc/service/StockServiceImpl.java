package mobilepoc.service;

import mobilepoc.client.yahoo.YahooRestPriceService;
import mobilepoc.client.yahoo.dto.DailyYahooPriceData;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mohan
 * Date: 22/09/12
 * Time: 7:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockServiceImpl implements StockService {
    private YahooRestPriceService yahooRestPriceService;

    public StockServiceImpl(YahooRestPriceService yahooRestPriceService) {
        this.yahooRestPriceService = yahooRestPriceService;
    }


    @Override
    public List<DailyYahooPriceData> getDailyYahooPriceData(String symbol) throws FileParseException {
        byte[] bytes = yahooRestPriceService.getStockQuotesAsByteArray(Collections.singletonList(symbol), DailyPriceYahooFieldsEnum.getEnumsFieldsAsList());
        Parser<DailyYahooPriceData> parser = new Parser<DailyYahooPriceData>();
        parser.setFileDelimeter(",");
        parser.setHasHeader(false);
        parser.setFileRowMapper(new DailyYahooPriceRowMapper());
        return parser.processByteArray(bytes);
    }
}
