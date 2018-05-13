package com.talkdocter.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.Key;
import java.security.KeyStore;
import sun.misc.BASE64Encoder;

class StringUtils{
  public static boolean isEmpty(CharSequence cs)
  {
    return (cs == null) || (cs.length() == 0);
  }
 }

public class JKSService {
  
  
  private String jksFilePath;
  private String keyStorePassword;
  private String keyAlias;
  private String keyExportFilePath;
  
  public JKSService(String  jksFilePath, String keyStorePassword,String keyAlias, String keyExportFilePath){
    this.jksFilePath=jksFilePath;
    this.keyStorePassword=keyStorePassword;
    this.keyAlias=keyAlias;
    this.keyExportFilePath=keyExportFilePath;
  }
  
 
     
  public void exportKey() throws Exception{
    
    KeyStore keyStore= KeyStore.getInstance("JCEKS");
    BASE64Encoder encoder=new BASE64Encoder();
    
    File jksFile= new File(jksFilePath);
    if(!jksFile.exists() || jksFile.isDirectory()){
      throw new IllegalArgumentException("The Keystore file can't be empty!");
    }
    
    if(StringUtils.isEmpty(keyStorePassword)){
      throw new IllegalArgumentException("The pasword can't be empty!");
    }
    
    keyStore.load(new FileInputStream(new File(jksFilePath)), keyStorePassword.toCharArray());
    Key key=keyStore.getKey(keyAlias, keyStorePassword.toCharArray());
  
    
    String encodedKey=encoder.encode(key.getEncoded());
    
    File keyExportFile= new File(keyExportFilePath);
    
    FileWriter fileWriter=new FileWriter(keyExportFile);
    fileWriter.write("---BEGIN PRIVATE KEY ---\n");
    fileWriter.write(encodedKey);
    fileWriter.write("---END PRIVATE KEY ---\n");
    fileWriter.close();
     
  }

  public String getJksFilePath() {
    return jksFilePath;
  }

  public void setJksFilePath(String jksFilePath) {
    this.jksFilePath = jksFilePath;
  }

  public String getKeyStorePassword() {
    return keyStorePassword;
  }

  public void setKeyStorePassword(String keyStorePassword) {
    this.keyStorePassword = keyStorePassword;
  }

  public String getKeyAlias() {
    return keyAlias;
  }

  public void setKeyAlias(String keyAlias) {
    this.keyAlias = keyAlias;
  }



  public String getKeyExportFilePath() {
    return keyExportFilePath;
  }



  public void setKeyExportFilePath(String keyExportFilePath) {
    this.keyExportFilePath = keyExportFilePath;
  }

  
}
