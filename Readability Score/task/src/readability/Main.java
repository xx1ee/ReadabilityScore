package readability;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static double age(double score) {
        if (score <= 1.0) {
            System.out.println("(about 6-year-olds)");
            return 6;
        } else if (score <= 2.0) {
            System.out.println("(about 7-year-olds)");
            return 7;
        } else if (score <= 3.0) {
            System.out.println("(about 8-year-olds)");
            return 8;
        } else if (score <= 4.0) {
            System.out.println("(about 9-year-olds)");
            return 9;
        } else if (score <= 5.0) {
            System.out.println("(about 10-year-olds)");
            return 10;
        } else if (score <= 6.0) {
            System.out.println("(about 11-year-olds)");
            return 11;
        } else if (score <= 7.0) {
            System.out.println("(about 12-year-olds)");
            return 12;
        } else if (score <= 8.0) {
            System.out.println("(about 13-year-olds)");
            return 13;
        } else if (score <= 9.0) {
            System.out.println("(about 14-year-olds)");
            return 14;
        } else if (score <= 10.0) {
            System.out.println("(about 15-year-olds)");
            return 15;
        } else if (score <= 11.0) {
            System.out.println("(about 16-year-olds)");
            return 16;
        } else if (score <= 12.0) {
            System.out.println("(about 17-year-olds)");
            return 17;
        } else if (score <= 13.0) {
            System.out.println("(about 18-year-olds)");
            return 18;
        } else if (score <=14.0 || score >= 14.0) {
            System.out.println("(about 22-year-olds)");
            return 22;
        }
        return 0;
    }
    public static double ColemanLiau(double dots, double wordss, double len) {
        double L = len / (wordss + 1) * 100;
        double S = dots / (wordss + 1) * 100;
        double score = 0.0588 * L - 0.296 * S - 15.8;
        String result = String.format("%.2f", score);
        System.out.print("Coleman–Liau index: ");
        System.out.print(result);
        return age(score);
    }
    public static double AutomatedIndex(double dots, double wordss, double len) {
        double score =  4.71 * len / (wordss + 1) + 0.5 * (wordss + 1) / dots - 21.43;
        String result = String.format("%.2f", score);
        System.out.print("Automated Readability Index: " + result);
        return age(score);
    }
    public static double FleschKincaid(int sill, double dots, double wordss) {
        double score = 0.39 * (wordss + 1) / dots + 11.8 * (double) sill / (wordss + 1) - 15.59;
        System.out.print("Flesch–Kincaid readability tests: ");
        String result = String.format("%.2f", score);
        System.out.print(result);
        return age(score);
    }
    public static double SMOG(double dots, int hard) {
        double score = 1.043 * Math.sqrt(hard * 30 / dots) + 3.1291;
        System.out.print("Simple Measure of Gobbledygook: ");
        String result = String.format("%.2f", score);
        System.out.print(result);
        return age(score);
    }
    public static int sill(String msg) {
        int sill = 0;
        //char[] vowels = {'a', 'e', 'i', 'o', 'u'};
        String reg = "[aeiou]";
        msg = msg.toLowerCase();
        msg = msg.replaceAll("[aeiou]{2,}", "a");
        msg = msg.replaceAll("[,\\.?!]", "");
        int lenW = 0;
        int vowel = 0;
        String[] words = msg.split("\\s");
        for (int i = 0; i < words.length; i++) {
            lenW = 0;
            //System.out.println(words[i]);
            while (words[i].length() > lenW) {
                //System.out.println(Character.toString(words[i].charAt(lenW)));
                //System.out.println(lenW);
                if (Character.toString(words[i].charAt(lenW)).matches(reg) && lenW != words[i].length() - 1) {
                    vowel++;
                }
                lenW++;
            }
            if (vowel == 0) {
                sill++;
            } else {
                sill+=vowel;
            }
            vowel = 0;
        }
        return sill;
    }

    public static void main(String[] args) {
        String pathToFileIn = "";
        String msg = "";
        pathToFileIn = args[0];
        File fileIn = new File(pathToFileIn);
        try (Scanner scanner = new Scanner(fileIn)) {
            while (scanner.hasNext()) {
                msg = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error, no file found: " + pathToFileIn);
        }
        String regex = "\\s";
        String regex2 = "\\S";
        String regex1 = "[!?.]";
        StringBuilder text = new StringBuilder(msg);
        int letters = msg.length();
        int k = 0;
        double wordss = 0;
        double dots = 0;
        double len = 0;
        while (k < text.length()) {
            if (String.valueOf(text.charAt(k)).matches(regex)) {
                wordss++;
            }
            if (String.valueOf(text.charAt(k)).matches(regex2)) {
                len++;
            }
            if (String.valueOf(text.charAt(k)).matches(regex1)) {
                dots++;
            }
            k++;
        }
        if (!String.valueOf(text.charAt(text.length() - 1)).matches(regex1)) {
            dots++;
        }
        String reg = "[aeiou]";
        int lenW = 0;
        int vowel = 0;
        int hard = 0;
        msg = msg.toLowerCase();
        msg = msg.replaceAll("[aeiou]{2,}", "a");
        msg = msg.replaceAll("[,\\.?!]", "");
        String[] words = msg.split("\\s");
        for (int i = 0; i < words.length; i++) {
            lenW = 0;
            while (words[i].length() > lenW) {
                if (Character.toString(words[i].charAt(lenW)).matches(reg) && i != lenW - 1) {
                    vowel++;
                }
                lenW++;
            }
            if (vowel > 2) {
                hard++;
            }
            vowel = 0;
        }
        int sill = sill(msg);
        System.out.println("The text is :");
        System.out.println(msg);
        System.out.println("Words: " + (wordss + 1));
        System.out.println("Sentences:" + dots);
        System.out.println("Characters:" + len);
        System.out.println("Syllables:" + sill(msg));
        System.out.println("Polysyllables:" + hard);
        System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        System.out.println();
        Scanner sc = new Scanner(System.in);
        String enter = sc.nextLine();
        double age = 0;
        if (enter.equals("all")) {
            age += AutomatedIndex(dots, wordss, len);
            age += FleschKincaid(sill, dots, wordss);
            age += SMOG(dots, hard);
            age += ColemanLiau(dots, wordss, len);
            System.out.println();
            System.out.print("This text should be understood in average by " + age / 4 + "-year-olds.");
        } else if (enter.equals("ARI")) {
            AutomatedIndex(dots, wordss, len);
        } else if (enter.equals("SMOG")) {
            SMOG(dots, hard);
        } else if (enter.equals("FK")) {
            FleschKincaid(sill, dots, wordss);
        } else if (enter.equals("CL")) {
            ColemanLiau(dots, wordss, len);
        }
    }
}
