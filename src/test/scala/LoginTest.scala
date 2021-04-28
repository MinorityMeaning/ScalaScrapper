package ru.arip

import org.junit.{After, Assert, Before, Test}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.scalatestplus.junit.AssertionsForJUnit
import org.scalatest._

import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit

class LoginTest extends AssertionsForJUnit {

  var property: ConfProperties = _
  var driver: WebDriver = _
  var loginPage: LoginPage = _
  var profilePage: ProfilePage = _
  var searchPage: YandexPage = _

   @Before def setup(): Unit = {
    property = new ConfProperties()
    System.setProperty("webdriver.chrome.driver", property.getProperty("chromedriver")) // Путь к драйверу
    //Экземпляр драйвера
    driver = new ChromeDriver()
    //Экземпляры страниц
    //loginPage = new LoginPage(driver)
    //profilePage = new ProfilePage(driver)
    searchPage = new YandexPage(driver)

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
    searchPage.inputWine(property.listWines()(0))
    searchPage.clickSearchBtn()
    searchPage.urlSite.click()
    sleep(10000)
    driver.close()
    sleep(10000)
  }

  @After def afterAll(): Unit = {
    driver.quit()
  }

}
