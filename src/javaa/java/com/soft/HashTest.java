package com.soft;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HashTest {

  public static void main(String[] args) {
    // HmacUtils

    try {
      String secret = "secret";
      String message = "Message";

      Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
      SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
      sha256_HMAC.init(secret_key);

      String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
      System.out.println(hash);
    } catch (Exception e) {
      System.out.println("Error");
    }

  }

}
