package com.talkdocter.ssl;

import org.junit.Test;

public class InvokeWechatAPITest {

  @Test
  public void testWeChatAPI(){
    String url="https://pleiades.stoa.org/";
    HttpsInvokeService httpsInvokeService=new HttpsInvokeService(url);
    httpsInvokeService.invoke();
  }
}

