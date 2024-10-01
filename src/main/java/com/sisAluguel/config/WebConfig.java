package com.sisAluguel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(@NonNull FormatterRegistry registry) {
        registry.addFormatterForFieldType(LocalDate.class, (Formatter<?>) new LocalDateFormatter());
    }

    public static class LocalDateFormatter implements org.springframework.format.Parser<LocalDate>, org.springframework.format.Printer<LocalDate> {

        @Override
        @NonNull
        public LocalDate parse(@NonNull String text, @NonNull Locale locale) {
            // Certifique-se de que o valor retornado nunca seja nulo
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(text, formatter);
        }

        @Override
        @NonNull
        public String print(@NonNull LocalDate object, @NonNull Locale locale) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return formatter.format(object);
        }
    }
}
