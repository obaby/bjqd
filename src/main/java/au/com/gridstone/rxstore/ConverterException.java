package au.com.gridstone.rxstore;

public class ConverterException extends RuntimeException {
    public ConverterException() {
    }

    public ConverterException(String str) {
        super(str);
    }

    public ConverterException(String str, Throwable th) {
        super(str, th);
    }

    public ConverterException(Throwable th) {
        super(th);
    }
}
