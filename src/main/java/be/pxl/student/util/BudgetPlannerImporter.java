package be.pxl.student.util;

import be.pxl.student.BudgetPlanner;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
    private static final Logger LOGGER = LogManager.getLogger(BudgetPlannerImporter.class);
    //checken of een file de juiste extentie heeft
    private PathMatcher csvMatcher = FileSystems.getDefault().getPathMatcher("glob:**/*.csv");
    private AccountMapper accountMapper = new AccountMapper();

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
        try (BufferedReader reader = Files.newBufferedReader(path)){
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                try {
                    LOGGER.debug(accountMapper.map(line));
                } catch (InvalidPaymentException e) {
                    LOGGER.error("Error while mapping line: {}", e.getMessage());
                } finally {
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            LOGGER.fatal("An error occurred while reading file: {}", path);
        }

    }
}

