package com.talkdocter.ssl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import org.apache.commons.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Test;

public class JKSServiceTest {

  @Test
  public void testExportKeyFromKeyStore(){
    String jksFilePath="C:\\ssldemo\\tomcat\\mykeystore.jks";
    String keyStorePassword="password";
    String keyAlias="tomcat";
    String keyExportFilePath="C:\\ssldemo\\tomcat\\tomcatkey.jks";
    File keystoreExportFile=new File(keyExportFilePath);
    if(keystoreExportFile.exists()){
      keystoreExportFile.delete();
    }
    JKSService jksService =new JKSService(jksFilePath, keyStorePassword, keyAlias, keyExportFilePath);
    try {
      jksService.exportKey();
    } catch (Exception e) {
      e.printStackTrace();
    }
    Assert.assertTrue(new File(keyExportFilePath).exists());
    Assert.assertTrue(new File(keyExportFilePath).getTotalSpace()>0);
  }
  
  @Test
  public void decodeBase64(){
    String encodeStr="c29tZSBzdHJpbmc=";
   
    StringBuilder stringBuilder=new StringBuilder();
    stringBuilder.append(Base64.decodeBase64(encodeStr));
    
    System.out.println(stringBuilder.toString());
    String asB64;
    try {
      asB64 =Base64.encodeBase64String("some string".getBytes()) ;   
      System.out.println(asB64); 

      byte[] asBytes = Base64.decodeBase64(encodeStr);
      System.out.println(new String(asBytes, "utf-8")); // ���Ϊ: some string
      
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
   
  }
}
