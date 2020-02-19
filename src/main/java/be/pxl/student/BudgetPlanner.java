package be.pxl.student;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.util.BudgetPlannerImporter;
import javafx.util.converter.LocalDateStringConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.Format;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BudgetPlanner {
    public static void main(String[] args) {
        new BudgetPlannerImporter().importCsv(Paths.get("src/main/resources/account_payments.csv"));
    }
}



