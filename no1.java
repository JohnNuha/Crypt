/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Crypt;

import java.util.Scanner;

public class no1 {
    
    char vignereTable[][] = new char[26][26];

    public void Generate(){
        char[] alphaArray = new char[26];
        char  a = 'A';
        for(int x = 0; x<26; x++){
            alphaArray[x]= a;
            a++;
        }

        int i, j, k;
        i = 0;
        while (i < 26){
            k = i;
            for(j = 0; j<26; j++){
                if(k>=26){
                    k=0;
                }
                vignereTable[i][j] = alphaArray[k++];
            }
            i++;
        }
    }

    private String key;

    public no1(String k){
        key = k;
    }

    public String encrypt(String plaintext){
        char[] plaintextArr = plaintext.toCharArray();
        while (key.length() < plaintext.length()){
            key += key;
        }
        key = key.substring(0, plaintext.length());
        System.out.println(key);
        char[] keyArray = key.toCharArray();
        String chiperText = "";
        for(int i = 0;  i<plaintext.length(); i++ ){
            int rowpos = keyArray[i] - 'A';
            int colpos = plaintextArr[i] - 'A';
            System.out.println(rowpos+"  "+colpos);
            chiperText += vignereTable[rowpos][colpos];
        }
        return chiperText;
    }

    public String decrypt(String chiperText){
        String plainText = "";
        char[] chiperTextArr = chiperText.toCharArray();
        char[] keyArray = key.toCharArray();
        char[] plainTextArr = new char[keyArray.length];
        for(int i = 0; i<keyArray.length; i++){
            int rowpos = keyArray[i] - 'A';
            int chiperpos = new String(vignereTable[rowpos]).indexOf(chiperTextArr[i]);
            plainTextArr[i] = vignereTable[0][chiperpos];
        }
        plainText = new String(plainTextArr);
        return plainText;
    }

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.print("Masukkan Key : ");
        String keyText = console.nextLine();
        no1 sister = new no1(keyText);
        sister.Generate();
        System.out.print("Masukkan PlainText\t: ");
        String plaintext = console.nextLine();
        String chiperText = sister.encrypt(plaintext);
        System.out.println("Hasil Enkripsi\t: "+chiperText);
        plaintext = sister.decrypt(chiperText);
        System.out.println("Hasil Deskripsi\t: "+plaintext);
    }
}
