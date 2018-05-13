package com.talkdocter.ssl;

import org.junit.Test;

public class HttpsInvokeServiceTest {

  @Test
  public void invokeBaiduHttpsURL(){
    //String url="https://10.34.0.96/";
    String url="https://www.baidu.com/"; 
    HttpsInvokeService httpsInvokeService=new HttpsInvokeService(url);
    httpsInvokeService.invoke();
  }
  
  @Test
  public void invokePrivateCAHttpsURL(){
    String url="https://docs.microsoft.com/en-us/sharepoint/dev/general-development/choose-the-right-api-set-in-sharepoint";
    HttpsInvokeService httpsInvokeService=new HttpsInvokeService(url);
    httpsInvokeService.invoke();
  }
  
  
}

