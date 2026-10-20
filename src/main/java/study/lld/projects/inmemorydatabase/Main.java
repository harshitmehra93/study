package study.lld.projects.inmemorydatabase;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        /*
        in memory database
        - create table with schema
        - create index in table
        - insert row into table
        - get rows from table with column filters
        - A table will be a collection of TableRow
        - A table will have indexes
        - An index is a sorted collection of rows. The sort key will be one or more columns
        - every row added to the table will be added to a default index with primary key as the sort key and then
            it will be added to all indexes.
        - selectWithIndex(index,a1,a2,a3...,List<filters>)
        - select(List<Filter>)
        - insert(Row)
        - createTable(List<Attribute> attributes, String name, String primaryKey)
            - Type: String, Int, Double
        - createIndex(List<Attribute> attributes, String indexName)


        Table{
            int id
            Map<String, Index> indexes
            List<Attribute<Type>> schema
            List<Row> rows

            List<Row> select(List<Filter> conditions)
            List<Row> selectWithIndex(List<Filter> conditions, String indexName,Value... values)
            void insert(Row row)
            createIndex(List<String> columns, String indexName)
            List<Row> searchIndex(Value... values)
        }

        Row{
            int id
            List<Value>
        }
        Attribute<DataType>{
            int id
            String name,
            DataType type;
            Object value;
            Constraint NonNull,Unique,Nullable
        }
        Value{
            int id;
            int columnId;
            Object value;
        }
        Type{
            String,Integer
        }

         */
        TableService tableService = new TableService();
        DatabaseService db = new DatabaseService(tableService);

        Header id = new Header("id", Constraint.NON_NULL, DataType.INT);
        Header name = new Header("name", Constraint.NON_NULL, DataType.STRING);
        Header city = new Header("city", Constraint.NON_NULL, DataType.STRING);
        db.createTable("test", List.of(id, name, city), id);

        Value a = new Value(id, 1);
        Value b = new Value(name, "Harshit");
        Value c = new Value(name, "ghaziabad");
        Row r1 = new Row(List.of(a, b, c));

        Value d = new Value(id, 2);
        Value e = new Value(name, "Smridhi");
        Value f = new Value(name, "ghaziabad");
        Row r2 = new Row(List.of(d, e, f));

        db.insert("test", r1);
        db.insert("test", r2);

        db.select("test").forEach(System.out::println);

        db.createIndexOnTable("test", "cityIndex", List.of(city));
    }
}

class Table {
    private static AtomicInteger counter = new AtomicInteger(0);
    int id;
    Map<String, Index> indexes = new HashMap<>();
    List<Header> schema = new ArrayList<>();
    Map<IndexKey, Row> rows = new HashMap<>();
    private final Header primaryKey;
    private final String name;

    Table(Header primaryKey, String name, List<Header> schema) {
        this.id = counter.getAndIncrement();
        this.primaryKey = primaryKey;
        this.name = name;
        this.schema = schema;
    }

    List<Row> select() {
        return new ArrayList<>(rows.values());
    }

    List<Row> select(List<Filter> conditions) {
        return null;
    }

    List<Row> selectWithIndex(List<Filter> conditions, String indexName, Value... values) {
        return null;
    }

    void insert(Row row) {
        validateConstraints(row);
        rows.put(row.getKey(List.of(primaryKey)), row);
        for (Index index : indexes.values()) {
            index.addRow(row);
        }
    }

    private void validateConstraints(Row row) {
        for (Value value : row.values) {}
    }

    void createIndex(List<Header> headers, String indexName) {
        // get table write lock
        if (indexes.containsKey(indexName))
            throw new RuntimeException("index with same name exists");
        indexes.put(indexName, new Index(indexName, headers));
        reindex(indexName);
        // release table write lock
    }

    private void reindex(String indexName) {
        if (!indexes.containsKey(indexName)) throw new RuntimeException("index not found");

        // get table write lock
        Index index = indexes.get(indexName);
        index.rows = new TreeMap<>();
        rows.values().forEach(index::addRow);
        // release table write lock
    }

    List<Row> searchIndex(Value... values) {
        return null;
    }
}

