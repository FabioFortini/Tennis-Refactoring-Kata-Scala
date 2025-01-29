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
      case Standard => player match {
        case Player1(name) => _scores match {
          case (Forty, _) => Game(name)
          case (Thirty, Forty) => Deuce
          case _ => Standard
        }
        case Player2(name) => _scores match {
          case (_, Forty) => Game(name)
          case (Forty, Thirty) => Deuce
          case _ => Standard
        }
      }
    }
    _scores = player match {
      case Player1(_) => (_scores._1.next, _scores._2)
      case Player2(_) => (_scores._1, _scores._2.next)
    }
  }
}