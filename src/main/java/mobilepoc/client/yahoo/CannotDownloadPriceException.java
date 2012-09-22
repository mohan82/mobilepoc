/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mobilepoc.client.yahoo;

/**
 *
 * @author mohan
 */
public class CannotDownloadPriceException extends RuntimeException {

    public CannotDownloadPriceException(String symbol, String message) {
        super("Cannot Download symbol :" + symbol + " , " + message);

    }

    public CannotDownloadPriceException(Throwable thrwbl) {
        super(thrwbl);
    }

    public CannotDownloadPriceException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public CannotDownloadPriceException(String string) {
        super(string);
    }

    public CannotDownloadPriceException() {
    }
}
