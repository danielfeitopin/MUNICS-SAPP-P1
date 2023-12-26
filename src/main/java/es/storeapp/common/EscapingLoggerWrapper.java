package es.storeapp.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.owasp.encoder.Encode;

public class EscapingLoggerWrapper {
    private final Logger wrappedLogger;

    public EscapingLoggerWrapper(Class<?> clazz) {
        this.wrappedLogger = LoggerFactory.getLogger(clazz);
    }

    // Implementa todos los métodos de la interfaz org.slf4j.Logger
    // y agrega la lógica de escape de caracteres antes de llamar a los métodos del logger subyacente

    public void info(String format, Object... arguments) {
        String escapedFormat = escapeCharacters(format);
        wrappedLogger.info(escapedFormat, arguments);
    }

    public void error(String format, Object... arguments) {
        String escapedFormat = escapeCharacters(format);
        wrappedLogger.error(escapedFormat, arguments);
    }

    public void debug(String format, Object... arguments) {
        String escapedFormat = escapeCharacters(format);
        wrappedLogger.debug(escapedFormat, arguments);
    }

    public void warn(String format, Object... arguments) {
        String escapedFormat = escapeCharacters(format);
        wrappedLogger.warn(escapedFormat, arguments);
    }

    public boolean isWarnEnabled(){
        return wrappedLogger.isWarnEnabled();
    }

    public boolean isDebugEnabled(){
        return wrappedLogger.isDebugEnabled();
    }

    public boolean isErrorEnabled(){
        return wrappedLogger.isErrorEnabled();
    }

    // Método de escape de caracteres
    private String escapeCharacters(String input) {
        String string = Encode.forHtml(input);//evita javascript inyection
        return Encode.forJava(string);//codifica los saltos de linea
    }
}
