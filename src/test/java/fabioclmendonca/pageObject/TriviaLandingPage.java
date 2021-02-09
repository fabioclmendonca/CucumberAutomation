package fabioclmendonca.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TriviaLandingPage {

    private WebDriver driver;

    public TriviaLandingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//div[@id='navbar']//a[@href='https://opentdb.com/browse.php']")
    private WebElement browseBtn;

    @FindBy(how = How.XPATH, using = "//div[@id='navbar']//a[@href='https://opentdb.com/api_config.php']")
    private WebElement apiBtn;

    public WebElement getBrowseBtn(){
        return this.browseBtn;
    }

    public WebElement getApiBtn() {
        return apiBtn;
    }
}
