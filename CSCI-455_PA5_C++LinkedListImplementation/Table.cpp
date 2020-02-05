// Name:       Chong Chen
// USC NetID:  chongche
// CSCI 455 PA5
// Fall 2019

// Table.cpp  Table class implementation

#include "Table.h"

#include <cassert>
#include <iostream>
#include <string>

using namespace std;

// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"

//*************************************************************************

// create an empty table, i.e., one where numEntries() is 0
// (Underlying hash table is HASH_SIZE.)
Table::Table() {
    hashSize = HASH_SIZE;
    buckets = new ListType[hashSize];
    for (int i = 0; i < hashSize; i++) {
        buckets[i] = NULL;
    }
}

// create an empty table, i.e., one where numEntries() is 0
// such that the underlying hash table is hSize
Table::Table(unsigned int hSize) {
    hashSize = hSize;
    buckets = new ListType[hashSize];
    for (int i = 0; i < hashSize; i++) {
        buckets[i] = NULL;
    }
}

// insert a new pair into the table
// return false iff this key was already present
//         (and no change made to table)
bool Table::insert(const string &key, int value) {
    ListType &node = buckets[hashCode(key)];
    return listAppend(node, key, value);
}

// returns the address of the value that goes with this key
// or NULL if key is not present.
//   Thus, this method can be used to either lookup the value or
//   update the value that goes with this key.
int *Table::lookup(const string &key) {
    ListType &node = buckets[hashCode(key)];
    return listLookUp(node, key);
}

// remove a pair given its key
// false iff key wasn't present
bool Table::remove(const string &key) {
    ListType &node = buckets[hashCode(key)];
    return listRemove(node, key);
}

// Return the number of entries in the table
int Table::numEntries() const {
    unsigned int count = 0;
    for (int i = 0; i < hashSize; i++) {
        count += currentEntries(buckets[i]);
    }
    return count;
}

// print out all the entries in the table, one per line.
// Sample output:
//   zhou 283
//   sam 84
//   babs 99
void Table::printAll() const {
    for (int i = 0; i < hashSize; i++) {
        listPrint(buckets[i]);
    }
}

// hashStats: for diagnostic purposes only
// prints out info to let us know if we're getting a good distribution
// of values in the hash table.
// Sample output from this function
//   number of buckets: 997
//   number of entries: 10
//   number of non-empty buckets: 9
//   longest chain: 2
void Table::hashStats(ostream &out) const {
    cout << "number of buckets: " << hashSize << endl;
    cout << "number of entries: " << numEntries() << endl;
    cout << "number of non-empty buckets: " << numNonEmptybuckets() << endl;
    cout << "longest chain: " << numLongestChain() << endl;
}

// add definitions for your private methods here

// Return the number of non-empty buckets in the hash table.
int Table::numNonEmptybuckets() const {
    unsigned int count = 0;
    for (int i = 0; i < hashSize; i++) {
        if (buckets[i] != NULL) {
            count++;
        }
    }
    return count;
}

// Return the longest chain in the hash table.
int Table::numLongestChain() const {
    unsigned int longest = 0;
    for (int i = 0; i < hashSize; i++) {
        if (currentEntries(buckets[i]) > longest) {
            longest = currentEntries(buckets[i]);
        };
    }
    return longest;
}