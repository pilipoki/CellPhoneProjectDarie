package darie.mitulescu;

import java.util.ArrayList;
import java.util.Scanner;

public class MobilePhone {
    private ArrayList<Contacts> contacts;
    private String myNumber;

    public ArrayList<Contacts> getContacts() {
        return contacts;
    }

    public String getMyNumber() {
        return myNumber;
    }

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.contacts = new ArrayList<Contacts>();
    }

    public void addContact(String name, String phoneNumber) {
        Contacts contact = new Contacts(name, phoneNumber);
            this.contacts.add(contact);
            System.out.println("Contact was added.");
    }

    public void removeContact(String name){
        for (int j = 0; j < contacts.size(); j++) {
            if (contacts.get(j).getName().equals(name)) {
                contacts.remove(j);
                System.out.println("Contact was removed.");
            }
        }

    }

    public void printListOfContacts () {
        System.out.println("You have " + contacts.size() + " contacts on this phone : ");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i+1) + ". " + contacts.get(i).getName() + ", " + contacts.get(i).getPhoneNumber());
        }
    }

    public int findContact(String name) {
        for (int i = 0; i < contacts.size(); i++) {
            if (name.toLowerCase().equals(contacts.get(i).getName().toLowerCase())) {
                // if person found, return the index in that arraylist
                return i;
            }
        }
        //contact not found
        return -1;
    }

}
