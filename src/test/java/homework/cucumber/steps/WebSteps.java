package homework.cucumber.steps;

import dataholder.DataHolder;
import homework.cucumber.CucumberTestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.prog.homework.pages.RozetkaPage;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class WebSteps extends CucumberTestRunner {

    public static RozetkaPage rozetkaPage;
    private DatabaseSteps databaseSteps = new DatabaseSteps();

    @Given("load Rozetka page")
    public void loadPage() {
        System.out.println("loading page...");
        rozetkaPage.loadPage();
    }

    @Given("search for {string}")
    public void searchFor(String searchFor) {
        System.out.println("Searching for "+searchFor+"...");
        rozetkaPage.enterSearchValue(searchFor);
    }

    @And("get the {string} and the {string} of the product")
    public void getProductInfo(String titleKey, String priceKey) {
        By GOODS_TITLE = By.cssSelector("span.goods-tile__title");
        By GOODS_PRICE = By.cssSelector("span.goods-tile__price-value");
        new WebDriverWait(driver, Duration.ofSeconds(5L))
                .until(ExpectedConditions.visibilityOfElementLocated(GOODS_TITLE));
        List<WebElement> titleList = driver.findElements(GOODS_TITLE);
        List<WebElement> priceList = driver.findElements(GOODS_PRICE);
        String title = titleList.get(0).getText();
        String price = priceList.get(0).getText().replace("₴", "").replace(" ", "");
        DataHolder.getInstance().put(titleKey, title);
        DataHolder.getInstance().put(priceKey, price);
        System.out.println(DataHolder.getInstance().get(titleKey)+" "+DataHolder.getInstance().get(priceKey));
    }

    @When("search for the same {string} on Rozetka from DB")
    public void searchAgain(String titleKey) {
        System.out.println("Searching again...");
        rozetkaPage.loadPage();
        rozetkaPage.enterSearchValue(DataHolder.getInstance().get(titleKey).toString());
    }

    @Then("compare the {string} of the {string} from database and web page and update the price in case of a failure")
    public void comparePrice(String priceKey, String titleKey) {
        By PRICE_TAG = By.cssSelector("p.product-price__big-color-red");

        System.out.println("Comparing price...");
        new WebDriverWait(driver, Duration.ofSeconds(5L))
                .until(ExpectedConditions.visibilityOfElementLocated(PRICE_TAG));
        String currentPrice = driver.findElement(PRICE_TAG).getText().replace("₴", "").replace(" ", "");
        String dbPrice = DataHolder.getInstance().get(priceKey).toString();
        if (currentPrice.equals(dbPrice)) {
            Assert.assertTrue(true);
        } else {
            databaseSteps.updateData(currentPrice, titleKey);
            Assert.fail("Price does not match");
        }
    }
}
