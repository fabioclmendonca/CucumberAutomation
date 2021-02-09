package fabioclmendonca.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TriviaApiPage {

    private WebDriver driver;

    public TriviaApiPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(how = How.XPATH, using = "//input[@id='trivia_amount']")
    private WebElement numberOfQuestionTxt;

    @FindBy(how = How.XPATH, using = "//select[@name='trivia_category']")
    private WebElement categorySlc;

    @FindBy(how = How.XPATH, using = "//select[@name='trivia_difficulty']")
    private WebElement dificultySlc;

    @FindBy(how = How.XPATH, using = "//select[@name='trivia_type']")
    private WebElement typeSlc;

    @FindBy(how = How.XPATH, using = "//button[@type='submit'][text()='Generate API URL']")
    private WebElement generateApiBtn;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'alert-success')]//input")
    private WebElement generatedUrl;

    public WebElement getNumberOfQuestionTxt() {
        return numberOfQuestionTxt;
    }

    public WebElement getCategorySlc() {
        return categorySlc;
    }

    public WebElement getDificultySlc() {
        return dificultySlc;
    }

    public WebElement getTypeSlc() {
        return typeSlc;
    }

    public WebElement getGenerateApiBtn() {
        return generateApiBtn;
    }

    public WebElement getGeneratedUrl() {
        return generatedUrl;
    }
}
