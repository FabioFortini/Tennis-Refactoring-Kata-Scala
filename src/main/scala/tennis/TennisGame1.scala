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
    var score: String = ""
    if (m_score1 == m_score2) {
      score = m_score1 match {
        case 0 => "Love-All"
        case 1 => "Fifteen-All"
        case 2 => "Thirty-All"
        case _ => "Deuce"
      }
    }
    else if (m_score1 >= 4 || m_score2 >= 4) {
      val minusResult = m_score1 - m_score2
      if (minusResult == 1) score = "Advantage player1"
      else if (minusResult == -1) score = "Advantage player2"
      else if (minusResult >= 2) score = "Win for player1"
      else score = "Win for player2"
    }
    else {
      val score1String = m_score1 match {
        case 0 => "Love"
        case 1 => "Fifteen"
        case 2 => "Thirty"
        case 3 => "Forty"
      }
      val score2String = m_score2 match {
        case 0 => "Love"
        case 1 => "Fifteen"
        case 2 => "Thirty"
        case 3 => "Forty"
      }
      score = s"$score1String-$score2String"
    }
    score
  }
}
