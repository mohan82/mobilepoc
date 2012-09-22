package mobilepoc.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mohan
 * Date: 22/09/12
 * Time: 8:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class StockParam {
    private String symbol;
    private List<String> errorList = new ArrayList<String>();


    public StockParam(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<String> getErrorList() {
        return errorList;
    }
    public  void  addError(String error){
        errorList.add(error);
    }
    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
