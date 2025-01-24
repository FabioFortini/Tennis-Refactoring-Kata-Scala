package tennis

class TennisGame1(val player1: Player, val player2: Player) extends TennisGame {
  private var m_score1: Int = 0
  private var m_score2: Int = 0

  def this(player1Name: String, player2Name: String) = this(Player(player1Name, 0), Player(player2Name, 0))

  def wonPoint(playerName: String): Unit = {
    if (playerName == player1.name)
      m_score1 += 1
    else
      m_score2 += 1
  }

  def calculateScore(): String = {
    val phase = Phases.from(m_score1, m_score2)
    phase match {
      case Parity => m_score1 match {
        case 0 => "Love-All"
        case 1 => "Fifteen-All"
        case 2 => "Thirty-All"
        case _ => "Deuce"
      }
      case Game => s"Win for ${higherScorePlayer()}"
      case Advantage => s"Advantage ${higherScorePlayer()}"
      case Standard => s"${scoreName(m_score1)}-${scoreName(m_score2)}"
    }
  }

  private def higherScorePlayer(): String = {
    if (m_score1 > m_score2) player1.name else player2.name
  }

  private def scoreName(score: Int) = score match {
    case 0 => "Love"
    case 1 => "Fifteen"
    case 2 => "Thirty"
    case 3 => "Forty"
  }
}
