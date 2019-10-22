package company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.MalformedURLException;
import java.net.URL;

public class FileIni {
    private ArrayList<Section> sections;

    
    /** 
     * @return 
     */
    public FileIni(){
        sections = new ArrayList<Section>();
    }

    
    /** 
     * @param fileName
     * @throws IOException
     */
    public void setInformation(String fileName) throws IOException  {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Pattern sectionPattern = Pattern.compile("^\\[([A-Z0-9_]+)\\] ?;?.*");
        Pattern fieldPattern = Pattern.compile("^([a-zA-Z0-9_]+) = ([^ ;\t]+) ?;?.*");
        Pattern neutralStringPattern = Pattern.compile("^(;.*)|(\t*)");
        String string;
        Section section = null;

        while ((string = reader.readLine()) != null) {
            Matcher sectionMatcher = sectionPattern.matcher(string);
            Matcher fieldMatcher = fieldPattern.matcher(string);
            Matcher neutralStringMatcher = neutralStringPattern.matcher(string);

            if(!neutralStringMatcher.matches()) {
                if (sectionMatcher.matches()) {
                    section = new Section(sectionMatcher.group(1));
                    sections.add(section);
                } else if (fieldMatcher.matches() && section != null) {
                    section.add(fieldMatcher.group(1), fieldMatcher.group(2));
                } else {
                    throw new IniFormatException("Wrong IniFile format at input string: \"" + string + "\"");
                }
            }
        }
        reader.close();
    }

    
    /** 
     * @param sectionName
     * @param fieldName
     * @param parser
     * @return T
     */
    private  <T> T getValue(String sectionName, String fieldName, FromStringFunction<T> parser) {
        for (Section x : sections) {
            if (x.getName().equals(sectionName)) {
                return parser.parse(x.getValue(fieldName));
            }
        }
        throw new ContainException("The file doesn't contain \"" + sectionName + "\" section");
    }

    
    /** 
     * @param sectionName
     * @param fieldName
     * @return int
     */
    public int getIntValue(String sectionName, String fieldName) {
        return getValue(sectionName, fieldName, (String string) -> {
            try {
                return Integer.parseInt(string);
            } catch (NumberFormatException e){
                throw new IniFormatException("Incorrect value type " + e.getMessage().toLowerCase());
            }
        });
    }

    
    /** 
     * @param sectionName
     * @param fieldName
     * @return double
     */
    public double getDoubleValue(String sectionName, String fieldName) {
        return getValue(sectionName, fieldName, (String string) -> {
            try {
                return Double.parseDouble(string);
            } catch (NumberFormatException e) {
                throw new IniFormatException("Incorrect value type " + e.getMessage().toLowerCase());
            }
        });
    }

    
    /** 
     * @param sectionName
     * @param fieldName
     * @return String
     */
    public String getStringValue(String sectionName, String fieldName) {
        return getValue(sectionName, fieldName, (String string) -> string);
    }

    
    public long getLongValue(String sectionName, String fieldName) {
        return getValue(sectionName, fieldName, (String string) -> {
            try {
                return Long.parseLong(string);
            } catch (NumberFormatException e) {
                throw new IniFormatException("Incorrect value type " + e.getMessage().toLowerCase());
            }
        });
    }

    public URL getUrlValue(String sectionName, String fieldName) {
        return getValue(sectionName, fieldName, (String string) -> {
            try {
                return new URL(string);
            } catch (NumberFormatException | MalformedURLException e) {
                throw new IniFormatException("Incorrect value type " + e.getMessage().toLowerCase());
            }
        });
    }
    /** 
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Section x : sections) {
            stringBuilder.append(x.toString());
        }
        return stringBuilder.toString();
    }
}