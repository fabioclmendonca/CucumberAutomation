package fabioclmendonca.driverManager.drivers;

import fabioclmendonca.driverManager.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class EdgeDriverManager extends DriverManager {

    @Override
    public void createDriver() {
        WebDriverManager.edgedriver().setup();
        driver = new ChromeDriver();
    }
}
