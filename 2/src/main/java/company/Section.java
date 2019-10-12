package company;

import java.util.ArrayList;

public class Section {
    private class Field {
        public final String name;
        public final String value;

        public Field(String name, String value) {
            this.name = name;
            this.value = value;
        }

        @Override
        public String toString() {
            return name + " = " + value + "\n";
        }
    } private ArrayList<Field> fields;

    final private String name;

    Section(String name) {
        this.name = name;
        fields = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public ArrayList<String> getFieldNames() {
        ArrayList<String> fieldNames = new ArrayList<>();

        for (Field x : fields){
            fieldNames.add(x.name);
        }
        return fieldNames;
    }

    public String getValue(String fieldName) {
        for (Field x : fields) {
            if (x.name.equals(fieldName)) {
                return x.value;
            }
        }
        throw new ContainException("\"" + name + "\" section doesn't contain \"" + fieldName + "\" field \n");
    }

    public void add(String name, String value){
        fields.add(new Field(name, value));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[" + name + "]\n");

        for (Field x : fields) {
            stringBuilder.append(x.toString());
        }
        return stringBuilder.toString();
    }
}