class Row {
    private static AtomicInteger counter = new AtomicInteger(0);
    int id;
    List<Value> values;

    Row(List<Value> values) {
        id = counter.getAndIncrement();
        this.values = values;
    }

    public IndexKey getKey(List<Header> indexedHeaders) {
        IndexKey indexedKey = new IndexKey();
        for (Header header : indexedHeaders) {
            Optional<Value> value =
                    values.stream().filter(v -> v.header.equals(header)).findFirst();
            value.ifPresent(value1 -> indexedKey.values.add(value1));
        }
        return indexedKey;
    }

    @Override
    public String toString() {
        String out = "RowId=%d ".formatted(id);
        StringBuilder sb = new StringBuilder(out);
        for (Value value : values) {
            sb.append(value.header.name + "=" + value.value + " ");
        }
        return sb.toString();
    }
}

class IndexKey implements Comparable<IndexKey> {
    List<Value> values;

    IndexKey() {
        values = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof IndexKey key) {
            for (int i = 0; i < values.size(); i++) {
                if (!values.get(i).equals(key.values.get(i))) return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return values.hashCode();
    }

    @Override
    public int compareTo(IndexKey o) {
        int result = 0;
        for (int i = 0; i < values.size(); i++) {
            result =
                    CharSequence.compare(
                            values.get(i).value.toString(), o.values.get(i).toString());
            if (result != 0) {
                return result;
            }
        }
        return result;
    }
}

class Value {
    int id;
    public final Header header;
    public final Object value;

    Value(Header header, Object value) {
        this.header = header;
        this.value = value;
    }

    @Override
    public String toString() {
        switch (header.type) {
            case STRING -> {
                return ((String) value);
            }
            case INT -> {
                return ((Integer) value).toString();
            }
        }
        throw new RuntimeException("invalid type");
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Value v) {
            return Objects.equals(v.id, id);
        }
        return false;
    }
}

class Header {
    private static AtomicInteger counter = new AtomicInteger(0);
    int id;
    String name;
    Constraint constraint;
    DataType type;

    Header(String name, Constraint constraint, DataType type) {
        this.id = counter.getAndIncrement();
        this.name = name;
        this.constraint = constraint;
        this.type = type;
    }
}

enum Constraint {
    NULLABLE,
    NON_NULL,
    UNIQUE
}

interface Filter {
    boolean isEqual(Value value);
}

enum DataType {
    STRING,
    INT
}

class Index {
    private static AtomicInteger counter = new AtomicInteger(0);
    private final int id;
    private final String name;
    private final List<Header> indexedHeaders;
    Map<IndexKey, Set<Row>> rows;

    public Index(String indexName, List<Header> headers) {
        this.id = counter.getAndIncrement();
        name = indexName;
        indexedHeaders = headers;
        rows = new HashMap<>();
    }

    public void addRow(Row row) {
        IndexKey key = row.getKey(indexedHeaders);
        Set<Row> set = rows.getOrDefault(key, new HashSet<>());
        set.add(row);
        rows.put(key, set);
    }
}

class DatabaseService {
    private final TableService tableService;

    DatabaseService(TableService tableService) {
        this.tableService = tableService;
    }

    void createTable(String tableName, List<Header> schema, Header primaryKey) {
        tableService.createTable(tableName, schema, primaryKey);
    }

    List<Header> describeTable(String tableName) {
        return tableService.getSchema(tableName);
    }

    void createIndexOnTable(String tableName, String indexName, List<Header> headers) {
        Table table = tableService.getTable(tableName);
        table.createIndex(headers, indexName);
    }

    void insert(String tableName, Row row) {
        Table table = tableService.getTable(tableName);
        table.insert(row);
    }

    List<Row> select(String tableName) {
        return tableService.getTable(tableName).select();
    }
}

class TableService {
    Map<String, Table> tables = new HashMap<>();

    public void createTable(String tableName, List<Header> schema, Header primaryKey) {
        Table table = new Table(primaryKey, tableName, schema);
        tables.put(tableName, table);
    }

    public List<Header> getSchema(String tableName) {
        return getTable(tableName).schema;
    }

    Table getTable(String name) {
        return tables.get(name);
    }
}
