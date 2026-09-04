package study.ocp.io; // Write code to compute the relative path between:

// /data/reports/2024/sales.txt

// and

// /data/reports/2024/summary.txt

// Expected output:

// ../summary.txt

// Practice:

// Path.relativize()
import java.nio.file.*;

public class RelativePaths {
    public static void main(String... args) {
        Path a = Path.of("/data/reports/2024/sales.txt");
        Path b = Paths.get("/data/reports/2024/summary.txt");
        System.out.println(a.relativize(b));
        System.out.println(b.relativize(a));
        System.out.println(a.relativize(a));
    }
}
