package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;
import utilities.Flow;

public class CashWiseLoginPage {

    public CashWiseLoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//button[@type='button'])[1]")
    public WebElement signInButton;

    @FindBy(xpath = "(//input[@name='email'])[2]")
    public WebElement emailInputBox;

    @FindBy(name="password")
    public WebElement passwordInputBox;

    @FindBy(xpath = "(//button[@type='submit'])[2]")
    public WebElement signInInputButton;

    public void signInCashWise(String email, String password){
        signInButton.click();
        Flow.wait(300);
        emailInputBox.sendKeys(email);
        passwordInputBox.sendKeys(password);
        signInInputButton.click();

    }


}