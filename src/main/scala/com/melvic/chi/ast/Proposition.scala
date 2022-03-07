package com.melvic.chi.ast

sealed trait Proposition

object Proposition {
  sealed trait PUnit extends Proposition
  case object PUnit extends PUnit
  final case class Atom(value: String) extends Proposition
  final case class Conjunction(components: List[Proposition]) extends Proposition
  final case class Disjunction(left: Proposition, right: Proposition) extends Proposition
  final case class Implication(antecedent: Proposition, consequent: Proposition) extends Proposition

  def show(proposition: Proposition): String =
    proposition match {
      case PUnit => "()"
      case Atom(value) => value
      case Conjunction(components) => "(" + components.map(show).mkString(", ") + ")"
      case Disjunction(left, right) => s"Either[${show(left)}, ${show(right)}]"
      case Implication(antecedent, consequent) => s"(${show(antecedent)} => ${show(consequent)})"
    }
}