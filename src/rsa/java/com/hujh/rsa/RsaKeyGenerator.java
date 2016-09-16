package com.hujh.rsa;
import java.math.BigInteger;

import java.util.Random;


class RsaKeyGenerator {
   public static void main(String[] a) {
      if (a.length<1) {
         System.out.println("Usage:");
         System.out.println("java RsaKeyGenerator size");
         return;
      }
      int size = Integer.parseInt(a[0]);

      Random rnd = new Random();
      BigInteger p = BigInteger.probablePrime(size/2,rnd);
      BigInteger q = p.nextProbablePrime();
      BigInteger n = p.multiply(q);
      BigInteger m = (p.subtract(BigInteger.ONE)).multiply(
         q.subtract(BigInteger.ONE));
      BigInteger e = getCoprime(m);
      BigInteger d = e.modInverse(m);

      System.out.println("p: "+p);
      System.out.println("q: "+q);
      System.out.println("m: "+m);
      System.out.println("Modulus: "+n);
      System.out.println("Key size: "+n.bitLength());
      System.out.println("Public key: "+e);
      System.out.println("Private key: "+d);
   }
   public static BigInteger getCoprime(BigInteger m) {
      Random rnd = new Random();
      int length = m.bitLength()-1;
      BigInteger e = BigInteger.probablePrime(length,rnd);
      while (! (m.gcd(e)).equals(BigInteger.ONE) ) {
      	 e = BigInteger.probablePrime(length,rnd);
      }
      return e;
   }
}