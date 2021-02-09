package fabioclmendonca.driverManager.drivers;

import fabioclmendonca.driverManager.DriverManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirefoxDriverManager extends DriverManager {

    @Override
    public void createDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
    }
}
