import data.DataAccessorHardCodedValues;
import logic.Controller;
import logic.PriceCalculator;

/**
 *
 * @author RODA
 */
public class Main {
    
    public static void main(String[] args) {
        new Controller(new DataAccessorHardCodedValues(),new PriceCalculator()).go();
    }
}
