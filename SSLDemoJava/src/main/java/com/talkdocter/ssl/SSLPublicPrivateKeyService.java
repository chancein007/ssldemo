package com.talkdocter.ssl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

public class SSLPublicPrivateKeyService {
  
  private  Key publicKey;
  private  Key privateKey;
  
  public SSLPublicPrivateKeyService(String keyPairType) {
    try {
      KeyPairGenerator kpg = KeyPairGenerator.getInstance(keyPairType);
      kpg.initialize(2048);
      KeyPair kp = kpg.generateKeyPair();
      publicKey= kp.getPublic();
      privateKey= kp.getPrivate();

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  public void getBinaryPublicKey(String publicKeyPath) throws FileNotFoundException {

    FileOutputStream out = new FileOutputStream(publicKeyPath);
    try {
      out.write(publicKey.getEncoded());
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    // prints "Public key format: X.509" on my machine
   System.err.println("Public key format: " + publicKey.getFormat());


  }
  public void getBase64PublicKey(String publicKeyPath){
    Base64.Encoder encoder = Base64.getEncoder();
   
    Writer out;
    try {
      out = new FileWriter(publicKeyPath);
      out.write("-----BEGIN RSA PUBLIC KEY-----\n");
      out.write(encoder.encodeToString(privateKey.getEncoded()));
      out.write("\n-----END RSA PUBLIC KEY-----\n");
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
  }
  
  public void getBinaryPrivateKey(String privateKeyPath) throws FileNotFoundException {

    FileOutputStream out = new FileOutputStream(privateKeyPath);
    try {
      out.write(privateKey.getEncoded());
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
     // prints "Private key format: PKCS#8" on my machine
    System.err.println("Private key format: " + privateKey.getFormat());
  
  }
 
  
  
  public void getBase64PrivateKey(String privateKeyPath){
    Base64.Encoder encoder = Base64.getEncoder();
   
    Writer out;
    try {
      out = new FileWriter(privateKeyPath);
      out.write("-----BEGIN RSA PRIVATE KEY-----\n");
      out.write(encoder.encodeToString(privateKey.getEncoded()));
      out.write("\n-----END RSA PRIVATE KEY-----\n");
      out.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }
  /*
  public void signFile(String dataFilePath,String signFilePath){
    Signature sign=null;
    try {
      sign = Signature.getInstance("SHA256withRSA");
      sign.initSign((PrivateKey) privateKey);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    }
   
    InputStream in = null;
    try {
        in = new FileInputStream(dataFilePath);
        byte[] buf = new byte[2048];
        int len;
        while ((len = in.read(buf)) != -1) {
          sign.update(buf, 0, len);
        }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (SignatureException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
        if ( in != null ) 
         try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
     
    OutputStream out = null;
    try {
        out = new FileOutputStream(signFilePath);
        byte[] signature = sign.sign();
        out.write(signature);
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SignatureException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
        if ( out != null ){
          try {
            out.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
    }
  }
  
  */
}
