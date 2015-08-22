import java.net.URLEncoder

import services.Memcache
import unfiltered.request._
import unfiltered.response._
import org.json4s.JsonDSL._
import org.json4s.native.JsonMethods._

import scalaj.http.Http
import info.mukel.telegram.bots.api._
import info.mukel.telegram.bots._
import info.mukel.telegram.bots.json._
import info.mukel.telegram.bots.OptionPimps._

trait Gengine extends unfiltered.filter.Plan {
  this : TelegramBot =>
  def intent = {
    case r@POST(Path("/bot")) =>
      Ok ~> {
        val json = Body.string(r)
        val update = JsonUtils.unjsonify[Update](json)
        handleUpdate(update)
        //Memcache.increment("like")
        ResponseString("Ok")
      }
  }
}

class GenginedBot extends TelegramBot(TOKEN) with Gengine with Commands {
  on("hello") { (sender, _) =>
    replyTo(sender) {
      "Hello GenginedBot"
    }
  }

  on("echo") { (sender, args) => {
    replyTo(sender) {
      args mkString " "
    }
  }}

  // Let Me Google That For You :)
  on("lmgtfy") { (sender, args) =>
    replyTo(sender, disableWebPagePreview = true) {
      "http://lmgtfy.com/?q=" + URLEncoder.encode(args mkString " ", "UTF-8")
  }}

  on("markup") { (sender, _) =>
    val kb: Array[Array[String]] = Array(Array("Sucks!", "Sucks^2!"))
    val markup = ReplyKeyboardMarkup(kb,
      resizeKeyboard = true,
      oneTimeKeyboard = false)

    sendMessage(sender, "Bieber?", replyMarkup = markup)
  }

  on("hide") { (sender, _) =>
    val markup = ReplyKeyboardHide(true)
    sendMessage(sender, "Bla bla bla...", replyMarkup = markup)
  }

  on("forcereply") { (sender, _) =>
    val markup = ForceReply(true)
    sendMessage(sender, "Bla bla bla...", replyMarkup = markup)
  }

  on("start") { (sender, _) =>
    replyTo(sender) {
      """GenginedBot demo commands:
        |/hello - a warm hello!!!
        |/lmgtfy query - sends a LMGTFY URL !!
        |/echo args - simple echo
        |/hide - hide markup example
        |/markup - custom markup example
        |/forcereply - forcereply markup example
      """.stripMargin
    }
  }
}
