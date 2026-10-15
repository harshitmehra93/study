package study.lld.projects.amazonlocker;

public class Main {
    public static void main(String[] args) {
        /*
        Amazon locker
        - multiple lockers of different sizes
        - amazon admin puts package in a locker and notifies customer with locker password,
            the locker + package will have an expiry lease. After lease expiry locker will be cleared.
        - amazon admin puts a package in the smallest possible of available packages. MEDIUM can be put in a MEDIUM/LARGE
            locker and so on. We can use a strategy to select available lockers. We will take an appropriate size locker
            and also one which have expired leases. Which we will assume as available.
        - One mutation boundary is when a package is put in the locker system so both read and write of the locker
            should be atomic and thread safe.
        - Another mutation boundary is when customer opensTheLocker and takes the package.
        - For both mutation boundaries we will acquire a write lock of the Locker.
        - When lease ends of the locker+package then we assume locker is available
        - customer can go to the locker and unlock the locker with the password.
        - A package can be of different sizes
        - password will be created, persisted, and verified by PasswordManager In a secure vault, ensuring we never
            store or validate password in the application but in a HSM vault.
        - Locker Dao will take care of giving list of available lockers of a compatible size. It will take care of resetting lockers who have expired leases

        Locker
        - id
        - isAvailable: boolean
        - currentLease: Lease
        - int packageId
        - Size: SMALL,MED,LARGE

        PasswordManager
        - createPasswordInVault(packageId,lockerId): LockerPackagePassword
        - isPasswordCorrect(lockPackagePasswordId, String enteredPassword): boolean

        LockerPackagePassword
        - id
        - int packageId
        - int lockerId
        - int secretId

        Package
        - id
        - customerId
        - Size
        - Status - DISPATCHED, IN_LOCKER, PICKED_BY_CUSTOMER

        Customer
        - id
        - name
        - email
        - phone

        AmazonAdmin
        - LockerFinderStrategy
        - NotificationService
        - CustomerService
        - addPackage(Package) -
            Get package write lock
            If package already has a lease throw
            If package is not in state DISPATCHED throw
            find a locker
            get locker write lock

            // atomic mutation boundary start
            add package to locker.
            Notify customer.
            Create a lease and add to PackageLockerLease
            set PACKAGE state as INLOCKER
            // atomic mutation boundary end
            release locks

        PackageLockerLease
        - id
        - packageId
        - lockerId
        - expiryTime

        LeaseManager
        - tryCreateLease(packageId,lockerId)
        - getLeaseForLocker(lockerId)
        - getLeaseForPackage(packageId)

        CustomerInterface
        - PasswordManager
        - openLocker(lockerId, String password) -
            - passwordManager validates password
            - start of mutatuion boundary transaction
            - get locker lock and package lock in order
            - locker is opened and marked available its current package state is set to null.
            - package state is set to PICKED UP
            - lease is cleared
            - end of mutatuion boundary transaction
         */
    }
}
