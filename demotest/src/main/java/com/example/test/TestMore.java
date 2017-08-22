package com.example.test;

import com.example.SimpleTest;
import org.junit.Test;

/**
 * Created by chenyu on 2017/8/22.
 */

public class TestMore extends SimpleTest {

  @Test
  public void login() {
    clickIfExitIds(new String[] {"tabMore", "logout"});

    sendWithIds(new String[][] {
        {"login", ""},
        {"email", "zhongchenyu_89@163.com"},
        {"password", "654321"},
        {"android:id/button1", ""}
    });

    assertTextWithIds(new String[][] {
        {"Get name successfully", "钟晨宇", "name"},
        {"Get e-mail successfully", "zhongchenyu_89@163.com", "email"},
        {"Find quit button", "", "logout"}
    });

  }
}
