package org.example

import slick.jdbc.PostgresProfile.api._
import slick.lifted.ProvenShape

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

object DataBase {
  val db = Database.forConfig("postgres") // Assuming you have a configuration named "mydb" in your application.conf

  class Summaries(tag: Tag) extends Table[SummaryRecord](tag, "summaries") {
    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def url: Rep[String] = column[String]("url")
    def summary: Rep[String] = column[String]("summary")
    def * : ProvenShape[SummaryRecord] = (id.?, url, summary) <> (SummaryRecord.tupled, SummaryRecord.unapply)
  }

  val summaries = TableQuery[Summaries]

  // Method to insert a SummaryRecord into the database
  def insertSummary(summary: SummaryRecord): Future[Int] = {
    db.run(summaries returning summaries.map(_.id) += summary)
  }

  // Method to retrieve all SummaryRecords from the database
  def getAllSummaries(): Future[Seq[SummaryRecord]] = {
    db.run(summaries.result)
  }
}
