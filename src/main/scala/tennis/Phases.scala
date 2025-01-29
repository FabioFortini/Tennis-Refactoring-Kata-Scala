package tennis

import tennis.Player.{Name, Score}

sealed trait Phases
case class Advantage(playerName: Name) extends Phases
case class Game(playerName: Name) extends Phases
case class Standard(score1: Score, score2: Score) extends Phases
case object Deuce extends Phases

//object Phases {
//  private def higherScorePlayer(player1: Player, player2: Player): Name = {
//    if (player1.score > player2.score) player1.name else player2.name
//  }
//
//  def from(player1: Player, player2: Player): Phases = {
//    if (player1.score == player2.score) return Parity(player1.score)
//    if ((player1.score >= 4 || player2.score >= 4) && math.abs(player1.score - player2.score) >= 2)
//      return Game(higherScorePlayer(player1, player2))
//    if (player1.score >= 4 || player2.score >= 4) return Advantage(higherScorePlayer(player1, player2))
//    Standard(player1.score, player2.score)
//  }
//}
