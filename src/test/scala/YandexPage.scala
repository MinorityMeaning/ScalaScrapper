package ru.arip

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.{FindBy, PageFactory}
import java.lang.Thread.sleep

class YandexPage(driver: WebDriver) {
  // Определение локатора поля ввода логина

  PageFactory.initElements(driver, this)

  //Локатор поля поиска
  @FindBy(xpath = "//*[@id=\"text\"]")
  var searchField: WebElement = _
  // Локатор кнопки поиска
  @FindBy(xpath = "//*[contains(@class, 'mini-suggest__button')]/..")
  var searchBtn: WebElement = _
  //Локатор найденной ссылки
  @FindBy(xpath = "//*[contains(text(), 'WineStyle.ru')]/..")
  var urlSite: WebElement = _

  def inputWine(wine: String): Unit = {
    searchField.sendKeys(wine)
  }

  def clickSearchBtn(): Unit = {
    searchBtn.click()
    //sleep(3000)
  }

}

