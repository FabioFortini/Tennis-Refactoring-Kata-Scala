package tennis

import tennis.Player.{Fifteen, Forty, Love, Name, Thirty}

case class ScoreBoard(player1Name: Name, player2Name: Name) {
  private var _phase: Phases = Standard(Love, Love)

  def phase: Phases = _phase

  def pointPlayer1: Unit = _phase = _phase match {
    case Advantage(playerName) => if (playerName == player1Name) Game(player1Name) else Deuce
    case Deuce => Advantage(player1Name)
    case Standard(Forty, _) => Game(player1Name)
    case Standard(Thirty, Forty) => Deuce
    case Standard(Thirty, _other) => Standard(Forty, _other)
    case Standard(Fifteen, _other) => Standard(Thirty, _other)
    case Standard(Love, _other) => Standard(Fifteen, _other)
  }

  def pointPlayer2: Unit = _phase = _phase match {
    case Advantage(playerName) => if (playerName == player2Name) Game(player2Name) else Deuce
    case Deuce => Advantage(player2Name)
    case Standard(_, Forty) => Game(player2Name)
    case Standard(Forty, Thirty) => Deuce
    case Standard(_other, Thirty) => Standard(_other, Forty)
    case Standard(_other, Fifteen) => Standard(_other, Thirty)
    case Standard(_other, Love) => Standard(_other, Fifteen)
  }
}

class TennisGame1(var player1: Player, var player2: Player) extends TennisGame {
  def this(player1Name: Name, player2Name: Name) = this(Player(player1Name, Love), Player(player2Name, Love))

  private val scoreBoard = ScoreBoard(player1.name, player2.name)

  def wonPoint(playerName: Name): Unit = {
    if (playerName == player1.name) {
      scoreBoard.pointPlayer1
    } else
      scoreBoard.pointPlayer2
  }

  def calculateScore(): String = {
    scoreBoard.phase match {
      case Game(playerName) => s"Win for $playerName"
      case Advantage(playerName) => s"Advantage $playerName"
      case Deuce => "Deuce"
      case Standard(score1, score2) => if (score1 == score2) s"$score1-All" else  s"$score1-$score2"
    }
  }
}
