package com.tnt.modelshoptrantrung.util;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
public class Validator implements IValidator{
    public static final String NUMBER_REGEX = "^\\d+$";

    public static final String LETTER_WITHOUT_NUMBER_REGEX = "([A-Z]+[a-z]*[ ]?)+$";
    public static final String EMAIL_REGEX = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    public static final String PASSWORD_COMPLEX_REGEX = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]+)(?!.*['\"`])(?=^.{6,}$).*$";

    private static final Pattern NONLATIN = Pattern.compile("[^\\w_-]");
    private static final Pattern SEPARATORS = Pattern.compile("[\\s\\p{Punct}&&[^-]]");

    public static String makeSlug(String input) {
        String noseparators = SEPARATORS.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(noseparators, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        return slug.toLowerCase( Locale.ENGLISH).replaceAll("-{2,}","-").replaceAll("^-|-$","");
    }

    public static boolean isLetterWithoutNumberValid(String name) {
        return Pattern.compile(LETTER_WITHOUT_NUMBER_REGEX).matcher(name).matches();
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public static boolean isPasswordValid(String pwd) {
        return Pattern.compile(PASSWORD_COMPLEX_REGEX).matcher(pwd).matches();
    }

    private String dateFormat;

    public Validator(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Validator() {

    }

    @Override
    public boolean isIntValid(String number) {
        return Pattern.compile( NUMBER_REGEX ).matcher(number).matches();
    }

    @Override
    public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat (this.dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
