package org.example

import slick.jdbc.PostgresProfile.api._
import org.json4s._
import scalaj.http._
import org.json4s.jackson.JsonMethods._
import play.api.libs.json._
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import java.net.URI
import java.net.http.{HttpClient, HttpRequest, HttpResponse}
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse.BodyHandlers
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import scalaj.http.Http

class ScalaLibrary(httpClient: HttpClient, objectMapper: ObjectMapper) {

  def this() {
    this(HttpClient.newBuilder().build(), new ObjectMapper().registerModule(DefaultScalaModule))
  }

  def summarize(url: String): String = {
    try {
      // Convert the URL to a JSON string
      val requestBody = s"""{"url": "$url"}"""
      val apiUrl = "http://localhost:8000/api/summarize"

      // Send the HTTP request
      val response = Http(apiUrl)
        .postData(requestBody)
        .header("content-type", "application/json")
        .asString

      // Print response for debugging
      //      println("response", response)
      //      println("body", response.body)

      // Parse the JSON response
      val json: JsValue = Json.parse(response.body)

      // Extract and return the summary from the response
      val summary: String = (json \ "summary").as[String]
      saveSummary(url, summary)
      summary
    } catch {
      case e: Exception =>
        // Handle exceptions and print an error message
        println(s"An error occurred: ${e.getMessage}")
        "An error occurred while summarizing the URL."
    }
  }

  def getHistory(): String = {
    val db = DataBase.db
    val summaries = DataBase.summaries

    val query = summaries.result
    val resultFuture: Future[Seq[SummaryRecord]] = db.run(query)
    val result = Await.result(resultFuture, 10.seconds)

    result.map(record => s"URL: ${record.url}, Summary: ${record.summary}").mkString("\n")
  }

  def saveSummary(url: String, summary: String): Int = {
    val db = DataBase.db
    val summaries = DataBase.summaries

    val insertAction = summaries += SummaryRecord(None, url, summary)
    val res = Await.result(db.run(insertAction),10.seconds);
    res
  }
}
