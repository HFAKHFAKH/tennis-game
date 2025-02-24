package com.tennis.game.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TennisGameServiceTest {

    private TennisGameService gameService;

    @BeforeEach
    void setUp() {
        gameService = Mockito.spy(new TennisGameService());
    }

    @Test
    void testGameWithPlayerAWinning() {
        gameService.play("ABABAA");
        Mockito.verify(gameService, Mockito.times(6)).printScore();
        Mockito.verify(gameService, Mockito.times(1)).logWins();
    }

    @Test
    void testGameWithPlayerBWinning() {
        gameService.play("ABABBB");
        Mockito.verify(gameService, Mockito.times(6)).printScore();
        Mockito.verify(gameService, Mockito.times(1)).logWins();
    }

    @Test
    void testGameWithDeuceAndAdvantage() {
       gameService.play("AAABBBA");
        Mockito.verify(gameService, Mockito.times(7)).printScore();
        Mockito.verify(gameService, Mockito.times(1)).logAdvantage();
    }

    @Test
    void testLongGameWithDeuce() {
        gameService.play("ABABABABABAB");
        Mockito.verify(gameService, Mockito.times(12)).printScore();
        Mockito.verify(gameService, Mockito.times(3)).logAdvantage();
    }

    @Test
    void testGameWithMultipleAdvantages() {
        gameService.play("ABBAABBAABBA");
        Mockito.verify(gameService, Mockito.times(12)).printScore();
        Mockito.verify(gameService, Mockito.times(3)).logAdvantage();
    }

    @Test
    void testGameWithPlayerAWinningAfterAdvantage() {
        gameService.play("AABBABABABAA");
        Mockito.verify(gameService, Mockito.times(12)).printScore();
        Mockito.verify(gameService, Mockito.times(3)).logAdvantage();
        Mockito.verify(gameService, Mockito.times(1)).logWins();
    }

    @Test
    void testGameWithPlayerBWinningAfterAdvantage() {
        gameService.play("AABBABABABBBA");
        Mockito.verify(gameService, Mockito.times(12)).printScore();
        Mockito.verify(gameService, Mockito.times(3)).logAdvantage();
        Mockito.verify(gameService, Mockito.times(1)).logWins();
    }
}
