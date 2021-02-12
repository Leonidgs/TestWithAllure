package ru.appline.framework.managers;

import ru.appline.framework.pages.MortgageForFinishedHousing;
import ru.appline.framework.pages.StartPage;

/**
 * @author Arkadiy_Alaverdyan
 * Класс для управления страничками
 */
public class ManagerPages {

    /**
     * Менеджер страничек
     */
    private static ManagerPages managerPages;

    /**
     * Стартовая страничка
     */
    StartPage startPage;

    /**
     * Страничка страхование путественников
     */
    MortgageForFinishedHousing mortgageForFinishedHousing;


    /**
     * Конструктор специально запривейтили (синглтон)
     * @see ManagerPages#getManagerPages()
     */
    private ManagerPages() {
    }

    /**
     * Ленивая инициализация ManagerPages
     *
     * @return ManagerPages
     */
    public static ManagerPages getManagerPages() {
        if (managerPages == null) {
            managerPages = new ManagerPages();
        }
        return managerPages;
    }

    /**
     * Ленивая инициализация {@link ru.appline.framework.pages.StartPage}
     *
     * @return StartPage
     */
    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    /**
     * Ленивая инициализация {@link MortgageForFinishedHousing}
     *
     * @return MortgageForFinishedHousing
     */
    public MortgageForFinishedHousing getStrahovaniePage() {
        if (mortgageForFinishedHousing == null) {
            mortgageForFinishedHousing = new MortgageForFinishedHousing();
        }
        return mortgageForFinishedHousing;
    }

}
