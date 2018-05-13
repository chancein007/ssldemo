package com.talkdocter.ssl;

import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SignAndVerifyServiceTest {

  String privateKeyPathParentFolder="c:\\ssldemo\\publicprivatekey";
  String privateKeyPath =privateKeyPathParentFolder+ "\\privateBinaryKey.key";
  String publicKeyPath =privateKeyPathParentFolder+ "\\publicBinaryKey.key";
  String dataFilePath=privateKeyPathParentFolder +"\\1.txt";
  String signFilePath=privateKeyPathParentFolder +"\\signed.txt";
  
  @Before
  public void SSLServerSetupHandler(){
    String keyPairType="RSA";
    SSLPublicPrivateKeyService sslPublicPrivateKeyService=new SSLPublicPrivateKeyService(keyPairType);
    File file=new File(privateKeyPathParentFolder);
    if(!file.exists()){
      file.mkdirs();
    }
    
    //1. Generated Private Key
    File privateKeyPathFile=new File(privateKeyPath);
    if(privateKeyPathFile.exists()){
      privateKeyPathFile.delete();
    }
    try {
      sslPublicPrivateKeyService.getBinaryPrivateKey(privateKeyPath);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    Assert.assertTrue(new File(privateKeyPath).exists());
    
    //2. Generated public Key
    File publicKeyPathFile=new File(publicKeyPath);
    if(publicKeyPathFile.exists()){
      publicKeyPathFile.delete();
    }
    try {
      sslPublicPrivateKeyService.getBinaryPublicKey(publicKeyPath);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    
    Assert.assertTrue(new File(publicKeyPath).exists());
    
  }
  
  
  @Test
  public void testSignAndVerify(){
 
    SignAndVerifyService signAndVerifyService=new SignAndVerifyService();
    File signFile=new File(signFilePath);
    if(signFile.exists()){
      signFile.delete();
    }
    
    signAndVerifyService.sign(privateKeyPath, dataFilePath, signFilePath);
    boolean isVerified=signAndVerifyService.verify(publicKeyPath, dataFilePath, signFilePath);
    Assert.assertTrue(isVerified);
    
  }
}
