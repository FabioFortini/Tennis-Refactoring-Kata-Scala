package tennis

import tennis.Players.{Name, Player, Player1, Player2}

case class Players(player1: Player1, player2: Player2) {
  def by(name: Name): Player = if (name == player1.name) player1 else player2
}

object Players {
  def from(namePlayer1: Name, namePlayer2: Name): Players = Players(Player1(namePlayer1), Player2(namePlayer2))

  sealed trait Player {
    def name: Name
  }
  case class Player1(name: Name) extends Player
  case class Player2(name: Name) extends Player

  type Name = String

  sealed trait Score {
    def next: Score
  }
  case object Love extends Score { def next: Score = Fifteen }
  case object Fifteen extends Score { def next: Score = Thirty }
  case object Thirty extends Score { def next: Score = Forty }
  case object Forty extends Score { def next: Score = null }
}