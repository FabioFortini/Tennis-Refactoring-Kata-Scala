package tennis

import tennis.Players._


case class ScoreBoard() {
  private var _phase: Phases = Standard(Love, Love)

  def phase: Phases = _phase

  def point(player: Player): Unit = {
    _phase = _phase match {
      case Advantage(name) if name == player.name => Game(player.name)
      case Advantage(_) => Deuce
      case Deuce => Advantage(player.name)
      case standard: Standard => standardNext(standard, player)
    }
  }

  private def standardNext(standard: Standard, player: Player): Phases = playerScoreBoard(standard, player) match {
    case PlayerScoreBoard(Forty, _) => Game(player.name)
    case PlayerScoreBoard(Thirty, Forty) => Deuce
    case _ => player match {
      case Player1(_) => Standard(standard.score1.next, standard.score2)
      case Player2(_) => Standard(standard.score1, standard.score2.next)
    }
  }

  def playerScoreBoard(standard: Standard, player: Player): PlayerScoreBoard = player match {
    case Player1(_) => PlayerScoreBoard(standard.score1, standard.score2)
    case Player2(_) => PlayerScoreBoard(standard.score2, standard.score1)
  }

  case class PlayerScoreBoard(myScore: Score, otherScore: Score)
}