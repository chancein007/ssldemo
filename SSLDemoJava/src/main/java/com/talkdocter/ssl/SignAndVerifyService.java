package com.talkdocter.ssl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


public class SignAndVerifyService {

  public static void  sign(String privateKeyFile,  String dataFile,String signFile){
    /* Read the private key bytes */
    Path path = Paths.get(privateKeyFile);
    byte[] bytes;
    try {
      bytes = Files.readAllBytes(path);
      PKCS8EncodedKeySpec ks = new PKCS8EncodedKeySpec(bytes);
      KeyFactory kf = KeyFactory.getInstance("RSA");
      PrivateKey pvt = kf.generatePrivate(ks);
      Signature sign = Signature.getInstance("SHA256withRSA");
      sign.initSign(pvt);
      InputStream in = null;
      try {
          in = new FileInputStream(dataFile);
          byte[] buf = new byte[2048];
          int len;
          while ((len = in.read(buf)) != -1) {
          sign.update(buf, 0, len);
          }
      } catch (SignatureException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } finally {
          if ( in != null ) in.close();
      }
      
      
      OutputStream out = null;
      try {
          out = new FileOutputStream(signFile);
          byte[] signature = sign.sign();
          out.write(signature);
      } catch (SignatureException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } finally {
          if ( out != null ) out.close();
      }

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InvalidKeySpecException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  
   }
  
  public static boolean  verify(String publicKeyFile,  String dataFile,String signFile){
  
    /* Read the public key bytes */
    Path path = Paths.get(publicKeyFile);
    Signature sign=null;
    byte[] bytes;
    boolean isVerify=false;
    try {
      bytes = Files.readAllBytes(path);
      X509EncodedKeySpec ks = new X509EncodedKeySpec(bytes);
      KeyFactory kf = KeyFactory.getInstance("RSA");
      PublicKey pub = kf.generatePublic(ks);
      sign = Signature.getInstance("SHA256withRSA");
      sign.initVerify(pub);

      InputStream in = null;
      try {
          in = new FileInputStream(dataFile);
          byte[] buf = new byte[2048];
          int len;
          while ((len = in.read(buf)) != -1) {
          sign.update(buf, 0, len);
          }
      } catch (SignatureException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } finally {
          if ( in != null ) in.close();
      }

      /* Read the signature bytes */
      path = Paths.get(signFile);
      bytes = Files.readAllBytes(path);
      isVerify= sign.verify(bytes);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InvalidKeySpecException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SignatureException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }  
    return isVerify;
   
  }
   
  
  }
 

