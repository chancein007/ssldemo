package com.talkdocter.ssl;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpsInvokeService {
  
  private String url;
  
  public HttpsInvokeService(String url){
    this.url=url;
  }
  
  public String invoke(){
    CloseableHttpClient httpClient=HttpClients.createDefault();
    HttpGet httpGet=new HttpGet(url);
    ResponseHandler<String> responseHandler=new ResponseHandler<String>() {

      public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
        int status=httpResponse.getStatusLine().getStatusCode();
        if(status>=200 && status <300){
          HttpEntity httpEntity=httpResponse.getEntity();
          if(null!=httpEntity){
            String result=EntityUtils.toString(httpEntity);
            return result;
          }else{
            return null;
          }
        }else{
          throw new ClientProtocolException("Unexpected Response status:" + status);
        }
        
      }
      
    };
    
    try {
      String responseBody= httpClient.execute(httpGet,responseHandler);
      if(null!=responseBody){
       System.out.println(responseBody);
      }
      
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally{
      if(null!=httpClient){
         try {
          httpClient.close();
         } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }
  
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
 
  
}
