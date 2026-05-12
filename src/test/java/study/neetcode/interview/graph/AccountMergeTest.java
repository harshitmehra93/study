package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

class AccountMergeTest {

    @Test
    void test_givenExample() {
        AccountMerge accountMerge = new AccountMerge();

        List<List<String>> accounts =
                List.of(
                        List.of("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                        List.of("John", "johnsmith@mail.com", "john00@mail.com"),
                        List.of("Mary", "mary@mail.com"),
                        List.of("John", "johnnybravo@mail.com"));

        List<List<String>> expectedAccounts =
                List.of(
                        List.of(
                                "John",
                                "john00@mail.com",
                                "john_newyork@mail.com",
                                "johnsmith@mail.com"),
                        List.of("Mary", "mary@mail.com"),
                        List.of("John", "johnnybravo@mail.com"));

        assertAccountsEqual(expectedAccounts, accountMerge.accountsMerge(accounts));
    }

    @Test
    void test_transitiveMerge() {
        AccountMerge accountMerge = new AccountMerge();

        List<List<String>> accounts =
                List.of(
                        List.of("John", "a@mail.com", "b@mail.com"),
                        List.of("John", "c@mail.com", "d@mail.com"),
                        List.of("John", "b@mail.com", "c@mail.com"));

        List<List<String>> expectedAccounts =
                List.of(List.of("John", "a@mail.com", "b@mail.com", "c@mail.com", "d@mail.com"));

        assertAccountsEqual(expectedAccounts, accountMerge.accountsMerge(accounts));
    }

    @Test
    void test_noSharedEmails_noMerge() {
        AccountMerge accountMerge = new AccountMerge();

        List<List<String>> accounts =
                List.of(
                        List.of("Gabe", "gabe0@mail.com", "gabe3@mail.com", "gabe1@mail.com"),
                        List.of("Kevin", "kevin3@mail.com", "kevin5@mail.com", "kevin0@mail.com"),
                        List.of("Mary", "mary@mail.com"));

        List<List<String>> expectedAccounts =
                List.of(
                        List.of("Gabe", "gabe0@mail.com", "gabe1@mail.com", "gabe3@mail.com"),
                        List.of("Kevin", "kevin0@mail.com", "kevin3@mail.com", "kevin5@mail.com"),
                        List.of("Mary", "mary@mail.com"));

        assertAccountsEqual(expectedAccounts, accountMerge.accountsMerge(accounts));
    }

    @Test
    void test_duplicateEmailsInsideSameAccount() {
        AccountMerge accountMerge = new AccountMerge();

        List<List<String>> accounts =
                List.of(
                        List.of("John", "a@mail.com", "a@mail.com", "b@mail.com"),
                        List.of("John", "b@mail.com", "c@mail.com"));

        List<List<String>> expectedAccounts =
                List.of(List.of("John", "a@mail.com", "b@mail.com", "c@mail.com"));

        assertAccountsEqual(expectedAccounts, accountMerge.accountsMerge(accounts));
    }

    @Test
    void test_multiplePeopleWithSameNameButNoSharedEmail_shouldNotMerge() {
        AccountMerge accountMerge = new AccountMerge();

        List<List<String>> accounts =
                List.of(
                        List.of("John", "a@mail.com"),
                        List.of("John", "b@mail.com"),
                        List.of("John", "c@mail.com"));

        List<List<String>> expectedAccounts =
                List.of(
                        List.of("John", "a@mail.com"),
                        List.of("John", "b@mail.com"),
                        List.of("John", "c@mail.com"));

        assertAccountsEqual(expectedAccounts, accountMerge.accountsMerge(accounts));
    }

    private void assertAccountsEqual(
            List<List<String>> expectedAccounts, List<List<String>> actualAccounts) {
        assertEquals(normalize(expectedAccounts), normalize(actualAccounts));
    }

    private List<List<String>> normalize(List<List<String>> accounts) {
        List<List<String>> normalized = new ArrayList<>();

        for (List<String> account : accounts) {
            assertFalse(account.isEmpty(), "Account should not be empty");

            String name = account.get(0);

            List<String> emails = new ArrayList<>(account.subList(1, account.size()));
            emails.sort(String::compareTo);

            List<String> normalizedAccount = new ArrayList<>();
            normalizedAccount.add(name);
            normalizedAccount.addAll(emails);

            normalized.add(normalizedAccount);
        }

        normalized.sort(
                Comparator.comparing((List<String> account) -> account.get(0))
                        .thenComparing(
                                account -> String.join(",", account.subList(1, account.size()))));

        return normalized;
    }
}
