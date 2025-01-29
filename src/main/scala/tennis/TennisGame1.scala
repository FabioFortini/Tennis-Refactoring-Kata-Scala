package tennis

import tennis.Players.Name


class TennisGame1(val players: Players) extends TennisGame {
  def this(player1Name: Name, player2Name: Name) = this(Players.from(player1Name, player2Name))

  private val scoreBoard = ScoreBoard()

  def wonPoint(playerName: Name): Unit = {
    scoreBoard.point(players.by(playerName))
  }

  def calculateScore(): String = {
    scoreBoard.phase match {
      case Game(playerName) => s"Win for $playerName"
      case Advantage(playerName) => s"Advantage $playerName"
      case Deuce => "Deuce"
      case Standard => {
        val (score1, score2) = scoreBoard.scores
        if (score1 == score2)
          s"$score1-All"
        else
          s"$score1-$score2"
      }
      //      case Standard(score1, score2) => s"$score1-$score2"
    }
  }
}
