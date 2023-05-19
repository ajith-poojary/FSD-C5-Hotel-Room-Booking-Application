package com.upgrad.booking.helper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringJoiner;

@Component
public class HotelDesk {
    StringJoiner stringJoiner=new StringJoiner(",");

    public  ArrayList<String> getRandomNumbers(int count){
        System.out.println("count "+count);
        Random rand = new Random();
        int upperBound = 100;
        ArrayList<String> numberList = new ArrayList<String>();

        for (int i=0; i<count; i++){
            numberList.add(String.valueOf(rand.nextInt(upperBound)));
        }

        System.out.println(numberList);

        return numberList;
    }

    public int calculatePrice(int numOfDays,int numOfRooms)
    {
        return 1000*numOfDays*numOfRooms;
    }

}
