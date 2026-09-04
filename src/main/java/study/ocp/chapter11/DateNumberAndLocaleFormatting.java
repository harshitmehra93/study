package study.ocp.chapter11;

import java.text.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

class DateNumberAndLocaleFormatting {
    public static void main(String... args) throws ParseException {
        var name = "Harshit";
        print("Hello %s".formatted(name));
        print(String.format("Hello %s", name));
        NumberFormat df1 = new DecimalFormat("$###,###,###.##");
        print(df1.format(123456789123456789123345.123456));
        print(LocalDate.of(2026, Month.AUGUST, 15).toString());
        print(
                LocalDate.of(2026, Month.AUGUST, 15)
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "'This' yyyy 'is' MMMM 'why' dd 'I m hot'"))
                        .toString());
        print(
                LocalDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "'thi is my date- 'yyyy   MMMM    dd  'and this is my time- 'HH mm SS")));
        print(
                ZonedDateTime.now()
                        .format(
                                DateTimeFormatter.ofPattern(
                                        "'thi is my date- 'yyyy   MMMM    dd  'and this is my time- 'HH mm SS zZ")));
        Locale locale = Locale.getDefault();
        System.out.println(locale);

        NumberFormat f;
        for (var l : Locale.getAvailableLocales()) {
            f = NumberFormat.getInstance(l);
            String num = "1234567.123";
            print(f.parse(num).toString());
            print("====\n");
            // print("");
            //            NumberFormat c = new CompactNumberFormat(l, Style.SHORT);
            //            NumberFormat d = new CompactNumberFormat(l, Style.LONG);
            //            print(c.format(num));
        }
    }

    static void print(String str) {
        System.out.println(str);
    }
}
