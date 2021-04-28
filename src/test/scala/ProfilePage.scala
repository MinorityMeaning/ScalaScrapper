package ru.arip

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait


class ProfilePage(driver: WebDriver) {
  //инициализация полей класса, и чтобы работал FindBy
  PageFactory.initElements(driver, this)

  //Локатор меню пользователя. Правый верхний угол.
  @FindBy(xpath = "//*[contains(@class, 'user-account__name')]")
  var userMenu: WebElement = _
  //Локатор кнопки выхода
  @FindBy(xpath = "//*[contains(@class, 'legouser__menu-item_action_exit')]")
  var logoutBtn: WebElement = _

  //Метод получения имени пользователя из меню
  def getUserName: String = {
    // Надо добавить ожидание, иначе страница не успевает прогрузиться.
    val wait = new WebDriverWait(driver, 10)
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class, 'user-account__name')]")))
    userMenu.getText
  }

  //Нажатие на логин пользователя. Чтобы раскрылась форма с кнопкой выхода.
  def entryMenu(): Unit = userMenu.click()

  //Метод выхода
  def userLogout(): Unit = logoutBtn.click()
}

