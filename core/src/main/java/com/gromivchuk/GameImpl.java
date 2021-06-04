package com.gromivchuk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class GameImpl implements Game{

    //========constants===============
    private static final Logger logger = LoggerFactory.getLogger(GameImpl.class);

    //=========fields==============
    @Autowired
    private NumberGenerator numberGenerator;
    private int guessCount = 10;
    private int number;
    private int guess;
    private int smallest;
    private int biggest;
    private int remainGuesses;
    private boolean validNumberRange = true;

    //=================Constructor - Used for Conctructor based dependency inyection========
//    public GameImpl(NumberGenerator numberGenerator) {
//        this.numberGenerator = numberGenerator;
//    }
    //=====init======
    @PostConstruct
    @Override
    public void reset() {
        smallest=0;
        guess=0;
        remainGuesses=guessCount;
        biggest = numberGenerator.getMaxNumber();
        number= numberGenerator.next();
        logger.debug("the number is {}", number);
    }

    @PreDestroy
    public void preDestroy(){
        logger.info("in Game PreDestroy");
    }

    //=========public methods===============
//    public void setNumberGenerator(NumberGenerator numberGenerator){
//        this.numberGenerator = numberGenerator;
//    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getGuess() {
        return guess;
    }

    @Override
    public void setGuess(int guess) {
        this.guess = guess;
    }

    @Override
    public int getSmallest() {
        return smallest;
    }

    @Override
    public int getBiggest() {
        return biggest;
    }

    @Override
    public int getRemaining() {
        return remainGuesses;
    }

    @Override
    public void check() {
        checkValidNumberRange();
        if(guess>number){
            biggest= guess -1;
        }
        if(guess<number){
            smallest = guess + 1;
        }
        remainGuesses--;
    }

    @Override
    public boolean isValidNumberRange() {
        return validNumberRange;
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainGuesses<=0 ;
    }

    //==========private methods============
    private void checkValidNumberRange(){
        validNumberRange = (guess<=biggest)&&(guess>=smallest);
    }
}
