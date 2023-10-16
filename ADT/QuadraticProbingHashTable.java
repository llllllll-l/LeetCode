package ADT;

public class QuadraticProbingHashTable {
  private static final int INITIAL_CAPACITY = 11;
  private static final double LOAD_FACTOR_THRESHOLD = 0.7;

  private int collisions = 0;
  private int offset1 = 0;
  private int offset2 = 0;
  private Entry[] table;
  private int size;

  public QuadraticProbingHashTable() {
    table = new Entry[INITIAL_CAPACITY];
    size = 0;
  }

  public QuadraticProbingHashTable(int SPECIFIED_CAPACITY) {
    table = new Entry[SPECIFIED_CAPACITY];
    size = 0;
  }

  private static class Entry {
    String key;
    Vehicle value;
    boolean isDeleted;

    Entry(String key, Vehicle value) {
      this.key = key;
      this.value = value;
      this.isDeleted = false;
    }
  }

  private int hash(String key) {
    int hash = 0;
    for (char character : key.toCharArray()) {
      hash += character;
    }
    return hash % table.length;
  }

  private int quadraticProbe(int hash, int i) {
    offset1++;
    if (offset1 > offset2) {
      offset2 = offset1;
    }
    return (hash + i * i) % table.length;
  }

  public void insert(String key, Vehicle value) {
    if (size >= LOAD_FACTOR_THRESHOLD * table.length) {
      resizeTable();
    }

    int hash = hash(key);
    int i = 0;
    int index = hash;

    while (
      table[index] != null &&
      !table[index].isDeleted &&
      !table[index].key.equals(key)
    ) {
      i++;
      collisions++;
      index = quadraticProbe(hash, i);
    }

    if (table[index] == null || table[index].isDeleted) {
      table[index] = new Entry(key, value);
      size++;
      offset1 = 0;
    } else { // key already exists.
      table[index].value = value;
      offset1 = 0;
    }
  }

  public boolean contains(String key) {
    int hash = hash(key);
    int i = 0;
    int index = hash;

    while (
      table[index] != null &&
      (table[index].isDeleted || !table[index].key.equals(key))
    ) {
      i++;
      index = quadraticProbe(hash, i);
    }

    return table[index] != null && !table[index].isDeleted;
  }

  public Vehicle find(String key) {
    int hash = hash(key);
    int i = 0;
    int index = hash;

    while (
      table[index] != null &&
      (table[index].isDeleted || !table[index].key.equals(key))
    ) {
      i++;
      index = quadraticProbe(hash, i);
    }

    if (table[index] != null && !table[index].isDeleted) {
      return table[index].value;
    } else {
      return null;
    }
  }

  public void delete(String key) {
    int hash = hash(key);
    int i = 0;
    int index = hash;

    while (
      table[index] != null &&
      (table[index].isDeleted || !table[index].key.equals(key))
    ) {
      i++;
      index = quadraticProbe(hash, i);
    }

    if (table[index] != null && !table[index].isDeleted) {
      table[index].isDeleted = true;
      size--;
    }
  }

  public void resizeTable() {
    int new_capacity = table.length * 2;
    Entry[] new_table = new Entry[new_capacity];

    for (Entry entry : table) {
      if (entry != null && !entry.isDeleted) {
        int hash = hash(entry.key);
        int i = 0;
        int index = hash;

        while (new_table[index] != null) {
          i++;
          index = quadraticProbe(hash, i);
        }

        new_table[index] = entry;
      }
    }
    table = new_table;
  }

  public int getCollisions() {
    return collisions;
  }

  public int getOffset() {
    return offset2;
  }

  public void displayHashTable() {
    System.out.println("Table:");
    for (int i = 0; i < table.length; i++) {
      if (table[i] != null) {
        if (table[i].isDeleted == true) {
          System.out.println("| " + i + "\t | -- |");
        } else {
          System.out.println(
            "| " + i + "\t | " + table[i].value.getLicensePlateNumber() + " |"
          );
        }
      } else {
        System.out.println("| " + i + "\t | -- |");
      }
    }
    System.out.println();
  }
}