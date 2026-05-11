package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class AccountMergeTest {
    @Test
    void test() {
        AccountMerge accountMerge = new AccountMerge();
        List<List<String>> accounts =
                List.of(
                    List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                    List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                    List.of("Mary", "mary@mail.com"),
                    List.of("John", "johnnybravo@mail.com")
                );

        List<List<String>> expectedAccounts =
            List.of(
                List.of("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                List.of("Mary", "mary@mail.com"),
                List.of("John", "johnnybravo@mail.com")
            );

        assertEquals(expectedAccounts, accountMerge.accountsMerge(accounts));
    }
}
