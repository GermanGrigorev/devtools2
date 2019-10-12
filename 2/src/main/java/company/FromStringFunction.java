package company;

@FunctionalInterface
public interface FromStringFunction<T> {
    T parse(String string);
}
