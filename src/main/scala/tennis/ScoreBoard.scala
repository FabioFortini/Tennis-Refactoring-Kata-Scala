package tennis

import tennis.Players.{Fifteen, Forty, Love, Name, Player, Player1, Player2, Thirty}


case class ScoreBoard() {
  private var _phase: Phases = Standard(Love, Love)

  def phase: Phases = _phase

  def point(player: Player) = player match {
    case Player1(name) => pointPlayer1(name)
    case Player2(name) => pointPlayer2(name)
  }

  private def pointPlayer1(playerName: Name): Unit = _phase = _phase match {
    case Advantage(name) if name == playerName => Game(playerName)
    case Advantage(_) => Deuce
    case Deuce => Advantage(playerName)
    case Standard(Forty, _) => Game(playerName)
    case Standard(Thirty, Forty) => Deuce
    case Standard(Thirty, _other) => Standard(Forty, _other)
    case Standard(Fifteen, _other) => Standard(Thirty, _other)
    case Standard(Love, _other) => Standard(Fifteen, _other)
  }

  private def pointPlayer2(playerName: Name): Unit = _phase = _phase match {
    case Advantage(name) => if (name == playerName) Game(playerName) else Deuce
    case Deuce => Advantage(playerName)
    case Standard(_, Forty) => Game(playerName)
    case Standard(Forty, Thirty) => Deuce
    case Standard(_other, Thirty) => Standard(_other, Forty)
    case Standard(_other, Fifteen) => Standard(_other, Thirty)
    case Standard(_other, Love) => Standard(_other, Fifteen)
  }
}