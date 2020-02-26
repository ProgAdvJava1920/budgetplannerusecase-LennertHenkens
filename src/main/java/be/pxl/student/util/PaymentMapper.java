package be.pxl.student.util;

import be.pxl.student.entity.Payment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PaymentMapper {

    public static Payment map(String line) {
        String[] splitted = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);

        return new Payment(LocalDateTime.parse(splitted[3], formatter), Float.parseFloat(splitted[4]), splitted[5], splitted[6]);
    }
}
