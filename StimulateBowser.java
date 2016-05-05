/**
*会执行javascript代码，返回正真的内容
*还需要一个htmlunit jar文件
*@author xialonglei
*@since 2016/5/5
*/
public class MyWebClient extends WebClient {

	public MyWebClient() {
		//开启css解析
		this.getOptions().setCssEnabled(true);
		//开启js解析
		this.getOptions().setJavaScriptEnabled(true);
		this.getOptions().setThrowExceptionOnScriptError(false);
		this.getOptions().setThrowExceptionOnFailingStatusCode(false);
	}
	@Override
	public void throwFailingHttpStatusCodeExceptionIfNecessary(WebResponse webResponse) {
		int statusCode = webResponse.getStatusCode();
		boolean successful = true;
		if (this.getOptions().isThrowExceptionOnFailingStatusCode() && !successful) {
			throw new FailingHttpStatusCodeException(webResponse);
		}
	}
	
	/**
	*@param 请求页面地址
	*@return 
	*获取去源码
	*/
	public String getSource(String url) throws Exception {
		WebRequest request = new WebRequest(new URL(url));
		HtmlPage page = webClient.getPage(request);
		webClient.waitForBackgroundJavaScript(10 * 1000);
		// 有可能执行js时会新开一个页面，就是获取当前页面源码
		HtmlPage enclosedPage = (HtmlPage) webClient.getCurrentWindow().getEnclosedPage();
		webClient.close();
		return enclosedPage.asXml();
	}
}




