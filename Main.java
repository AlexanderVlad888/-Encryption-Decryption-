package encryptdecrypt;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        int mode = - 1;
        int key = - 1;
        int data = - 1;
        int in = -1;
        int out = -1;
        int alg = -1;
        for (int i = 0; i < args.length; i++) {
            if ("-mode".equals(args[i])) {
               mode = i;
            } else if ("-key".equals(args[i])) {
               key = i;
            } else if ("-data".equals(args[i])) {
               data = i;
            } else if ("-in".equals(args[i])) {
                in = i;
            } else if ("-out".equals(args[i])) {
                out = i;
            } else if ("-alg".equals(args[i])) {
                alg = i;
            }
        }
        if (out < 0) {
            if (mode < 0) {
                enc(args[data + 1], Integer.parseInt(args[key + 1]), out);
            } else if (key < 0) {
                if (args[mode + 1].equals("enc") && "unicode".equals(args[alg + 1])) {
                    enc(args[data + 1], 0 , out);
                } else if (args[mode + 1].equals("dec") && "unicode".equals(args[alg + 1])) {
                    dec(args[data + 1], 0 );
                } else if (args[mode + 1].equals("enc") && "shift".equals(args[alg + 1])) {
                    //enc shift
                    encShift(args[data + 1], 0 , out);
                } else if (args[mode + 1].equals("dec") && "shift".equals(args[alg + 1])) {
                    //dec shift
                    decShift(args[data + 1], 0 );
                }
            } else if (data < 0) {
                System.out.println("");
            } else {
                if ("enc".equals(args[mode + 1]) && "unicode".equals(args[alg + 1])) {
                    enc(args[data + 1], Integer.parseInt(args[key + 1]), out );
                } else if (args[mode + 1].equals("dec") && "unicode".equals(args[alg + 1])) {
                    dec( args[data + 1], Integer.parseInt(args[key + 1]) );
                } else if (args[mode + 1].equals("enc") && "shift".equals(args[alg + 1])) {
                    //enc shift
                    encShift(args[data + 1], Integer.parseInt(args[key + 1]), out);
                } else if (args[mode + 1].equals("dec") && "shift".equals(args[alg + 1])) {
                    //dec shift
                    decShift( args[data + 1], Integer.parseInt(args[key + 1]) );
                }
            }
        //} else if (data > 0 && in > 0) {

        } else {
            File inFile = new File(args[in + 1]);

            try (Scanner scanner = new Scanner(inFile)){

                String read = scanner.nextLine();

                FileWriter fileWriter = new FileWriter(args[out + 1]);
                if ("enc".equals(args[mode + 1]) && "unicode".equals(args[alg + 1])) {
                    fileWriter.write(String.valueOf(enc(read, Integer.parseInt(args[key + 1]),out)));
                    fileWriter.close();
                } else if (args[mode + 1].equals("dec") && "unicode".equals(args[alg + 1])) {
                    fileWriter.write(String.valueOf(dec(read, Integer.parseInt(args[key + 1]))));
                    fileWriter.close();
                } else if (args[mode + 1].equals("enc") && "shift".equals(args[alg + 1])) {
                    //enc shift
                    fileWriter.write(String.valueOf(encShift(read, Integer.parseInt(args[key + 1]),out)));
                    fileWriter.close();
                } else if (args[mode + 1].equals("dec") && "shift".equals(args[alg + 1])) {
                    //dec shift
                    fileWriter.write(String.valueOf(decShift(read, Integer.parseInt(args[key + 1]))));
                    fileWriter.close();
                }

            } catch (FileNotFoundException e) {
                System.out.println("Error");
                e.printStackTrace();
            }

        }


    }
 /*   static void menu(int out) {
        Scanner scanner = new Scanner(System.in);
        String enc = scanner.nextLine();
        String sentences = scanner.nextLine();
        String strA = scanner.nextLine();
        int a = Integer.parseInt(strA);
        switch (enc) {
            case "enc":
                enc(sentences, a, out);
                break;
            case "dec":
                dec(sentences, a);
                break;
            default:
        }
    } */
    static StringBuilder enc(String sentences, int a, int out) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sentences.length(); i++) {

            stringBuilder.append((char) (a + sentences.charAt(i)));
        }
        if (out < 0) {
            return stringBuilder;
        } else {

        }

        sentences = String.valueOf(stringBuilder);
        //dec(sentences,a);
        return stringBuilder;
    }
    static StringBuilder dec(String sentences, int a) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sentences.length(); i++) {

            if ((sentences.charAt(i) - a) < 97 && sentences.charAt(i) > 96) {
                stringBuilder.append((char) (sentences.charAt(i) - a + 26));
            } else {
                stringBuilder.append((char) (sentences.charAt(i) - a));
            }

        }
        return  stringBuilder;
    }
    static StringBuilder encShift(String sentences, int a, int out) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sentences.length(); i++) {
            if (sentences.charAt(i) == ' ' || sentences.charAt(i) == '!' ||sentences.charAt(i) == ','
                    || sentences.charAt(i) == '.' ) {
                stringBuilder.append(sentences.charAt(i));
            } else if ((a + sentences.charAt(i)) > 122) {
                stringBuilder.append((char) (a - (122 - sentences.charAt(i)) + 96));

            } else {
                stringBuilder.append((char) (a + sentences.charAt(i)));
            }
        }
        if (out < 0) {
            return stringBuilder;
        }
        return stringBuilder;
    }
    static StringBuilder decShift(String sentences, int a) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < sentences.length(); i++) {
            if (sentences.charAt(i) == ' ' || sentences.charAt(i) == '!' ||sentences.charAt(i) == ','
                    || sentences.charAt(i) == '.' ) {
                stringBuilder.append(sentences.charAt(i));
            } else if (( sentences.charAt(i) - a) < 96) {
                stringBuilder.append((char) ((sentences.charAt(i)- a) + 26));

            } else {
                stringBuilder.append((char) (sentences.charAt(i) - a));
            }
        }
        return stringBuilder;
    }
    static void outFile(String encyp) {
       // FileWriter fileWriter = new FileWriter(a);
    }
    static void stage1() {
        //System.out.println("Hello World!");
        //Scanner scanner = new Scanner(System.in);
        //String sentences = scanner.nextLine();
        String sentences = "we found a treasure!";
        String newword = "";
        for (int i = 0; i < sentences.length(); i++) {
            if ((char) (219 - sentences.charAt(i)) == '»') {
                newword = newword.concat(" ");
            } else if ((char) (219 - sentences.charAt(i)) == 'º') {
                newword = newword.concat("!");
            } else {
                newword = newword.concat(String.valueOf((char) (219 - sentences.charAt(i))));
            }


        }
        System.out.println(newword);
    }
}
// we found a treasure!
// dv»ulfmw»z»givzhfivº
