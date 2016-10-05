package chibill.FunCommands.utils;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileArrayProvider {

    public static String[] readLines(File file) throws IOException {
        Scanner scan = new Scanner(file);
        List<String> lines = new ArrayList<String>();
        String line = null;
        while (scan.hasNextLine()) {
            lines.add(scan.nextLine());
        }
        return lines.toArray(new String[lines.size()]);
    }
}
