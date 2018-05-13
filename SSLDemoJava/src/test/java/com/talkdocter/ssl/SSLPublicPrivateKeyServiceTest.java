package com.talkdocter.ssl;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class SSLPublicPrivateKeyServiceTest {

  @Test
  public void testGetBinaryPublicKey(){
    String keyPairType="RSA";
    SSLPublicPrivateKeyService sslPublicPrivateKeyService=new SSLPublicPrivateKeyService(keyPairType);
    String publicKeyPathParentFolder="c:\\ssldemo\\publicprivatekey";
    File file=new File(publicKeyPathParentFolder);
    if(!file.exists()){
      file.mkdirs();
    }
    String publicKeyPath =publicKeyPathParentFolder+ "\\binarypublicKey.pub";
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
  public void testGetBase64PublicKey(){
    String keyPairType="RSA";
    SSLPublicPrivateKeyService sslPublicPrivateKeyService=new SSLPublicPrivateKeyService(keyPairType);
    String publicKeyPathParentFolder="c:\\ssldemo\\publicprivatekey";
    File file=new File(publicKeyPathParentFolder);
    if(!file.exists()){
      file.mkdirs();
    }
    String publicKeyPath =publicKeyPathParentFolder+ "\\base64PublicKey.pub";
    File publicKeyPathFile=new File(publicKeyPath);
    if(publicKeyPathFile.exists()){
      publicKeyPathFile.delete();
    }
    sslPublicPrivateKeyService.getBase64PublicKey(publicKeyPath);
    Assert.assertTrue(new File(publicKeyPath).exists());
  }
  
  @Test
  public void testGetBinaryPrivateKey(){
    String keyPairType="RSA";
    SSLPublicPrivateKeyService sslPublicPrivateKeyService=new SSLPublicPrivateKeyService(keyPairType);
    String privateKeyPathParentFolder="c:\\ssldemo\\publicprivatekey";
    File file=new File(privateKeyPathParentFolder);
    if(!file.exists()){
      file.mkdirs();
    }
    String privateKeyPath =privateKeyPathParentFolder+ "\\privateBinaryKey.key";
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
  }
  
  @Test
  public void testGetBase64PrivateKey(){
    String keyPairType="RSA";
    SSLPublicPrivateKeyService sslPublicPrivateKeyService=new SSLPublicPrivateKeyService(keyPairType);
    String privateKeyPathParentFolder="c:\\ssldemo\\publicprivatekey";
    File file=new File(privateKeyPathParentFolder);
    if(!file.exists()){
      file.mkdirs();
    }
    String privateKeyPath =privateKeyPathParentFolder+ "\\base64PrivateKey.key";
    File privateKeyPathFile=new File(privateKeyPath);
    if(privateKeyPathFile.exists()){
      privateKeyPathFile.delete();
    }
   
    sslPublicPrivateKeyService.getBase64PrivateKey(privateKeyPath);
    Assert.assertTrue(new File(privateKeyPath).exists());
  }
  
  @Test
  public void testSignFile(){
    String keyPairType="RSA";
    SSLPublicPrivateKeyService sslPublicPrivateKeyService=new SSLPublicPrivateKeyService(keyPairType);
    String privateKeyPathParentFolder="c:\\ssldemo\\publicprivatekey";
    File file=new File(privateKeyPathParentFolder);
    if(!file.exists()){
      file.mkdirs();
    }
    String dataFilePath =privateKeyPathParentFolder+ "\\1.txt";
    String signFilePath =privateKeyPathParentFolder+ "\\signed-1.txt";
    File signFilePathFile=new File(signFilePath);
    if(signFilePathFile.exists()){
      signFilePathFile.delete();
    }
    //sslPublicPrivateKeyService.signFile(dataFilePath, signFilePath);
  }
}
