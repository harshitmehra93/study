package study.lld.projects.splitwise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/*
Learnings
- time: 1h45m
- I could have simplified the model. If I ran through user stories once, probably / maybe
- I was storing the same relationship twice, that should have been a flag for me to rethink owedBy,owedTo rows for both group and overall
- I could not spend time on core apis like add expense, settle debt
- modelling for settle was completely missing
- I later spent 2 hours to add missing things and corrected its logic as well
 */
public class Main {
    public static void main(String[] args) {
        /*
        Design splitwise

        User
        Group
        Expense - Who paid, who all are involved with what share, amount spent
                amount spent can be split with different strategies. Like equal share,
                unequal share, %of amount spent.
        How will the owing be calculated ?
            A spends 90 and is split equally between A B C
            Each user can own a owedTo table and owedBy
            With each Expense we can split the transaction and update the owesTo and owedBy sections of each user
            A
                owedBy
                    B : 30
                    C : 30
            B
                owesTo
                    A : 30
            C
                owesTo
                    A : 30
        Problem with User owning these tables then we cannot have a group wise view,
            and what happens when we update transactions
        Therefore We should have a separate Overall UserBalance and GroupUserBalance
        OwesTo
            - id
            - sourceUserId
            - targetUserId
            - amount
            - groupId
        OwedBy
            - id
            - sourceUserId
            - targetUserId
            - amount
            - groupId
         Here if group is null then it can be an overall entry and if it has a group then it will be a group entry.
         Problem here is that adding an expense in a group, will add/update entry for a user pair with a group id,
            and also update overall ownings with groupId null, so invariant is that every expense addition
            will need to update multiple rows. And all these need to be done atomically, and with thread safety
         Calculate final balances for a group ?
            - get all owesTo by groupId
            - get all owedBy by groupId
            - above represent all owings in a group
            - For each user get final balance -
                Map<User, Integer> each entry will have a user and have a +/- balance representing owesTo or OwedBy
                     first construct map with all entires from owedBy for a user ID
                     then update existing or add new entries in the map with owesTo (make owedTo Negative)
         Calculate final balances Overall ?
            - get all owesTo by groupId=null
            - get all owedBy by groupId=null
            - above represent all owings
            - For each user get final balance - UserBalance -> Map<User,Integer>
                Map<User, Integer> each entry will have a user and have a +/- balance representing owesTo or OwedBy
                     first construct map with all entires from owedBy for a user ID
                     then update existing or add new entries in the map with owesTo (make owedTo Negative)

         API
         SplitwiseService
            - ExpenseService
            addExpense(Expense)
            getGroupBalance(): List<UserBalance>
            getOverallBalance(): List<UserBalance>

        ExpenseService
            OwingDao
            ExpenseDao
            SplittingService
            addExpense(Expense)
                - splitExpense based on strategy  splitExpense(expense): List<UserBalance>
                - Update OwingDao with group id null
                If group id is present - Update OwingDao with group id as is.
         SplittingService
            splitExpense(expense): List<UserBalance>
         Expense
         - id
         - paidBy: Map<User,Integer>
         - groupId
         - splitStrategy
           - (EQUAL_SHARE/SPLIT_BY_PERCENTAGE/SPLIT_BY_SHARES)
           - Map<User, Integer> (used when we define SPLIT_BY_PERCENTAGE/SPLIT_BY_SHARES)


         Owing
         - id
         - sourceUserId
         - targetUserId
         - amount
         - groupId
         - type : OwesTo / OwedBy

         UserBalance
         - User
         - Map<User, Integer>

         User
         - id
         - name

         Group
         - id
         - userId


        An expense in a group can update both group balance and overall balance




         Low priority
         Simplify settlements -> A owes B, B owes C -> create DAG and normalise traversal with
            an algorithm which reduces the number of edges in the DAG by creating new edges or updating edges
            with different values


         */
    }
}
/*
SplitwiseService
            - ExpenseService
            addExpense(Expense)
            getGroupBalance(): List<UserBalance>
            getOverallBalance(): List<UserBalance>

        ExpenseService
            OwingDao
            ExpenseDao
            SplittingService
            addExpense(Expense)
                - splitExpense based on strategy  splitExpense(expense): List<UserBalance>
                - Update OwingDao with group id null
                If group id is present - Update OwingDao with group id as is.
         SplittingService
            splitExpense(expense): List<UserBalance>
 */
class SplitwiseService {
    ExpenseService expenseService;
    OwingService owingService;

    void addExpense(Expense expense) {
        expenseService.addExpense(expense);
    }

    Map<User, Map<User, Double>> getGroupBalance(int userId, int groupId) {
        return owingService.getGroupBalance(groupId);
    }

    Map<User, Map<User, Double>> getOverallBalance(int userId) {
        return owingService.getOverallBalance(userId);
    }

    void recordPayment(User a, User b, int groupId, double amount) {
        owingService.recordPaymentInGroup(a, b, groupId, amount);
    }
}

class ExpenseService {
    OwingService owingService;
    ExpenseDao expenseDao;
    SplittingService splittingService;

    void addExpense(Expense expense) {
        Map<User, Map<User, Double>> balances = splittingService.split(expense);
        // atomic transaction boundary start
        // we need a huge db level optimistic lock via a transaction on all owingBy and owingto
        // rows, along with expense row
        addAllOwingEntries(balances, expense.groupId);
        expenseDao.save(expense);
        // atomic transaction boundary end
    }

    void addAllOwingEntries(Map<User, Map<User, Double>> balances, Integer groupId) {
        for (Map.Entry<User, Map<User, Double>> userBalance : balances.entrySet()) {
            User creditor = userBalance.getKey();
            for (Map.Entry<User, Double> debtorEntry : userBalance.getValue().entrySet()) {
                User debtor = debtorEntry.getKey();
                Double amount = debtorEntry.getValue();
                if (amount == 0) continue;
                owingService.createOrUpdateOwing(creditor, debtor, amount, groupId);
            }
        }
    }
}

