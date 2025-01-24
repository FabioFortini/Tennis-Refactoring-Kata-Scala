package tennis

class TennisGame1(var player1: Player, var player2: Player) extends TennisGame {
  def this(player1Name: String, player2Name: String) = this(Player(player1Name, 0), Player(player2Name, 0))

  def wonPoint(playerName: String): Unit = {
    if (playerName == player1.name)
      player1 = player1.copy(score = player1.score + 1)
    else
      player2 = player2.copy(score = player2.score + 1)
  }

  def calculateScore(): String = {
    val phase = Phases.from(player1.score, player2.score)
    phase match {
      case Parity => player1.score match {
        case 0 => "Love-All"
        case 1 => "Fifteen-All"
        case 2 => "Thirty-All"
        case _ => "Deuce"
      }
      case Game => s"Win for ${higherScorePlayer(player1, player2)}"
      case Advantage => s"Advantage ${higherScorePlayer(player1, player2)}"
      case Standard => s"${scoreName(player1.score)}-${scoreName(player2.score)}"
    }
  }

  private def higherScorePlayer(player1: Player, player2: Player): String = {
    if (player1.score > player2.score) player1.name else player2.name
  }

  private def scoreName(score: Int) = score match {
    case 0 => "Love"
    case 1 => "Fifteen"
    case 2 => "Thirty"
    case 3 => "Forty"
  }
}
