package com.gromivchuk;

public interface Game {
    int getNumber();
    int getGuess();
    void setGuess(int guess);
    int getSmallest();
    int getBiggest();
    int getRemaining();
    void reset();
    void check();
    boolean isValidNumberRange();
    boolean isGameWon();
    boolean isGameLost();

}
