// Name:       Chong Chen
// USC NetID:  chongche
// CSCI 455 PA5
// Fall 2019

#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
    key = theKey;
    value = theValue;
    next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
    key = theKey;
    value = theValue;
    next = n;
}

//*************************************************************************
// put the function definitions for your list functions below

// Find out if the list containing a entry with the target key.
// @param list    the list to operate on
// @param target  the key to look up
// @return true if the list has such a entry
bool contains(ListType &list, const string &target) {
    for (ListType node = list; node != NULL; node = node->next) {
        if (node->key == target) {
            // return true if the list contains such a entry with the target
            // key.
            return true;
        }
    }
    // return false if the list does not contain such a entry with the target
    // key.
    return false;
}

// Remove the hash entry containing the target key.
// @param list    the list to operate on
// @param target  the key of the entry to be appended
// @param value   the value of the entry to be appended
// @return true if the appendment is successful
bool listAppend(ListType &list, const string &target, int value) {
    if (contains(list, target)) {
        return false;
    }

    ListType node = list;
    ListType newNode = new Node(target, value);

    if (node == NULL) {
        newNode->next = NULL;
        list = newNode;
        return true;
    }

    while (node->next != NULL) {
        node = node->next;
    }
    // After the while-loop, the node points to the last element in the list.

    node->next = newNode;  // append the newNode to the list
    newNode->next = NULL;
    return true;
}

// look up the value of the target key
// @param list    the list to operate on
// @param target  the key of the entry to be looked up
// @return the address of the value. If the target was not found, return null.
int *listLookUp(ListType &list, const string &target) {
    for (ListType node = list; node != NULL; node = node->next) {
        if (node->key == target) {
            return &(node->value);
        }
    }
    return NULL;
}

// Remove the hash entry containing the target key.
// @param list    the list to operate on
// @param target  the key of the entry to be removed
// @return true if the removement is successful
bool listRemove(ListType &list, const string &target) {
    // If the list is null, no such a entry to be removed
    if (list == NULL) {
        return false;
    }

    ListType node = list;

    // If it is the first entry to be removed
    if (node->key == target) {
        ListType temp = list;
        list = list->next;
        delete temp;
        return true;
    }

    for (ListType preNode = NULL; node != NULL; node = node->next) {
        if (node->key == target) {
            preNode->next = node->next;
            delete node;
            return true;
        }
        preNode = node;
    }

    // If such a entry has not been found, return false.
    return false;
}

// Update the value of the entry containing the target key.
// @param list       the list to operate on
// @param target     the key of the entry to be updated
// @param newValue   the new value of the entry to be updated
// @return true if the update is successful
bool listUpdate(ListType &list, const string &target, int newValue) {
    if (!contains(list, target)) {
        return false;
    }

    else {
        *listLookUp(list, target) = newValue;
        return true;
    }
}

// Print out the whole list, one entry a line.
// @param list    the list to operate on
void listPrint(ListType &list) {
    for (ListType node = list; node != NULL; node = node->next) {
        cout << node->key << " " << node->value << endl;
    }
}

// Return the number of current valid entries in the list.
// @param list    the list to operate on
int currentEntries(ListType &list) {
    int count = 0;
    for (ListType node = list; node != NULL; node = node->next) {
        count++;
    }
    return count;
}