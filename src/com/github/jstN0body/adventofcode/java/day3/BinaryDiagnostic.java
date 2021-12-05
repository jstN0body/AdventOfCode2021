package com.github.jstN0body.adventofcode.java.day3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryDiagnostic {
    public static void main(String[] args) {
        int length = args[0].length();
        int[] ones = new int[length];
        int[] zeros = new int[length];

        for (String s : args) {
            for (int i = 0; i < length; i++) {
                if (s.charAt(i) == '1') {
                    ones[i]++;
                } else {
                    zeros[i]++;
                }
            }
        }
        String gamma = "", epsilon = "";
        for (int i = 0; i < length; i++) {
            if (ones[i] > zeros[i]) {
                gamma = gamma.concat("1");
                epsilon = epsilon.concat("0");
            } else {
                gamma = gamma.concat("0");
                epsilon = epsilon.concat("1");
            }
        }

        System.out.println(gamma + " gamma");
        System.out.println(epsilon + " epsilon");
        int gammaDec = Integer.parseInt(gamma, 2);
        int epsilonDec = Integer.parseInt(epsilon, 2);

        System.out.println(gammaDec * epsilonDec + " consumption\n");
        List<String> oxygenGen = Arrays.asList(args);
        List<String> co2Scrubber = Arrays.asList(args);

        while (oxygenGen.size() > 1 || co2Scrubber.size() > 1) {
            for (int i = 0; i < length; i++) {
                if (oxygenGen.size() > 1) {
                    List<String> onesOxygen = new ArrayList<>();
                    List<String> zerosOxygen = new ArrayList<>();
                    for (String s : oxygenGen) {
                        if (s.charAt(i) == '1') {
                            onesOxygen.add(s);
                        } else {
                            zerosOxygen.add(s);
                        }
                    }
                    if (onesOxygen.size() >= zerosOxygen.size()) {
                        oxygenGen = onesOxygen;
                    } else {
                        oxygenGen = zerosOxygen;
                    }
                }

                if (co2Scrubber.size() > 1) {
                    List<String> onesCO2 = new ArrayList<>();
                    List<String> zerosCO2 = new ArrayList<>();
                    for (String s : co2Scrubber) {
                        if (s.charAt(i) == '1') {
                            onesCO2.add(s);
                        } else {
                            zerosCO2.add(s);
                        }
                    }
                    if (zerosCO2.size() <= onesCO2.size()) {
                        co2Scrubber = zerosCO2;
                    } else {
                        co2Scrubber = onesCO2;
                    }
                }
            }
        }

        int oxygenGenDec = Integer.parseInt(oxygenGen.get(0), 2);
        int co2ScrubberDec = Integer.parseInt(co2Scrubber.get(0), 2);
        System.out.println(oxygenGenDec + " oxygen generator rating");
        System.out.println(co2ScrubberDec + " CO2 scrubber rating");
        System.out.println(co2ScrubberDec * oxygenGenDec + " life support rating");
    }
}
