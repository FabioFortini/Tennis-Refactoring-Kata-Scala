package tennis

import tennis.Player.{Fifteen, Forty, Love, Name, Thirty}

case class ScoreBoard(player1Name: Name, player2Name: Name) {
  private var _phase: Phases = Parity(Love)

  def phase: Phases = _phase

  def pointPlayer1: Unit = _phase = _phase match {
    case Parity(Love) => Standard(Fifteen, Love)
    case Standard(Love, Fifteen) => Parity(Fifteen)
    case Parity(Fifteen) => Standard(Thirty, Fifteen)
    case Standard(Fifteen, Thirty) => Parity(Thirty)
    case Parity(Thirty) => Standard(Forty, Thirty)
    case Standard(Thirty, Forty) => Deuce
    case Standard(Love, score2) => Standard(Fifteen, score2)
    case Standard(Fifteen, score2) => Standard(Thirty, score2)
    case Standard(Thirty, score2) => Standard(Forty, score2)
    case Standard(Forty, _) => Game(player1Name)
    case Deuce => Advantage(player1Name)
    case Advantage(playerName) => if (playerName == player1Name) Game(player1Name) else Deuce
  }

  def pointPlayer2: Unit = _phase = _phase match {
    case Parity(Love) => Standard(Love, Fifteen)
    case Standard(Fifteen, Love) => Parity(Fifteen)
    case Parity(Fifteen) => Standard(Fifteen, Thirty)
    case Standard(Thirty, Fifteen) => Parity(Thirty)
    case Parity(Thirty) => Standard(Thirty, Forty)
    case Standard(Forty, Thirty) => Deuce
    case Standard(score2, Love) => Standard(score2, Fifteen)
    case Standard(score2, Fifteen) => Standard(score2, Thirty)
    case Standard(score2, Thirty) => Standard(score2, Forty)
    case Standard(_, Forty) => Game(player2Name)
    case Deuce => Advantage(player2Name)
    case Advantage(playerName) => if (playerName == player2Name) Game(player2Name) else Deuce
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
    val phase = scoreBoard.phase
    phase match {
      case Parity(score) => s"$score-All"
      case Deuce => "Deuce"
      case Game(playerName) => s"Win for $playerName"
      case Advantage(playerName) => s"Advantage $playerName"
      case Standard(score1, score2) => s"$score1-$score2"
    }
  }
}
