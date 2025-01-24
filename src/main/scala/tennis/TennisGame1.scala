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
    if (m_score1 == m_score2) {
      m_score1 match {
        case 0 => "Love-All"
        case 1 => "Fifteen-All"
        case 2 => "Thirty-All"
        case _ => "Deuce"
      }
    }
    else if (m_score1 >= 4 || m_score2 >= 4) {
      val minusResult = m_score1 - m_score2
      if (minusResult == 1) return "Advantage player1"
      if (minusResult == -1) return "Advantage player2"
      if (minusResult >= 2) return "Win for player1"
      "Win for player2"
    }
    else {
      s"${scoreName(m_score1)}-${scoreName(m_score2)}"
    }
  }

  private def scoreName(score: Int) = score match {
    case 0 => "Love"
    case 1 => "Fifteen"
    case 2 => "Thirty"
    case 3 => "Forty"
  }
}