import scalaj.http._

object HttpUtil {
  val GET_REGEX = """^(?i)get$""".r
  val POST_REGEX = """^(?i)post$""".r
  val PUT_REGEX = """^(?i)put$""".r
  val DELETE_REGEX = """^(?i)delete$""".r

  def main(args: Array[String]): Unit = {
    println("Hello, world!")
    val params = Map[String, Object]()
    val header = Map[String, Object](("Content-Type", "application/json"))
    val data = "{\"accuracy\" : \"great\"}"
    httpRequest("http://127.0.0.1:9200/griffin/accuracy", "post", params, header, data)
    println("Run, http post request!")
    httpRequest("http://127.0.0.1:9200/griffin/accuracy/_search?pretty=true", "get", params, header, "")
    println("Run, http get request!")
  }

  def httpRequest(url: String,
                  method: String,
                  params: Map[String, Object],
                  headers: Map[String, Object],
                  data: String): Boolean = {
    val httpReq = Http(url).params(convertObjMap2StrMap(params))
      .headers(convertObjMap2StrMap(headers))
    method match {
      case POST_REGEX() =>
        val res = httpReq.postData(data).asString
        res.isSuccess
      case PUT_REGEX() =>
        val res = httpReq.put(data).asString
        res.isSuccess
      case GET_REGEX() =>
        val res = httpReq.put(data).method("GET").asString
        println(res)
        res.isSuccess
      case _ => false
    }
  }

  private def convertObjMap2StrMap(map: Map[String, Object]): Map[String, String] = {
    map.map(pair => pair._1 -> pair._2.toString)
  }
}