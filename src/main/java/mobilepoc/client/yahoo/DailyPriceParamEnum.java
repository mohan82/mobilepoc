/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mobilepoc.client.yahoo;

/**
 *
 * @author mohan
 */
public enum DailyPriceParamEnum {

    DLY_SYMBOL("s"),
    DLY_FIELD("f");
    private final String parameter;

    private DailyPriceParamEnum(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
