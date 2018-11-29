/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypt;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author USER
 */
public class no2 {


    private BigInteger p; //prima
    private BigInteger q;
    private BigInteger N; //p.q tidak rahasia
    private BigInteger phi; //(p-1)(q-1)
    private BigInteger e; //public
    private BigInteger d; //private
    private int bitLength = 1024;
    private int blocksize = 254;

    private Random r;
    
   public no2(){
       r = new Random();
       p = BigInteger.probablePrime(bitLength, r);
       q = BigInteger.probablePrime(bitLength, r);
       N = p.multiply(q);

       phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
       e = BigInteger.probablePrime(bitLength/2, r);

       while(phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0){
           e.add(BigInteger.ONE);
       }

       d = e.modInverse(phi);
   }

   public no2(BigInteger e, BigInteger d, BigInteger N){
       this.e = e;
       this.d = d;
       this.N = N;
   }

    public static void main(String[] args) throws IOException {
        no2 rsa = new no2();
        DataInputStream in = new DataInputStream(System.in);
        String testString;
        System.out.print("Masukkan Plain Text\t: ");
        testString = in.readLine();
        System.out.println("String in Bytes\t:"+ bytesToString(testString.getBytes()));

        //enkripsi
        byte[] enkripsi = rsa.encrypt(testString.getBytes());
        System.out.println("Enkripsi String dalam Byte\t: "+bytesToString(enkripsi));

        //deskrips
        byte[] deskripsi = rsa.descrypt(enkripsi);
        System.out.println("Deskripsi String dalam Byte\t: "+bytesToString(deskripsi));

        System.out.println("Deskripsi String\t: "+ new String(deskripsi));
    }


    private static String bytesToString(byte[] encrypted) {
       String tes = "";
       for(byte b: encrypted){
           tes += Byte.toString(b);
       }
       return tes;
    }

    public byte[] encrypt(byte[] message){
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }

    public byte[] descrypt(byte[] message){
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
}