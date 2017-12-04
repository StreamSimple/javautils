package com.streamsimple.javautil.net;

import org.junit.Assert;
import org.junit.Test;

public class SchemeTest
{
  @Test
  public void httpSchemeTest()
  {
    Assert.assertEquals("http", Scheme.HTTP.getText());
  }

  @Test
  public void httpsSchemeTest()
  {
    Assert.assertEquals("https", Scheme.HTTPS.getText());
  }
}
