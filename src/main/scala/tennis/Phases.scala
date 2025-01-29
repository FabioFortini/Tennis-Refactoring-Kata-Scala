package tennis

import tennis.Players.{Name, Score}

sealed trait Phases

case class Advantage(playerName: Name) extends Phases
case class Game(playerName: Name) extends Phases
case object Standard extends Phases
case object Deuce extends Phases
