package com.advanceio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.commons.math3.util.CombinatoricsUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.advanceio.exception.BadRequestException;
import com.google.common.collect.Lists;

@SpringBootApplication
public class MoneyDispenserApplication {

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/api");
        SpringApplication.run(MoneyDispenserApplication.class, args);
    }

    /*
     * public static void main(String [] args) { double changeDue = 12.75; int
     * change = (int)(Math.ceil(changeDue*100)); int dollars =
     * Math.round((int)change/100); change=change%100; int quarters =
     * Math.round((int)change/25); change=change%25; int dimes =
     * Math.round((int)change/10); change=change%10; int nickels =
     * Math.round((int)change/5); change=change%5; int pennies =
     * Math.round((int)change/1);
     * 
     * System.out.println("Dollars: " + dollars);
     * System.out.println("Quarters: " + quarters); System.out.println("Dimes: "
     * + dimes); System.out.println("Nickels: " + nickels);
     * System.out.println("Pennies: " + pennies); }
     */

}