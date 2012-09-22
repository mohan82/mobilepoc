package mobilepoc.client.yahoo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class YahooRestPriceService  {

    private YahooClientHelper yahooRestClient;

    public void setYahooRestClient(YahooClientHelper yahooRestClient) {
        this.yahooRestClient = yahooRestClient;
    }

    public List<String[]> getStockQuotes(List<String> stockSymbols,
            List<String> fields) {

        return yahooRestClient.getDailyQuotes(stockSymbols, fields);
    }

    public byte[] getStockQuotesAsByteArray(List<String> stockSymbols, List<String> fields) {
        return yahooRestClient.getDailyQuotesAsByteArray(stockSymbols, fields);
    }


}
