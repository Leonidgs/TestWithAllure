package ru.appline.framework.pages;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class MortgageForFinishedHousing extends BasePage {

    @FindBy(xpath = "//div[@data-right-hint='30 млн. ₽']//input[@step='10000']")
    WebElement propertyValue;
    @FindBy(xpath = "//iframe[@title='Основной контент']")
    WebElement swtichIntoIfarame;
    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/initial-fee-input']//input[@step='10000']")
    WebElement firstMany;
    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/credit-term-input']//input[contains(@class, 'dc-input__input')]")
    WebElement creditTerm;
    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/discount-item-2-switcher']//input[@type='checkbox']")
    WebElement insuranceButton;
    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/discount-item-6-switcher']//input[@type='checkbox']")
    WebElement familyButton;
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/mobile-result-monthly-payment']//span")
    WebElement checkMonthlyPayment;
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-credit-sum']//span")
    WebElement creditSum;
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-required-income']//span")
    WebElement requiredIncome;
    @FindBy(xpath = "//span[@data-e2e-id='mland-calculator/medium-result-credit-rate']//span")
    WebElement creditRate;
    @FindBy(xpath = "//*[@class='_1CyiyN1qJdh_dWG5pvvrcw']/following::span[@class='_1ZfZYgvLm4KBWPL41DOSO']")
    List<WebElement> checkAdditionalButtons;


    //Стоимость недвижимости
    @Step("Ввести стоимость недвижимости 'Значение = ' {value}")
    public MortgageForFinishedHousing setPropertyValue(String value) {
        js.executeScript("scroll(0,1700)");
        getDriver().switchTo().frame(swtichIntoIfarame);
        propertyValue.click();
        propertyValue.sendKeys("\b\b\b\b\b\b\b\b\b");
        propertyValue.sendKeys(value);

        return this;
    }

    //Первоначальный взнос
    @Step("Ввести первоначальный взнос 'Значение = ' {value}")
    public MortgageForFinishedHousing putFristMany(String value) {
        //((JavascriptExecutor) getDriver()).executeScript("scroll(0,1700)");
        firstMany.click();
        firstMany.sendKeys("\b\b\b\b\b\b\b\b\b");
        firstMany.sendKeys(value);
        return this;
    }

    //Срок кредита 30 годов
    @Step("Утсановка срока кредита 'Значение = ' {value}")
    public MortgageForFinishedHousing setCreditTerm(String value) {
        creditTerm.click();
        creditTerm.sendKeys("\b\b\b");
        creditTerm.sendKeys(value);
        return this;
    }

    @Step("Проверка дополнительных опций {fieldName}")
    public MortgageForFinishedHousing checkFieldOnOrOffByName(String fieldName) {
        checkAdditionalButtons.stream()
                .forEach(elem -> {
                    if (elem.getText().equalsIgnoreCase(fieldName)) {
                        if (fieldName.equalsIgnoreCase("Страхование жизни и здоровья")) {
                            offInssurens();
                        }else if (fieldName.equalsIgnoreCase("Молодая семья")) {
                            checkNewFamilyButton();
                        }
                    }
                });
        return this;
    }

    //Убираем иншуренс
    @Step("Убираем страховку {setFirstMoney}")
    public MortgageForFinishedHousing offInssurens() {
        insuranceButton.click();
        return this;
    }

    //Ставим молодую семью
    @Step("Молодая семья {setFirstMoney}")
    public MortgageForFinishedHousing checkNewFamilyButton() {
        if (familyButton.getAttribute("ariaChecked").equalsIgnoreCase("true")) {
            return this;
        } else {
            familyButton.click();
            return this;
        }
    }

    //Проверяю ежемесячный платёж
    @Step("Проверка суммы ежемесячного платежа  'Значение = ' {value}")
    public MortgageForFinishedHousing checkField(String value) {
        getDriver().switchTo().defaultContent();
        js.executeScript("scroll(0,1600)");
        getDriver().switchTo().frame(swtichIntoIfarame);
        attributeToBe(checkMonthlyPayment, "innerText", value);
        //System.out.println("per/month " + checkMonthlyPayment.getAttribute("innerText"));
        if (checkMonthlyPayment.getAttribute("innerText").equalsIgnoreCase(value)) {
            return this;
        }
        Assert.fail("Сумма ежемесячного платежа " + value + " не соответствует ожидаемой!");
        return this;
    }

    //Проверяю сумму кредита
    @Step("Проверка суммы кредита 'Значение = ' {value}")
    public MortgageForFinishedHousing checkFieldSumCredit(String value) {

        //System.out.println("per/month " + creditSum.getAttribute("innerText"));
        if (creditSum.getAttribute("innerText").equalsIgnoreCase(value)) {
            return this;
        }
        Assert.fail("Сумма кредита " + value + " не соответствует ожидаемой!");
        return this;
    }

    //Необходимый регулярный доход
    @Step("Проверка необходимого дохода 'Значение = ' {value}")
    public MortgageForFinishedHousing chekRequiredIncome(String value) {
        //System.out.println("per/month " + requiredIncome.getAttribute("innerText"));
        if (requiredIncome.getAttribute("innerText").equalsIgnoreCase(value)) {
            return this;
        }
        Assert.fail("Сумма дохода" + value + "' не соответствует ожидаемой!");
        return this;
    }

    //Чекаем процентную ставку и фэйлим тест
    @Step("Проверка процентной ставки 'Значение = ' {rate}")
    public MortgageForFinishedHousing chekCreditRate(String rate) {

        //System.out.println("per/month " + creditRate.getAttribute("innerText"));
        if (creditRate.getAttribute("innerText").equalsIgnoreCase(rate)) {
            return this;
        }
        Assert.fail("Процентная ставка" + rate + " не соответствует ожидаемой!");
        return this;
    }
}
