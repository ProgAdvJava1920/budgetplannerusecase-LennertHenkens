package be.pxl.student.util;

import be.pxl.student.BudgetPlanner;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    private static final Logger LOGGER = LogManager.getLogger(BudgetPlanner.class);
    //checken of een file de juiste extentie heeft
    private PathMatcher csvMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");

    public void importCsv(Path path) {
        if (!csvMatcher.matches(path)) {
            LOGGER.error("Invalid file: .csv expected. Provided {}", path);
            return;
        }

        if (!Files.exists(path)) {
            LOGGER.error("File {} does not exist.", path);
            return;
        }
        List<Account> accounts = new ArrayList<>();
        List<Payment> payments = new ArrayList<>();
        //Thu Feb 13 05:47:35 CET 2020
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM d hh:mm:ss z yyyy", Locale.ENGLISH);
        try (BufferedReader reader = Files.newBufferedReader(path);){
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                String[] splitted = line.split(",");
                //creating account and adding name and iban
                Account account = new Account();
                account.setName(splitted[0]);
                account.setIBAN(splitted[1]);

                //creating payment
                Payment payment = new Payment(LocalDateTime.parse(splitted[3], formatter), Float.parseFloat(splitted[4]), splitted[5], splitted[6]);

                //adding accounts and payments
                payments.add(payment);
                account.setPayments(payments);
                accounts.add(account);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

