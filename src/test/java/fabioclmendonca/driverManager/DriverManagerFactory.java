package fabioclmendonca.driverManager;


import fabioclmendonca.driverManager.drivers.ChromeDriverManager;
import fabioclmendonca.driverManager.drivers.EdgeDriverManager;
import fabioclmendonca.driverManager.drivers.FirefoxDriverManager;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type) {
        DriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
                driverManager = new FirefoxDriverManager();
                break;
            default:
                driverManager = new EdgeDriverManager();
                break;
        }
        return driverManager;
    }
}
