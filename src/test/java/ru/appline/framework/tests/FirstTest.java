package ru.appline.framework.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import ru.appline.framework.basetestsclass.BaseTests;

public class FirstTest extends BaseTests {

    @Test()
    @DisplayName("Оформление ипотеки для готового жилья")
    @Description("Демонстрационный e2e сценарий")
    public void startTest() {
        app.getStartPage()
                .selectBaseMenu("Ипотека")
                .selectSubMenu("Ипотека на готовое жильё")
                .setPropertyValue("5180000")
                .putFristMany("999999")
                .setCreditTerm("30")
                .checkFieldOnOrOffByName("Страхование жизни и здоровья")
                .checkFieldOnOrOffByName("Молодая семья")
                .checkField("32 735 ₽")
                .checkFieldSumCredit("4 180 001 ₽")
                .chekRequiredIncome("42 140 ₽")
                .chekCreditRate("1,7%");

    }

}
