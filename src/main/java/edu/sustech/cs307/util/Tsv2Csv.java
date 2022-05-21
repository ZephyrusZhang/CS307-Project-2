package edu.sustech.cs307.util;

import java.io.*;

public class Tsv2Csv {

    public static void main(String[] args) {
        String tsvPath = "src/main/resources/static/final-testcase/delete_final.tsv";
        String csvPath = "src/main/resources/static/final-testcase/delete_final.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(tsvPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(csvPath))) {
            String readLine;
            StringBuilder writeLine;
            String[] token;
            while ((readLine = reader.readLine()) != null) {
                token = readLine.split("\t");
                writeLine = new StringBuilder("");
                for (int i = 0; i < token.length - 1; i++) {
                    writeLine.append(token[i]).append(",");
                }
                writeLine.append(token[token.length - 1]).append("\n");
                writer.write(String.valueOf(writeLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
