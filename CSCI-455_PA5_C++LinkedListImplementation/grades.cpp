// Name:       Chong Chen
// USC NetID:  chongche
// CSCI 455 PA5
// Fall 2019

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

// Function delcarations

// Set the parameters by changing the value of name and return the value of
// score
// @param cmd  the command input
// @param name the name input
// return the value of score input
int setParams(string cmd, string &name);

// Execute the commands passed and print out the execution information.
// @param cmd     the command input
// @param name    the name input
// @param score   the score input
// @param grades  the hashtable to operate on
void executeCmd(string cmd, string &name, int score, Table *grades);

// Insert the name and score pair into the hash table, print out the execution
// information
// @param name    the name to insert
// @param score   the score to insert
// @param grades  the hashtable to operate on
void insert(string &name, int score, Table *grades);

// Change the score belong to the name in the hash table, print out the
// execution information
// @param name    the name to change score
// @param newScore   the new score to repalce the old one
// @param grades  the hashtable to operate on
void change(string &name, int newScore, Table *grades);

// lookup the name and score pair into the hash table, print out the execution
// information
// @param name    the name to lookup
void lookUp(string &name, Table *grades);

// Print out a brief summary of valid commands
void printHelp();

int main(int argc, char *argv[]) {
    // gets the hash table size from the command line

    int hashSize = Table::HASH_SIZE;

    Table *grades;  // Table is dynamically allocated below, so we can call
    // different constructors depending on input from the user.

    if (argc > 1) {
        hashSize = atoi(argv[1]);  // atoi converts c-string to int

        if (hashSize < 1) {
            cout << "Command line argument (hashSize) must be a positive number"
                 << endl;
            return 1;
        }

        grades = new Table(hashSize);

    } else {  // no command line args given -- use default table size
        grades = new Table();
    }

    grades->hashStats(cout);

    // add more code here
    // Reminder: use -> when calling Table methods, since grades is type Table*

    string cmd = "";
    string name = "";

    bool isQuit = false;

    while (!isQuit) {
        cout << "cmd> ";
        cin >> cmd;
        if (cmd == "quit") {
            isQuit = true;
        } else {
            int score = setParams(cmd, name);
            executeCmd(cmd, name, score, grades);
        }
    }

    return 0;
}

// Set the parameters by changing the value of name and return the value of
// score
// @param cmd  the command input
// @param name the name input
// return the value of score input
int setParams(string cmd, string &name) {
    int score = -1;

    if (cmd == "lookup" || cmd == "remove") {
        cin >> name;
    }

    else if (cmd == "insert" || cmd == "change") {
        cin >> name;
        cin >> score;
    }

    return score;
}

// Execute the commands passed and print out the execution information.
// @param cmd     the command input
// @param name    the name input
// @param score   the score input
// @param grades  the hashtable to operate on
void executeCmd(string cmd, string &name, int score, Table *grades) {
    if (cmd == "insert") {
        insert(name, score, grades);
    }

    else if (cmd == "change") {
        change(name, score, grades);
    }

    else if (cmd == "lookup") {
        lookUp(name, grades);
    }

    else if (cmd == "remove") {
        grades->remove(name);
    }

    else if (cmd == "print") {
        grades->printAll();
    }

    else if (cmd == "size") {
        cout << grades->numEntries() << endl;
    }

    else if (cmd == "stats") {
        grades->hashStats(cout);
    }

    else if (cmd == "help") {
        printHelp();
    }

    else {
        cout << "ERROR: invalid command. Please input \"help\" to get the "
                "brief command summary."
             << endl;
    }
}

// Insert the name and score pair into the hash table, print out the execution
// information
// @param name    the name to insert
// @param score   the score to insert
// @param grades  the hashtable to operate on
void insert(string &name, int score, Table *grades) {
    if (grades->lookup(name) == NULL) {
        grades->insert(name, score);
    }

    else {
        cout << "Insteration Failed: the name already exists." << endl;
    }
}

// Change the score belong to the name in the hash table, print out the
// execution information
// @param name    the name to change score
// @param newScore   the new score to repalce the old one
// @param grades  the hashtable to operate on
void change(string &name, int newScore, Table *grades) {
    if (grades->lookup(name) == NULL) {
        cout << "Score-Change Failed: the name \"" << name
             << "\" does not exist." << endl;
    }

    else {
        *(grades->lookup(name)) = newScore;
    }
}

// lookup the name and score pair into the hash table, print out the execution
// information
// @param name    the name to lookup
void lookUp(string &name, Table *grades) {
    if (grades->lookup(name) == NULL) {
        cout << "Lookup Failed: the name \"" << name << "\" does not exist."
             << endl;
    }

    else {
        cout << "The score of " << name << " is: " << *grades->lookup(name)
             << endl;
    }
}

// Print out a brief summary of valid commands
void printHelp() {
    cout << "The valid command are as follows : " << endl;
    cout << "(You do not need to use [] to pass parameters, just type-in the "
            "values you want.)"
         << endl;
    cout << "insert [name] [score]" << endl
         << "\t Insert this name and score in the grade table." << endl;
    cout << "change [name] [newscore]" << endl
         << "\t Change the score for name." << endl;
    cout << "lookup [name]" << endl
         << "\t Lookup the name, and print out his or her score." << endl;
    cout << "remove [name]" << endl << "\t Remove this student." << endl;
    cout << "print" << endl
         << "\t Prints out all names and scores in the table." << endl;
    cout << "size" << endl
         << "\t Prints out the number of entries in the table." << endl;
    cout << "stats" << endl
         << "\t Prints out statistics about the hash table at this point."
         << endl;
    cout << "help" << endl << "\t Prints out a brief command summary." << endl;
    cout << "quit" << endl << "\t Exits the program." << endl;
}