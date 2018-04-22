package logic;

import presentation.GUI;
import presentation.UI;
import data.DataAccessor;
import data.DataAccessorDatabase;
import logic.FrameType;
import logic.PriceCalculator;
import presentation.TUI;

/**
 *
 * @author RODA
 */
public class Controller{
    public static final boolean DEBUG = true;
    private UI ui = new TUI();
//    private DataAccessor data = new DataAccessorFile();
    private DataAccessor data = new DataAccessorDatabase();
    private PriceCalculator logic = new PriceCalculator();

    public Controller(DataAccessor da, PriceCalculator pc)
    {
        logic = pc;
        data = da;
    }

    public void go() {
        // Get input
        double height = ui.getFrameHeight();
        //System.out.println("Height = " + height);
        double width = ui.getFrameWidth();
        //System.out.println("Width = " + width);
        FrameType type = ui.getFrameType();
        //System.out.println("Type = " + type.toString());
        
        // Calculate price
        double price = logic.calculatePrice(height, width, type, data);
        
        // Display result
        ui.displayPrice(price);
        //System.exit(0);
    }
}
