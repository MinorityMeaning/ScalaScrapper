package ru.arip
package pageClass

import org.openqa.selenium.{WebDriver, WebElement}
import org.openqa.selenium.support.{FindBy, PageFactory}

class WineStylePage(driver: WebDriver) {
  // Определение локатора поля ввода логина

  PageFactory.initElements(driver, this)

  @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div[1]/div[2]/span[2]")
  var nameWine: WebElement = _

  @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div/div[2]/ul/li[2]/div/a[1]")
  var color: WebElement = _

  @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div/div[2]/ul/li[2]/div/a[2]")
  var taste: WebElement = _

  @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div/div[2]/ul/li[3]/div/a[1]")
  var country: WebElement = _

  @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[1]")
  var price: WebElement = _

  def getPrice: String = price.getText.drop(5) // Без пяти первых символов. То есть без "Цена/n"

  def getNamed: String = {
    nameWine.getText + " " + color.getText + " " + taste.getText + " " + country.getText
  }

}
