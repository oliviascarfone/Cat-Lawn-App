package persistance;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.oracle.javafx.jmx.json.JSONWriter;
import model.Cat;
import model.Yard;
import netscape.javascript.JSObject;

import java.io.*;
import java.util.ArrayList;

//ideas from https://crunchify.com/how-to-write-json-object-to-file-in-java/
//a writer that writes cat lawn data to a file
public class Writer {

    private static FileWriter file;

    //EFFECTS: creates a json object for the yard that is saved to file

    public static void toFile() {
        try {
            file = new FileWriter("./data/yard.txt");

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                file.flush();
                file.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


//
//    private PrintWriter printWriter;
//
//    // EFFECTS: constructs a writer that will write data to file
//    public Writer(File file) throws FileNotFoundException, UnsupportedEncodingException {
//        printWriter = new PrintWriter(file, "UTF-8");
//
//    }
//
//
//
//     //MODIFIES: this
//     //EFFECTS: writes saveable to file
//
//    public void write(Saveable saveable) {
//        saveable.save(printWriter);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: close print writer
//    // NOTE: you MUST call this method when you are done writing data!
//    public void close() {
//        printWriter.close();
//    }


}
