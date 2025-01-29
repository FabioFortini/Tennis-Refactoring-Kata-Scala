package tennis

import tennis.Players.{Name, Score}

sealed trait Phases

case class Advantage(playerName: Name) extends Phases
case class Game(playerName: Name) extends Phases
case class Standard(score1: Score, score2: Score) extends Phases
case object Deuce extends Phases
