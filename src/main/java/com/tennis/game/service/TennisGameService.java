package com.tennis.game.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TennisGameService {

    private static final Logger LOG = LoggerFactory.getLogger(TennisGameService.class);

    private final Map<Character, Integer> scores;
    private Character advantage;
    private Character winner;

    public TennisGameService() {
        scores = new HashMap<>();
        scores.put('A', 0);
        scores.put('B', 0);
        advantage = null;
        winner = null;
    }

    public void play(String input) {
        for (char player : input.toCharArray()) {
            if (winner != null) {
                break;
            }
            pointWonBy(player);
            printScore();
        }
    }

    private void pointWonBy(char player) {
        if (winner != null) return;

        char opponent = opponent(player);

        if (scores.get(player) < 30) {
            scores.put(player, scores.get(player) + 15);
        } else if (scores.get(player) == 30) {
            scores.put(player, 40);
        } else if (scores.get(player) == 40) {
            handleFortyScore(player, opponent);
        } else if (advantage != null) {
            handleAdvantageScore(player);
        }
    }

    private void handleFortyScore(char player, char opponent) {
        if (scores.get(opponent) == 40) {
            if (advantage == null) {
                advantage = player;
            } else if (advantage == player) {
                winner = player;
            } else {
                advantage = null; // Back to deuce
            }
        } else {
            winner = player;
        }
    }

    private void handleAdvantageScore(char player) {
        if (advantage == player) {
            winner = player;
        } else {
            advantage = null; // Advantage lost
        }
    }


    private char opponent(char player) {
        return player == 'A' ? 'B' : 'A';
    }

    private String getPlayerName(char player) {
        return "Player " + player;
    }

    public void printScore() {
        if (winner != null) {
            logWins();
        } else if (advantage != null) {
            logAdvantage();
        } else {
            logScore();
        }
    }

    public void logScore() {
        LOG.info("{} : {} / {} : {}", getPlayerName('A'), formatScore(scores.get('A')), getPlayerName('B'), formatScore(scores.get('B')));
    }

    public void logWins() {
        LOG.info("{} wins the game", getPlayerName(winner));
    }

    public void logAdvantage() {
        LOG.info("{} : Advantage", getPlayerName(advantage));
    }

    private String formatScore(int score) {
        if (score == 0 || score == 15 || score == 30) {
            return String.valueOf(score);
        } else if (score == 40) {
            return "40";
        } else {
            return "";
        }
    }
}
