package org.springaop.chapter.five.cache;

public class DummyClass {

    public String getFooFighters() {
        return "My hero!";
    }

    public String getHives(String year) {
        if (year.equals("2004")) {
            return "Walk idiot walk !";
        } else {
            return "Abra Cadaver";
        }
    }

    public String getDandyWarhols() {
        return "Ride";
    }

    public void exit() {
        System.out.println("The end.");
    }
}
