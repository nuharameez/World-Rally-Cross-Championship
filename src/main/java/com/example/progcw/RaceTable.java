package com.example.progcw;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RaceTable {
    public RaceTable() throws IOException {
        String filePath = "raceDrivers.txt";
        List<List<String>> allRaces = new ArrayList<>();
        List<String> eachRace = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner readFile = new Scanner(file);
            while (readFile.hasNextLine()) {
                String line = readFile.nextLine();
                String[] details = line.split(",");

                if (details.length>0 && details[details.length - 1].matches("\\d{2}-\\d{2}-\\d{4}")) {
                    List<String> formattedEachRace = new ArrayList<>();
                    for (int i = 0; i < eachRace.size(); i += 2) {
                        if (i + 1 < eachRace.size()) {
                            formattedEachRace.add(eachRace.get(i) + "-" + eachRace.get(i + 1));
                        }
                        else{
                            formattedEachRace.add(eachRace.get(i));
                        }
                    }
                    if(!formattedEachRace.isEmpty()){
                        allRaces.add(formattedEachRace);
                    }

                    eachRace = new ArrayList<>();
                } else {
                    for (String element : details) {
                        eachRace.add(element);
                    }
                }
            }

            List<String> formattedEachRace = new ArrayList<>();
            for (int i = 0; i < eachRace.size(); i += 2) {
                if (i + 1 < eachRace.size()) {
                    formattedEachRace.add(eachRace.get(i) + "-" + eachRace.get(i + 1));
                }
                else{
                    formattedEachRace.add(eachRace.get(i));
                }
            }
            if(!formattedEachRace.isEmpty()){
                allRaces.add(formattedEachRace);
            }
            

            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");

        }
        System.out.println(allRaces);
    }
}





