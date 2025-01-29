package tennis

import tennis.Player.{Name, Score}

case class Player(name: Name, score: Score)

object Player {
  type Name = String
//  type Score = Int

    sealed trait Score
    case object Love extends Score
    case object Fifteen extends Score
    case object Thirty extends Score
    case object Forty extends Score
//    case object Advantage extends Score
//    case object Win extends Score
}