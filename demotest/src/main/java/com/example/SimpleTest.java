package com.example;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jetty.websocket.common.util.TextUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
/**
 * Created by chenyu on 2017/8/22.
 */

public class SimpleTest {
  protected AppiumDriver<AndroidElement> driver;

  @Before
  public void setUp() throws Exception {
    File classpathRoot = new File(System.getProperty("user.dir"));
    File appDir = new File(classpathRoot, "demotest/src/main/java/apps");
    File app = new File(appDir, "jokes.apk");
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("deviceName", "emulator-5554");
    capabilities.setCapability("platformVersion", "6.0");
    capabilities.setCapability("app", app.getAbsolutePath());
    capabilities.setCapability("appPackage", "chenyu.jokes");
    capabilities.setCapability("appActivity", ".feature.main.MainActivity");
    capabilities.setCapability("noReset", true);

    driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }

  protected void clickIfExitId(String id) {
    try {
      driver.findElementById(id).click();
    } catch (NoSuchElementException e){
      System.out.println("Can not find element with id " + id + " . Skip!");
    }
  }

  protected void clickIfExitIds(String[] ids) {
    for (String id:ids) {
      clickIfExitId(id);
    }
  }

  protected void sendWithId(SendAction action) {
    WebElement element = driver.findElementById(action.id);
    if(action.text == null || action.text.length() == 0) {
      element.click();
    } else {
      element.sendKeys(action.text);
    }
  }

  protected void sendWithIds(SendAction[] actions) {
    for (SendAction action: actions) {
      sendWithId(action);
    }
  }

  protected void sendWithIds(String[][] actions) {
    for(String[] action:actions) {
      sendWithId(new SendAction(action[0],action[1]));
    }
  }

  protected void assertTextWithIds(String[][] assertions) {
    for (String[] assertion: assertions) {
      if (assertion[1] == null || assertion[1].length() == 0) {
        Assert.assertNotNull(assertion[0], driver.findElementById(assertion[2]));
      } else {
        Assert.assertEquals(assertion[0], assertion[1], driver.findElementById(assertion[2]).getText());
      }
    }
  }
}
