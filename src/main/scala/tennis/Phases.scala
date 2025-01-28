package tennis

sealed trait Phases
case class Advantage(player: Player) extends Phases
case class Game(player: Player) extends Phases
case class Standard(p1: Player, p2: Player) extends Phases
case class Parity(score: Int) extends Phases

object Phases {
  private def higherScorePlayer(player1: Player, player2: Player): Player = {
    if (player1.score > player2.score) player1 else player2
  }

  def from(player1: Player, player2: Player): Phases = {
    if (player1.score == player2.score) return Parity(player1.score)
    if ((player1.score >= 4 || player2.score >= 4) && math.abs(player1.score - player2.score) >= 2)
      return Game(higherScorePlayer(player1, player2))
    if (player1.score >= 4 || player2.score >= 4) return Advantage(higherScorePlayer(player1, player2))
    Standard(player1, player2)
  }
}
