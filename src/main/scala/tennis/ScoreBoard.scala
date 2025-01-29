package tennis

import tennis.Players._


case class ScoreBoard() {
  private var _scores: (Score, Score) = (Love, Love)
  private var _phase: Phases = Standard

  def phase: Phases = _phase
  def scores: (Score, Score) = _scores

  def point(player: Player): Unit = {
    _phase = _phase match {
      case Advantage(name) if name == player.name => Game(player.name)
      case Advantage(_) => Deuce
      case Deuce => Advantage(player.name)
      case Standard => standardNext(player)
    }
    _scores = player match {
      case Player1(_) => (_scores._1.next, _scores._2)
      case Player2(_) => (_scores._1, _scores._2.next)
    }
  }

  private def standardNext(player: Player): Phases = playerScoreBoard(player) match {
    case (Forty, _) => Game(player.name)
    case (Thirty, Forty) => Deuce
    case _ => Standard
  }

  private def playerScoreBoard(player: Player): (Score, Score) = player match {
    case Player1(_) => (_scores._1, _scores._2)
    case Player2(_) => (_scores._2, _scores._1)
  }
}