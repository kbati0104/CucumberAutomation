package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import pages.CashWiseLoginPage;
import pages.CashWiseProductAndServicesPage;
import pages.CashWiseUsersMainPage;
import utilities.Config;
import utilities.Driver;
import utilities.Flow;

import java.util.ArrayList;
import java.util.List;

public class CashWiseSalesOptionSteps {
    CashWiseLoginPage cashWiseLoginPage=new CashWiseLoginPage();
    CashWiseUsersMainPage cashWiseUserPage=new CashWiseUsersMainPage();

    CashWiseProductAndServicesPage cashWiseProductAndServicesPage=new CashWiseProductAndServicesPage();

    @Given("User is on the cashwise login page")
    public void user_is_on_the_cashwise_login_page() {
        Driver.getDriver().get(Config.getValue("cashWiseURL"));
    }
    @When("User login with valid {string} and {string}")
    public void user_login_with_valid_and(String string, String string2) {
        cashWiseLoginPage.signInCashWise(Config.getValue("cashWiseLoginEmail"), Config.getValue("cashWiseLoginPassword"));
    }
    @When("User click on the Sales menu bar")
    public void user_click_on_the_sales_menu_bar() {
        cashWiseUserPage.salesButton.click();
        cashWiseUserPage.salesButtonOptions.get(1).click();
    }


    @Then("User should provide on the 3 different pages")
    public void user_should_provide_on_the_different_pages() {
        List<String> expectedMenuOfSalesButton=new ArrayList<>();
        expectedMenuOfSalesButton.add("Clients");
        expectedMenuOfSalesButton.add("Products and services");
        expectedMenuOfSalesButton.add("Invoice");

        List<String> expectedUrls=new ArrayList<>();
        expectedUrls.add("https://cashwise.us/dashboard/sales/clients/active");
        expectedUrls.add("https://cashwise.us/dashboard/sales/products-and-services");
        expectedUrls.add("https://cashwise.us/dashboard/sales/invoice/invoices");

        List<String> actualUrls=new ArrayList<>();
        for(WebElement option:cashWiseUserPage.salesButtonOptions){
            option.click();
            Flow.wait(500);
            String actualLink=Driver.getDriver().getCurrentUrl();
            actualUrls.add(actualLink);
        }

        for(String url: actualUrls){
            Assertions.assertTrue(expectedUrls.contains(url), "Failed. URL of Sidebar menu options didn't match");
        }
    }

    @When("User click on the Products and Services option")
    public void user_click_on_the_products_and_services_option() {
        cashWiseUserPage.salesButton.click();
        cashWiseUserPage.salesButtonOptions.get(1).click();

    }
    @When("User click on the delete button")
    public void user_click_on_the_delete_button() {
        cashWiseProductAndServicesPage.deleteButton.click();
    }
    @Then("User should see message")
    public void user_should_see_message() {
        String expectedMessage="Delete";

        boolean afterDeleteBtn=cashWiseProductAndServicesPage.afterDeleteBtnMessage.getText().contains(expectedMessage);

        Assertions.assertTrue(afterDeleteBtn, "Message after clicking Delete button failed. Expected massage is :Delete face cream. Actual massage is: "
                +cashWiseProductAndServicesPage.afterDeleteBtnMessage.getText());



    }


}


