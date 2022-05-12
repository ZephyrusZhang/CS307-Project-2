package edu.sustech.cs307.util;

import java.sql.Date;

public class Util {

    public static String formatDate(String date) {
        return Date.valueOf(date).toString();
    }

}
