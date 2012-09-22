package mobilepoc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mobilepoc.client.yahoo.dto.DailyYahooPriceData;
import mobilepoc.controller.StockParam;
import mobilepoc.service.StockService;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class StockController extends SimpleFormController {

    private StockService stockService;

    public StockController() {
        setCommandClass(StockParam.class);
        setCommandName("stockParam");
    }

    public void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
                                    HttpServletResponse response, Object command, BindException errors)
            throws Exception {

        StockParam stockParam = (StockParam) command;
        List<DailyYahooPriceData> data = stockService.getDailyYahooPriceData(stockParam.getSymbol());
        return new ModelAndView("stockData", "stock-data",data);

    }

    @Override
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception {

        StockParam stockParam = new StockParam("AAPL");
        return stockParam;
    }

    @Override
    protected void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception {
        StockParam stockParam = (StockParam) command;
        for (Object obj : errors.getFieldErrors()) {
            FieldError fieldError = (FieldError) obj;
            String error = "Field:" + fieldError.getField() + " " + fieldError.getCode();
            stockParam.addError(error);

        }

    }
}