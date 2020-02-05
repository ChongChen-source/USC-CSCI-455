/*  Name:      Chong Chen
 *  USC NetID: chongche
 *  CS 455 Fall 2019
 *
 *  See ecListFuncs.h for specification of each function.
 */

// for NULL
#include <cstdlib>

// in case you want to use assert statements
#include <cassert>

#include "ecListFuncs.h"

using namespace std;

/*
 * longestRun
 *    A run is several adjacent nodes that have the same value.
 *
 * PRE: list is a well-formed list with at least one node
 *
 * After the function runs:
 *   maxRunVal will contain the value that occurred in the longest run
 *   maxRunLen will contain the length of that run
 * If there are multiple sequences of the max length, it will return the value
 * for the first one (see Examples 1 and 3 below). maxRunVal   maxRunLen
 *  Example1: list = (3 0 -10)              3           1
 *  Example2: list = (3 7 5 0 0 9)          0           2
 *  Example3: list = (5 5 5 2 2 2 9 9 9)    5           3
 *  Example3: list = (3)                    3           1
 *  Example4: list = (7 7 2 2 2 2 4 4 4)    2           4
 *  Example5: list = (7 7 3 3 7 7 7)        7           3
 */
void longestRun(ListType list, int &maxRunVal, int &maxRunLen) {
  Node *node = list;

  // record the value and length of the first run in the list.
  // temporarily mark the first run as the max run.
  maxRunVal = node->data;
  maxRunLen = 0;

  while (node->data == maxRunVal) {
    maxRunLen++;
    if (node->next != NULL) {
      node = node->next;
    } else {
      break;
    }
  }

  // update the max run.
  while (node->next != NULL) {
    // record the value and length of the next temporary run in the list.
    int tempRunVal = node->data;
    int tempRunLen = 0;
    while (node->data == tempRunVal) {
      tempRunLen++;
      if (node->next != NULL) {
        node = node->next;
      } else {
        break;
      }
    }
    // update the max run if it is shorter than the just finished run.
    if (tempRunLen > maxRunLen) {
      maxRunLen = tempRunLen;
      maxRunVal = tempRunVal;
    }
  }
}

/*
 * removeMultiplesOf3
 *
 * PRE: list is a well-formed list.
 *
 * removes all all of the multiples of 3 from the list
 *
 * Examples:
 *  list before call  list after call
 *    ()                ()
 *    (6 2 3 3 7 12)    (2 7)
 *    (3 6 9 12)        ()
 *    (1 5 1 7)         (1 5 1 7)
 */
void removeMultiplesOf3(ListType &list) {
  Node *node = list;
  while (node != NULL && node->data % 3 == 0) {
    list = node->next;
    delete node;
    node = list;
  }

  while (node != NULL) {
    Node *prev = node;
    node = node->next;
    while (node != NULL && node->data % 3 == 0) {
      prev->next = node->next;
      delete node;
      node = prev->next;
    }
  }
}

/*
 * insertMiddle
 *
 * PRE: list is a well-formed list.
 *
 * inserts midVal at the middle of the list
 * If the list has an odd number of values beforehand, it will go just left of
 * the middle.
 *
 * Examples:
 *  list before call  midVal    list after call
 *    (1 1 1 1 1 1)   6         (1 1 1 6 1 1 1)
 *    (1 1 1 1 1)     6         (1 1 6 1 1 1)
 *    ()              32        (32)
 *    (10)            5         (5 10)
 *    (1 17)          85        (1 85 17)
 */
void insertMiddle(ListType &list, int midVal) {
  Node *node = list;

  int listLength = 0;
  while (node != NULL) {
    listLength++;
    node = node->next;
  }

  node = list;
  Node *newNode = new Node(midVal);

  if (listLength == 0) {
    list = newNode;
    return;
  }

  if (listLength == 1) {
    newNode->next = node;
    list = newNode;
    return;
  }

  for (int i = 0; i < listLength / 2 - 1; i++) {
    node = node->next;
  }
  newNode->next = node->next;
  node->next = newNode;
}

/*
 * merge
 *
 * PRE: list1 and list2 are well-formed lists such that each one is
 *      a list of unique values in increasing order
 *
 * returns a list of unique values in increasing order that has all the values
 *       of the original lists. list1 and list2 are undefined after this
 * operation.
 *
 * Note1: this is required to be a destructive merge: the new list will be made
 * up of nodes of the original list, so this function will not call new or
 * delete, except for the case mentioned in Note2.
 *
 * Note2: If list1 and list2 have any values in common, only one of these will
 * end up in the result list and you must reclaim memory for the other one. (see
 * ***'d examples below)
 *
 * Note3: your code must use the O(n+m) merge algorithm (discussed in lecture on
 * 10/1)
 *
 * Examples:
 *   list1 before call      list2 before call     merge(list1, list2)
 *
 *   (2 4 6)               (1 3 5)                (1 2 3 4 5 6)
 *   (1 2 3 4 5)           (6 7 8)                (1 2 3 4 5 6 7 8)
 *   (2 4 6 8 10 12)       (3 6 9 12)             (2 3 4 6 8 9 10 12)     ***
 *   (3 4 5)               (3 4 5)                (3 4 5)                 ***
 *   (1 2 3 4 5)           ()                     (1 2 3 4 5)
 *   ()                    ()                     ()
 *   ()                    (1 2 3 4 5)            (1 2 3 4 5)
 */
ListType merge(ListType list1, ListType list2) {
  Node *node1 = list1;
  Node *node2 = list2;

  if (node1 == NULL) {
    return node2;
  }
  if (node2 == NULL) {
    return node1;
  }

  if (node1->data < node2->data) {
    node1->next = merge(node1->next, node2);
    return node1;
  }

  else if (node1->data > node2->data) {
    node2->next = merge(node2->next, node1);
    return node2;
  }

  else {
    Node *p = node2;
    node2 = node2->next;
    delete p;
    node1->next = merge(node1->next, node2);
    return node1;
  }
}