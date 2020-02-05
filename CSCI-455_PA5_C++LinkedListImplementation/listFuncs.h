// Name: Chong Chen
// USC NetID: chongche
// CSCI 455 PA5
// Fall 2019

//*************************************************************************
// Node class definition
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in *header* files.
// Thus here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H

struct Node {
    std::string key;
    int value;

    Node *next;

    Node(const std::string &theKey, int theValue);

    Node(const std::string &theKey, int theValue, Node *n);
};

typedef Node *ListType;

//*************************************************************************
// add function headers (aka, function prototypes) for your functions
// that operate on a list here (i.e., each includes a parameter of type
// ListType or ListType&).  No function definitions go in this file.

// Find out if the list containing a entry with the target key.
// @param list    the list to operate on
// @param target  the key to look up
// @return true if the list has such a entry
bool contains(ListType &list, const std::string &target);

// Remove the entry containing the target key.
// @param list    the list to operate on
// @param target  the key of the entry to be appended
// @param value   the value of the entry to be appended
// @return true if the appendment is successful
bool listAppend(ListType &list, const std::string &target, int value);

// look up the value of the target key
// @param list    the list to operate on
// @param target  the key of the entry to be looked up
// @return the address of the value. If the target was not found, return null.
int *listLookUp(ListType &list, const std::string &target);

// Remove the entry containing the target key.
// @param list    the list to operate on
// @param target  the key of the entry to be removed
// @return true if the removement is successful
bool listRemove(ListType &list, const std::string &target);

// Update the value of the entry containing the target key.
// @param list       the list to operate on
// @param target     the key of the entry to be updated
// @param newValue   the new value of the entry to be updated
// @return true if the update is successful
bool listUpdate(ListType &list, const std::string &target, int newValue);

// Print out the whole list, one entry a line.
// @param list    the list to operate on
void listPrint(ListType &list);

// Return the number of current valid entries in the list.
// @param list    the list to operate on
int currentEntries(ListType &list);

// keep the following line at the end of the file
#endif

