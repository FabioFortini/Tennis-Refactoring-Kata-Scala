package tennis

import tennis.Player.Name

class TennisGame1(var player1: Player, var player2: Player) extends TennisGame {
  def this(player1Name: Name, player2Name: Name) = this(Player(player1Name, 0), Player(player2Name, 0))

  def wonPoint(playerName: Name): Unit = {
    if (playerName == player1.name)
      player1 = player1.copy(score = player1.score + 1)
    else
      player2 = player2.copy(score = player2.score + 1)
  }

  def calculateScore(): String = {
    val phase = Phases.from(player1, player2)
    phase match {
      case Parity(score) => score match {
        case 0 => "Love-All"
        case 1 => "Fifteen-All"
        case 2 => "Thirty-All"
        case _ => "Deuce"
      }
      case Game(playerName) => s"Win for $playerName"
      case Advantage(playerName) => s"Advantage $playerName"
      case Standard(score1, score2) => s"${scoreName(score1)}-${scoreName(score2)}"
    }
  }

  private def scoreName(score: Int) = score match {
    case 0 => "Love"
    case 1 => "Fifteen"
    case 2 => "Thirty"
    case 3 => "Forty"
  }
}
