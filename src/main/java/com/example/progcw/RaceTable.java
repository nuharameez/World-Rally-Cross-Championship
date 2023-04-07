package com.example.progcw;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
                        formattedEachRace.add(details[details.length-1]);
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


        List<List<String>> modifiedList = new ArrayList<>();

        for(List<String> innerList : allRaces){
            String location = innerList.get(innerList.size() - 2);
            String date = innerList.get(innerList.size() - 1);
            List<String> modifiedInnerList = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i< innerList.size() - 2; i++){
                sb.append(innerList.get(i)).append(" ");
            }
            modifiedInnerList.add(sb.toString().trim());
            modifiedInnerList.add(location);

            try{
                SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date convertDate = originalFormat.parse(date);
                modifiedInnerList.add(originalFormat.format(convertDate));
            }
            catch (ParseException e){
                System.out.println("Date could not be converted to date format");
            }
            modifiedList.add(modifiedInnerList);
        }

        /*for(int k = 1; k< modifiedList.size();k++){
            List<String> current = modifiedList.get(k);
            String currentDate = current.get(current.size()-1);
            int j = k - 1;
            while(j >=0 && currentDate.compareTo(modifiedList.get(k).get(modifiedList.get(j).size()-1))<0){
                modifiedList.set(j+1, modifiedList.get(j));
                j--;

        }
        System.out.println("Sorted" + modifiedList);*/

        boolean sorted = false;
        while(!sorted){
            sorted = true;
            //loop to go trhough all elements in the nested list
            for(int i = 0; i< modifiedList.size()-1;i++){
                //loop to iterate through all the elements in the inner list
                //for(int j =0; j< modifiedList.get(i).size() -1; j++){
                    //checks for third element to consider it date
                    //if (j + 2 < modifiedList.get(i).size()  && i + 1 < modifiedList.size() ) {
                        //getting current date and next date
                        String currentDate = (String) modifiedList.get(i).get(2);
                        String nextDate = (String) modifiedList.get(i+1).get(2);
                        //sepearting the day, month and year to compare all three.
                        String[] currentDateElements = currentDate.split("-");
                        String[] nextDateElements = nextDate.split("-");
                        int currentDay = Integer.parseInt(currentDateElements[0]);
                        int nextDay = Integer.parseInt(nextDateElements[0]);
                        int currentMonth = Integer.parseInt(currentDateElements[1]);
                        int nextMonth = Integer.parseInt(nextDateElements[1]);
                        int currentYear = Integer.parseInt(currentDateElements[2]);
                        int nextYear = Integer.parseInt(nextDateElements[2]);
                        if(currentYear>nextYear || (currentYear == nextYear && currentMonth > nextMonth) || (currentYear == nextYear && currentMonth == nextMonth && currentDay > nextDay)){
                            Collections.swap(modifiedList, i, i+1);
                            sorted = false;
                        }
                    }


        }
        System.out.println(modifiedList);
    }
}





