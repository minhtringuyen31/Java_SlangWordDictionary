import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.io.FileReader;

public class Dictionary {
    private HashMap<String, ArrayList<String>> _dictionary = new HashMap<String, ArrayList<String>>();

    Dictionary() {
        _dictionary = new HashMap<String, ArrayList<String>>();
    }

    public HashMap<String, ArrayList<String>> getDictionary() {
        return _dictionary;
    }

    public void setDictionary(HashMap<String, ArrayList<String>> dictionary) {
        this._dictionary = dictionary;
    }

    public HashMap<String, ArrayList<String>> searchByKeySlang(String key) {
        // Option: chuyen key ve dang chu thuong
        // String standardKey = key.toLowerCase();

        HashMap<String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>();
        for (HashMap.Entry<String, ArrayList<String>> entry : _dictionary.entrySet()) {
            if (entry.getKey().contains(key)) {
                res.put(entry.getKey(), entry.getValue());

            }
        }
        return res;
    }

    public HashMap<String, ArrayList<String>> searchByDefinition(String key) {
        // Option: chuyen key ve dang chu thuong
        // String standardKey = key.toLowerCase();
        HashMap<String, ArrayList<String>> res = new HashMap<String, ArrayList<String>>();
        for (HashMap.Entry<String, ArrayList<String>> entry : _dictionary.entrySet()) {
            boolean check = false;
            for (String str : entry.getValue()) {
                if (str.contains(key)) {
                    check = true;
                }
            }
            if (check) {
                res.put(entry.getKey(), entry.getValue());

            }
        }
        return res;
    }

    public boolean checkExistedSlangWord(String slang) {
        return _dictionary.containsKey(slang) ? true : false;
    }

    public void addDuplicateANewSlangWord(String slang, ArrayList<String> definition) {
        ArrayList<String> oldDefinition = _dictionary.get(slang);
        oldDefinition.addAll(definition);
        _dictionary.put(slang, oldDefinition);
    }

    public void addANewSlangWord(String slang, ArrayList<String> definition) {
        _dictionary.put(slang, definition);
    }

    // public void deleteASlangWord(){
    // try{
    // String slang =Slang.F
    // }
    // }

    public void randomSlangWord() {
        ArrayList<String> keySet = new ArrayList<String>(_dictionary.keySet());
        Random rd = new Random();
        int rdIndex = rd.nextInt(keySet.size());
        String key = keySet.get(rdIndex);
        String definition = _dictionary.get(key).get(0);
        String rdWord = key + ": " + definition;
        // rdLabel.settext(rdWord);
    }

}
