package ru.arip

import org.junit.{After, Assert, Before, Test}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatestplus.junit.AssertionsForJUnit
import org.scalatest._
import org.scalatestplus.selenium._

import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

class LoginTest extends AssertionsForJUnit {

  var property: ConfProperties = _
  var driver: WebDriver = _
  var searchPage: YandexPage = _
  var wineStylePage: WineStylePage = _
  var yandexHandle: String = _

   @Before def setup(): Unit = {
    property = new ConfProperties()
    System.setProperty("webdriver.chrome.driver", property.getProperty("chromedriver")) // Путь к драйверу
    //Экземпляр драйвера
    driver = new ChromeDriver()
    //Экземпляры страниц
    searchPage = new YandexPage(driver)
    wineStylePage = new WineStylePage(driver)

    driver.manage.window.maximize()
    // Задержка на выполнение теста = 10 сек.
    driver.manage.timeouts.implicitlyWait(10, TimeUnit.SECONDS)
    // Получение ссылки на страницу входа из файла настроек
    driver.get(property.getProperty("loginpage"))
  }

/*
    @Test def test() {
      // Логин и пароль берутся из файла настроек по аналогии с chromedriver
      loginPage.inputLogin(property.getProperty("login"))
      // нажимаем на вход
      loginPage.clickLoginBtn()
      // вводим пароль
      loginPage.inputPasswd(property.getProperty("password"))
      loginPage.clickLoginBtn()
      // Получаем отображаемый логин и сравниваем его с логином из файла настроек
      val user: String = profilePage.getUserName
      if (property.getProperty("login") == user) println("Удачная авторизация")
      Assert.assertEquals(property.getProperty("login"), user)
    }

 */

  @Test def test2(): Unit ={
    for (wine: String <- property.listWines) {
      searchPage.inputWine(wine + " site:winestyle.ru")
      searchPage.clickSearchBtn()
      yandexHandle = driver.getWindowHandle // Получаем дескриптор яндекса
      searchPage.urlSite.click()

      driver.switchTo().window(getHandle) // Переключаем фокус на страницу с вином
      //Печатаем цену
      println(wineStylePage.getPrice + " " + wineStylePage.getNamed)
      driver.close()
      driver.switchTo().window(yandexHandle) // Переключаем фокус на страницу поиска
      driver.navigate().back()
    }
  }

  @After def afterAll(): Unit = {
    driver.quit()
  }

  // Вернёт дескриптор открытого окна с вином.
  def getHandle: String= {
    val windowSet = driver.getWindowHandles // Все тайтлы вкладок
    windowSet.remove(yandexHandle)
    windowSet.iterator().next() // Вернёт оставшуюся строку вкладки
  }

}
