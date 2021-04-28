package ru.arip

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.{FindBy, PageFactory}

class LoginPage(driver: WebDriver) {
  // Определение локатора поля ввода логина

  PageFactory.initElements(driver, this)

  @FindBy(xpath = "//*[@id=\"passp-field-login\"]")
  var loginField: WebElement = _
  // Локатор кнопки
  @FindBy(xpath = "//*[contains(text(), 'Войти')]/..")
  var loginBtn: WebElement = _
  // Локатор пароля
  @FindBy(xpath = "//*[@id=\"passp-field-passwd\"]")
  var passwdField: WebElement = _

  def inputLogin(login: String): Unit = {
    loginField.sendKeys(login)
  }

  def inputPasswd(passwd: String): Unit = {
    passwdField.sendKeys(passwd)
  }

  def clickLoginBtn(): Unit = {
    loginBtn.click()
  }

}
