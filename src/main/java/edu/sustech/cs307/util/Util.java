package edu.sustech.cs307.util;

import java.sql.Date;
import java.time.LocalDate;

public class Util {

    public static String formatDate(String date) {
        return Date.valueOf(date).toString();
    }

    public static LocalDate strToLocalDate(String date) {
        return LocalDate.parse(formatDate(date.replaceAll("/", "-")));
    }

}