class SplittingService {
    Map<User, Map<User, Double>> split(Expense expense) {
        return null;
    }
}

class ExpenseDao {

    public void save(Expense expense) {}
}

class OwingService {
    OwingDao owingDao;
    UserService userService;

    public void createOrUpdateOwing(User principal, User target, Double balance, Integer groupId) {
        //  get existing entry for principal with the type and target user in overall, or assume 0
        // balance
        //  get existing entry for principal with the type and target user in group, or assume 0
        // balance

        // first update overall
        // then update group id entries for principal and owingtype
    }

    /*
           A
               owedBy
                   B : 30
                   C : 30
           B
               owesTo
                   A : 30
           C
               owesTo
                   A : 30
    */
    public Map<User, Map<User, Double>> getGroupBalance(Integer groupId) {
        List<Owing> owings = owingDao.getAllOwingsInGroup(groupId);

        Map<User, Map<User, Double>> result = new HashMap<>();

        for (Owing owing : owings) {
            User creditor = owing.creditor;
            if (result.containsKey(creditor)) continue;
            Map<User, Double> debtors = getAllDebtorsForCreditor(creditor, owings);
            result.put(creditor, debtors);
        }

        return result;
    }

    /*
    A is owed by B 20
    A is owed by B 30
    A is owed by C 40
     */
    private Map<User, Double> getAllDebtorsForCreditor(User creditor, List<Owing> owings) {
        Map<User, Double> debtors = new HashMap<>();
        for (Owing owing : owings) {
            if (owing.creditor != creditor) continue;
            debtors.put(owing.debtor, debtors.getOrDefault(owing.debtor, 0.0) + owing.amount);
        }
        return debtors;
    }

    public Map<User, Map<User, Double>> getOverallBalance(int userId) {
        List<Owing> owingsForA = owingDao.getAllOwingForUser(userId);
        List<Owing> debtsOfA = owingDao.getAllDebtsOfUser(userId);

        User user = userService.get(userId);

        Map<User, Map<User, Double>> result = new HashMap<>();
        HashMap<User, Double> balances = new HashMap<>();
        result.put(user, balances);
        for (Owing owing : debtsOfA) {
            balances.put(owing.creditor, balances.getOrDefault(owing.creditor, 0.0) - owing.amount);
        }
        for (Owing owing : owingsForA) {
            balances.put(owing.debtor, balances.getOrDefault(owing.debtor, 0.0) + owing.amount);
        }
        return result;
    }

    public void recordPaymentInGroup(User a, User b, int groupId, double amount) {
        List<Owing> owingBetweenAB = owingDao.getAllOwingForGroup(a, b, groupId);

        // A is owed by B 50
        double totalOwedByBToA = 0;
        for (Owing oweing : owingBetweenAB) {
            if (oweing.creditor.id == a.id) {
                totalOwedByBToA += oweing.amount;
            } else {
                totalOwedByBToA -= oweing.amount;
            }
        }
        totalOwedByBToA -= amount;
        owingBetweenAB.forEach(o -> owingDao.delete(o.id));
        if (totalOwedByBToA == 0) {
            System.out.println("completely settled");
        } else if (totalOwedByBToA > 0) {
            System.out.println("B owes A " + totalOwedByBToA);
            owingDao.save(new Owing(a, b, groupId, totalOwedByBToA));
        } else {
            double owedByAtoB = -totalOwedByBToA;
            System.out.println("A owes B " + totalOwedByBToA);
            owingDao.save(new Owing(b, a, groupId, owedByAtoB));
        }
    }
}

class UserService {
    User get(int id) {
        return null;
    }
}

class OwingDao {

    Map<Integer, Owing> owings = new HashMap<>();

    public List<Owing> getAllOwingsInGroup(Integer groupId) {
        return owings.values().stream().filter(o -> Objects.equals(o.groupId, groupId)).toList();
    }

    public Map<User, Map<User, Double>> getAllOwedToUserBalances(Integer groupId) {
        return null;
    }

    public List<Owing> getAllOwing(User a, User b) {
        return null;
    }

    public void delete(int id) {}

    public void save(Owing owing) {}

    public List<Owing> getAllOwingForGroup(User a, User b, int groupId) {
        return null;
    }

    public List<Owing> getAllOwingForUser(int userId) {
        return null;
    }

    public List<Owing> getAllDebtsOfUser(int userId) {
        return null;
    }
}

class User {
    int id;
    String name;
    String email;
    int phoneNumber;
}

class Group {
    int id;
    List<User> users;
}

class Owing {
    int id;
    User creditor;
    User debtor;
    double amount;
    Integer groupId;

    public Owing(User a, User b, int groupId, double totalOwed) {}
}
/*
        Expense
        - id
        - paidBy: Map<User,Integer>
        - groupId
        - splitStrategy
          - (EQUAL_SHARE/SPLIT_BY_PERCENTAGE/SPLIT_BY_SHARES)
          - Map<User, Integer> (used when we define SPLIT_BY_PERCENTAGE/SPLIT_BY_SHARES)

*/
class Expense {
    int id;
    Map<User, Double> paidBy;
    Integer groupId; // nullable
    SplitStrategyDetails splitStrategyDetails;
}

class SplitStrategyDetails {
    SplitStrategy splitStrategy;
    Map<User, Double> details;
}

enum SplitStrategy {
    EQUAL_SHARE,
    SPLIT_BY_PERCENTAGE,
    SPLIT_BY_SHARES,
    SPLIT_BY_AMOUNT
}
