package fabioclmendonca.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TriviaSearchPage {

    private WebDriver driver;

    public TriviaSearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private String TABLE_LINE_QUESTIONS = "//h2[text()=' Browse Questions ']/following::table/tbody//tr";

    @FindBy(how = How.XPATH, using = "//input[@id='query']")
    private WebElement searchFieldTxt;

    @FindBy(how = How.XPATH, using = "//select[@id='type']")
    private WebElement searchTypeSlc;

    @FindBy(how = How.XPATH, using = "//input[@id='query']/parent::div/button[contains(text(),'Search')]")
    private WebElement searchBtn;

    @FindBy(how = How.XPATH, using = "//div[contains(@class,'alert-danger')]")
    private WebElement errorMessage;

    @FindBy(how = How.XPATH, using = "//ul[contains(@class, 'pagination')]")
    private WebElement paginationSection;

    public WebElement getSearchFieldTxt(){
        return this.searchFieldTxt;
    }

    public WebElement getSearchTypeSlc() {
        return searchTypeSlc;
    }

    public WebElement getSearchBtn() {
        return searchBtn;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public Integer getTableLinesCount(){
        return driver.findElements(By.xpath(TABLE_LINE_QUESTIONS)).size();
    }

    public WebElement getPaginationSection() {
        return paginationSection;
    }
}
