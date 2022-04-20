package com.company;

import java.math.BigInteger;
public class Main {

    public static void main(String[] args) {
        String message = "Ala";

        BigInteger p = BigInteger.valueOf(3331);
        BigInteger q = BigInteger.valueOf(3769);
        //BigInteger p = BigInteger.valueOf(31);
        //BigInteger q = BigInteger.valueOf(19);

        System.out.println("p: " + p);
        System.out.println("q: " + q);

        BigInteger n = getN(p, q);
        System.out.println("n: " + n);

        BigInteger phi = getPhi(p, q);
        System.out.println("Phi: " + phi);

        BigInteger e = genE(phi);
        System.out.println("e: " + e);

        BigInteger d = getD(e, phi);
        System.out.println("d: " + d);

        BigInteger cipherMessage = stringCipher(message);
        System.out.println(cipherMessage);

        BigInteger encrypted = encrypt(cipherMessage, e, n);
        BigInteger decrypted = decrypt(encrypted, d, n);

        System.out.println("Original message: " + message);
        System.out.println("Ciphered: " + cipherMessage);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        String backMessage = cipherToString(decrypted);
        System.out.println("Odszyfrowana kurwo: " + backMessage);
    }


    public static BigInteger stringCipher(String message) {
        message = message.toUpperCase();
        String cipherString = "";
        int i = 0;
        while (i < message.length()) {
            int ch = (int) message.charAt(i);
            cipherString = cipherString + ch;
            i++;
        }
        BigInteger cipherBig = new BigInteger(String.valueOf(cipherString));
        return cipherBig;
    }


    public static String cipherToString(BigInteger message) {
        String cipherString = message.toString();
        String output = "";
        int i = 0;
        while (i < cipherString.length()) {
            int temp = Integer.parseInt(cipherString.substring(i, i + 2));
            char ch = (char) temp;
            output = output + ch;
            i = i + 2;
        }
        return output;
    }

    public static BigInteger getPhi(BigInteger p, BigInteger q) {
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        return phi;
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if (a.equals(BigInteger.ZERO)) {
            return b;
        } else {
            return gcd(b.mod(a), a);
        }
    }

    public static BigInteger getD(BigInteger e, BigInteger phi){
        BigInteger d = null;
        BigInteger i;
        for (i = BigInteger.valueOf(2); i.compareTo(BigInteger.ZERO) < 10; i = i.add(BigInteger.ONE)) {
            BigInteger x = BigInteger.ONE.add((i.multiply(phi)));
            if(x.mod(e).equals(BigInteger.ZERO)){
                d = x.divide(e);
                break;
            }
        }
        return d;
    }

    public static BigInteger genE(BigInteger phi) {
        BigInteger bi;
        for (bi = BigInteger.valueOf(2); bi.compareTo(BigInteger.ZERO) < phi.intValue(); bi = bi.add(BigInteger.ONE)) {
            if (gcd(bi, phi).equals(BigInteger.ONE)) {
                break;
            }
        }
        return bi;
    }

    public static BigInteger encrypt(BigInteger message, BigInteger e, BigInteger n) {
        return message.modPow(e, n);
    }

    public static BigInteger decrypt(BigInteger message, BigInteger d, BigInteger n) {
        return message.modPow(d, n);
    }

    public static BigInteger getN(BigInteger p, BigInteger q) {
        return p.multiply(q);
    }
}
