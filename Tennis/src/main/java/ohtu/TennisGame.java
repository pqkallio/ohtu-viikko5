package ohtu;

public class TennisGame {
    
    private int player1Score;
    private int player2Score;
    private final String player1Name;
    private final String player2Name;
    private static final String EVEN = "All";
    private static final String DEUCE = "Deuce";
    private static final String WIN = "Win for ";
    private static final String ADVANTAGE = "Advantage ";
    private static final String DELIMITER = "-";
    private static final String[] POINTS = {"Love",
                                            "Fifteen",
                                            "Thirty",
                                            "Forty"};

    public TennisGame(String player1Name, String player2Name) {
        this.player1Score = 0;
        this.player2Score = 0;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score++;
        } else {
            player2Score++;
        }
    }

    public String getScore() {
        int scoreDifference = player1Score - player2Score;
        int highestScore = Math.max(player1Score, player2Score);
        
        return currentScore(scoreDifference, highestScore);
    }
    
    private String currentScore(int scoreDifference, int highestScore) {
        if (scoreDifference == 0) {
            return evenScore(highestScore);
        } else if (highestScore < 4) {
            return buildString(POINTS[player1Score], DELIMITER, POINTS[player2Score]);
        } else if (scoreDifference < 0) {
            return overFortyPointsWithScoreDifference(player2Name, scoreDifference);
        } else {
            return overFortyPointsWithScoreDifference(player1Name, scoreDifference);
        }
    }

    private String evenScore(int highestScore) {
        if (highestScore < 4) {
            return buildString(POINTS[player1Score], DELIMITER, EVEN);
        } else {
            return DEUCE;
        }
    }

    private String overFortyPointsWithScoreDifference(String playerName, int scoreDifference) {
        if (Math.abs(scoreDifference) > 1) {
            return buildString(WIN, playerName);
        } else {
            return buildString(ADVANTAGE, playerName);
        }
    }

    private String buildString(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string);
        }
        return builder.toString();
    }
}