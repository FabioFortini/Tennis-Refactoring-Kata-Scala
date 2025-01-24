package tennis

import tennis.Player.{Name, Score}

case class Player(name: Name, score: Score)

object Player {
  type Name = String
  type Score = Int
}