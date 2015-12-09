import java.io.IOException;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import okio.BufferedSink;

public class SyncGet {
	
   public static void main(String[] args) throws Exception {
	   // header();
	   // post();
	   // get();
	   postStream();
	   System.out.println(Long.MAX_VALUE);
   }
   
   
   /**
    * RequestBody formBody = new FormEncodingBuilder()
            .add("query", "Hello")
            .build();
    * 
   
   
   
   MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
   RequestBody requestBody = new MultipartBuilder()
       .type(MultipartBuilder.FORM)
       .addPart(
               Headers.of("Content-Disposition", "form-data; name=\"title\""),
               RequestBody.create(null, "测试文档"))
       .addPart(
               Headers.of("Content-Disposition", "form-data; name=\"file\""),
               RequestBody.create(MEDIA_TYPE_TEXT, new File("input.txt")))
       .build();
    */
   
   
   public static void postStream() throws Exception {
	   OkHttpClient client = new OkHttpClient();
	    final MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
	    final String postBody = "Hello World";

	    RequestBody requestBody = new RequestBody() {
	        @Override
	        public MediaType contentType() {
	            return MEDIA_TYPE_TEXT;
	        }

	        @Override
	        public void writeTo(BufferedSink sink) throws IOException {
	            sink.writeUtf8(postBody);
	        }

	 @Override
	        public long contentLength() throws IOException {
	            return postBody.length();
	        }
	    };

	    Request request = new Request.Builder()
	            .url("http://www.baidu.com")
	            .post(requestBody)
	            .build();

	    Response response = client.newCall(request).execute();
	    if (!response.isSuccessful()) {
	        throw new IOException("服务器端错误: " + response);
	    }

	    System.out.println(response.body().string());
   }
   
   public static void get() throws Exception{
	   OkHttpClient client = new OkHttpClient();

	    Request request = new Request.Builder()
	            .url("https://www.baidu.com")
	            .build();

	    Response response = client.newCall(request).execute();
	    if (!response.isSuccessful()) {
	        throw new IOException("服务器端错误: " + response);
	    }

	    Headers responseHeaders = response.headers();
	    for (int i = 0; i < responseHeaders.size(); i++) {
	        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
	    }

	    System.out.println(response.body().string());
   }
   
    
   public static void header() throws Exception {
	   OkHttpClient client = new OkHttpClient();

	    Request request = new Request.Builder()
	            .url("http://www.baidu.com")
	            .header("User-Agent", "My super agent")
	            .addHeader("Accept", "text/html")
	            .build();

	    Response response = client.newCall(request).execute();
	    if (!response.isSuccessful()) {
	        throw new IOException("服务器端错误: " + response);
	    }

	    System.out.println(response.header("Server"));
	    System.out.println(response.headers("Set-Cookie"));
	    System.out.println(response.body().string());
   }
   
   public static void post() throws Exception {
	   
	   OkHttpClient client = new OkHttpClient();
	   
	    MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");
	    String postBody = "Hello World";

	    Request request = new Request.Builder()
	            .url("http://www.baidu.com")
	            .post(RequestBody.create(MEDIA_TYPE_TEXT, postBody))
	            .build();

	    Response response = client.newCall(request).execute();
	    if (!response.isSuccessful()) {
	        throw new IOException("服务器端错误: " + response);
	    }

	    System.out.println(response.body().string());
	   }

   
}