# OCP drills

The original numbered `Test` classes are grouped here by the Java 17 OCP study-guide chapter that best matches their contents. Names describe the concept exercised rather than the order in which the files were created. Repeated former numbers came from separate batches of drills.

## Building Blocks (`basics`)

- `HelloWorld` (formerly `Test27`)
- `VariableDeclarations` (formerly `Test39`)

## Lambdas and Functional Interfaces (`lambdas`)

Revise the lambda drills in this order:

### 1. Lambda fundamentals (`lambdas.fundamentals`)

- `IntegerAdditionLambda` (formerly `Test`)
- `PrimeNumberLambda` (formerly `Test2`; under `fundamentals.prime`)
- `SquareLambda` (formerly `Test5`; under `fundamentals.square`)
- `LambdaPredicateFiltering` (formerly `Test`)

### 2. Method references (`lambdas.methodreferences`)

- `CurrentTimeMethodReference` (formerly `Test4`)
- `ParseIntegerMethodReference` (formerly `Test6`)
- `StringConcatenationMethodReference` (formerly `Test3`)
- `LowercaseMethodReference` (formerly `Test7`; under `methodreferences.lowercase`)
- `PrintlnMethodReference` (formerly `Test9`)

### 3. Constructor references (`lambdas.constructorreferences`)

- `ArrayListConstructorReference` (formerly `Test8`)

## Collections and Generics (`collections`)

- `LinkedListOperations` (formerly `Test10`)
- `ListCreationAndMutability` (formerly `Test11`)
- `HashSetOperations` (formerly `Test12`)
- `QueueOperations` (formerly `Test13`)
- `DequeOperations` (formerly `Test14`)
- `DequeAsStack` (formerly `Test15`)
- `ComparatorChaining` (formerly `Test16`)
- `TreeSetComparatorOrdering` (formerly `Test20`)

## Streams (`streams`)

Revise the stream drills in this order:

### 1. Stream fundamentals (`streams.fundamentals`)

- `StreamReductionCollectionAndGeneration` (formerly `Test17`)
- `GeneratedStreamReuse` (formerly `Test21`)

### 2. Mapping and filtering pipelines (`streams.pipelines`)

- `LengthsStartingWithA` (formerly `Test24`)
- `FirstLongWord` (formerly `Test25`)

### 3. Primitive streams (`streams.primitivestreams`)

- `PrimitiveIntStreams` (formerly `Test18`)
- `ParseValidIntegers` (formerly `Test19`)
- `SortedStreamMiddleElement` (formerly `Test20`)
- `AverageOfEvenNumbers` (formerly `Test27`)
- `IntStreamProduct` (formerly `Test30`)

### 4. Optional values (`streams.optionals`)

- `OptionalEmailValidation` (formerly `Test28`)

### 5. Reduction (`streams.reduction`)

- `JoinNonBlankStrings` (formerly `Test29`)

### 6. Collectors (`streams.collectors`)

- `FilteringCollector` (formerly `Test23`)
- `DistinctLowercaseWordsByLength` (formerly `Test26`)
- `TotalLengthsByFirstCharacter` (formerly `Test31`)
- `LowercaseWordsByLength` (formerly `Test32`)
- `PartitionWordsByLength` (formerly `Test33`)
- `EmployeeSalaryReport` (formerly `Test37`; under `collectors.salaryreport`)
- `DepartmentEmployeeCollectors` (formerly `Test38`; under `collectors.departmentreport`)

### 7. Flattening nested data (`streams.flattening`)

- `FlattenUniqueTags` (formerly `Test34`)
- `WordFrequency` (formerly `Test35`)

### 8. Spliterators (`streams.spliterators`)

- `SpliteratorPartitioning` (formerly `Test22`)

## Exceptions and Localization (`exceptionslocalization`)

- `TryWithResourcesSuppressedExceptions` (formerly `Test23`)
- `DateNumberAndLocaleFormatting` (formerly `Test24`)
- `ResourceBundleLookup` (formerly `Test25`)

## Concurrency (`concurrency`)

Revise the concurrency drills in this order:

### 1. Thread fundamentals (`concurrency.fundamentals`)

- `StartingThreadsWithRunnable` (formerly `Test26`)
- `ThreadCreation` (formerly `Test41`)
- `ThreadStateObservation` (formerly `Test42`; under `fundamentals.threadstates`)
- `ThreadInterruption` (formerly `Test28`)
- `ThreadInterruptFlag` (formerly `Test51`)

### 2. Executors (`concurrency.executors`)

- `SingleThreadExecutor` (formerly `Test29`)
- `ExecutorLifecycle` (formerly `Test39`)
- `ScheduledFixedRateTask` (formerly `Test40`)
- `SingleThreadExecutorOrdering` (formerly `Test43`)
- `FixedThreadPoolOrdering` (formerly `Test44`)
- `SubmitVersusExecute` (formerly `Test45`)

### 3. Futures and callables (`concurrency.futures`)

- `CallableExceptionPropagation` (formerly `Test46`)
- `CallableFutureSum` (formerly `Test47`)
- `FutureTimeout` (formerly `Test48`)

### 4. Synchronization (`concurrency.synchronization`)

- `AtomicCounterSynchronization` (formerly `Test30`)
- `SynchronizedCounterDemo` (formerly `Test49`)
- `ReentrantLockTryLock` (formerly `Test52`)
- `CounterSynchronizationVariants` (formerly `Test54`)

### 5. Concurrent collections (`concurrency.concurrentcollections`)

- `CopyOnWriteArrayListIteration` (formerly `Test50`)

### 6. Coordination and queues (`concurrency.coordination`)

- `CyclicBarrierPlaceholder` (formerly `Test53`)
- `CustomBoundedQueueProducerConsumer` (formerly `Test55`)
- `BlockingQueueProducerConsumer` (formerly `Test56`)
- `TransferQueueProducerConsumer` (formerly `Test57`)

### 7. Parallel streams (`concurrency.parallelstreams`)

- `ParallelLengthReduction` (formerly `Test21`)
- `ParallelSquares` (formerly `Test22`)
- `ParallelStreamTiming` (formerly `Test31`)
- `SafeParallelCollection` (formerly `Test32`)
- `ParallelSquareCollection` (formerly `Test36`)

## I/O (`io`)

- `PathComponents` (formerly `Test33`)
- `ResolveAndCreatePath` (formerly `Test34`)
- `RelativePaths` (formerly `Test35`)
- `FileProperties` (formerly `Test36`)
- `FileReadWriteExercises` (formerly `Test37`)
- `BufferedBinaryFileCopy` (formerly `Test38`)
- `FileAndNioComparison` (formerly `Test58`)

The text and log files produced while practicing I/O live under `src/main/resources/study/ocp/io`. Java compiler argument artifacts live under `src/main/resources/study/ocp/compiler`.
