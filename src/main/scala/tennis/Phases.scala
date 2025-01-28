package tennis

sealed trait Phases
case object Advantage extends Phases
case object Game extends Phases
case object Standard extends Phases
case object Parity extends Phases

object Phases {
  def from(player1: Player, player2: Player): Phases = {
    if (player1.score == player2.score) return Parity
    if ((player1.score >= 4 || player2.score >= 4) && math.abs(player1.score - player2.score) >= 2) return Game
    if (player1.score >= 4 || player2.score >= 4) return Advantage
    Standard
  }
}
