package de.axelspringer.ideas.flipdots;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm:ss");

    public String getTimeString() {
        return FORMAT.format(new Date());
    }
}
