package study.ocp.exceptionslocalization;

import java.util.*;

class ResourceBundleLookup {
    public static void main(String... args) {
        // Locale l = Locale.getDefault();
        ResourceBundle rb = ResourceBundle.getBundle("Test");
        System.out.println(rb.getString("hello"));
        System.out.println(rb.getString("status"));

        Locale.setDefault(new Locale("es"));
        rb = ResourceBundle.getBundle("Test");
        System.out.println(rb.getString("hello"));
        System.out.println(rb.getString("status"));
    }
}
