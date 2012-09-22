/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mobilepoc.client.yahoo;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import static mobilepoc.client.yahoo.DailyPriceParamEnum.*;


/**
 * @author mohan
 */
public class YahooClientHelper {

    private WebResource dailyWebResource;
    private Logger logger = LoggerFactory.getLogger(YahooClientHelper.class);


    public void setDailyWebResource(WebResource dailyWebResource) {
        this.dailyWebResource = dailyWebResource;
    }


    public List<String[]> getDailyQuotes(List<String> symbolList, List<String> fields) {
        try {
            byte[] response = getDailyQuotesAsByteArray(symbolList, fields);

            return RestUtil.parseCSV(new ByteArrayInputStream(response));
        } catch (IOException ex) {
            throw new CannotDownloadPriceException(RestUtil.getListAsCSV(symbolList), " For fields" + RestUtil.getListAsCSV(fields));
        }

    }

    public byte[] getDailyQuotesAsByteArray(List<String> symbolList, List<String> fields) {
        MultivaluedMap<String, String> params = new MultivaluedMapImpl();
        params.add(DLY_SYMBOL.getParameter(), RestUtil.getListAsCSV(symbolList));
        params.add(DLY_FIELD.getParameter(), RestUtil.getListAsString(fields));
        dailyWebResource = dailyWebResource.queryParams(params);
        logger.info("Downloading daily quote for URL :" + dailyWebResource.toString());
        byte[] response = dailyWebResource.accept(MediaType.APPLICATION_OCTET_STREAM_TYPE).get(byte[].class);
        return response;
    }


}
