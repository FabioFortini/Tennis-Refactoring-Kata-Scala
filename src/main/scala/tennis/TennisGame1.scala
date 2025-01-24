package tennis

class TennisGame1(val player1Name: String, val player2Name: String) extends TennisGame {
  private var m_score1: Int = 0
  private var m_score2: Int = 0

  def wonPoint(playerName: String): Unit = {
    if (playerName == "player1")
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
    if (m_score1 > m_score2) "player1" else "player2"
  }

  private def scoreName(score: Int) = score match {
    case 0 => "Love"
    case 1 => "Fifteen"
    case 2 => "Thirty"
    case 3 => "Forty"
  }
}
