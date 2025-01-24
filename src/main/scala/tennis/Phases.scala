package tennis

sealed trait Phases
case object Advantage extends Phases
case object Game extends Phases
case object Standard extends Phases
case object Parity extends Phases

object Phases {
  def from(score1: Int, score2: Int): Phases = {
    if(score1 == score2) return Parity
    if((score1 >= 4 || score2 >= 4) && math.abs(score1 - score2) >= 2) return Game
    if(score1 >= 4 || score2 >= 4) return Advantage
    Standard
  }
}
