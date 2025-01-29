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
      case Standard(score1, score2) => player match {
        case Player1(name) => score1 match {
          case Forty => Game(name)
          case Thirty if score2 == Forty => Deuce
          case _ => Standard(score1.next, score2)
        }
        case Player2(name) => score2 match {
          case Forty => Game(name)
          case Thirty if score1 == Forty => Deuce
          case _ => Standard(score1, score2.next)
        }
      }
    }
  }
}