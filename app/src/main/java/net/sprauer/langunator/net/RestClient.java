package net.sprauer.langunator.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/*
 *
 * This is the object responsible for communicating with a REST API.
 * Specify the constants below to change the API being communicated with.
 * See a full list of supported API classes:
 *   https://github.com/fernandezpablo85/scribe-java/tree/master/src/main/java/org/scribe/builder/api
 * Key and Secret are provided by the developer site for the given API i.e dev.twitter.com
 * Add methods for each relevant endpoint in the API.
 *
 * NOTE: You may want to rename this object based on the service i.e TwitterClient or FlickrClient
 *
 */
public class RestClient extends AsyncHttpClient {
    public static final String REST_URL = "https://langunator.staging.inline.de"; // Change this, base API URL

    public void getIndex(JsonHttpResponseHandler jsonHttpResponseHandler) {
        get(apiUrl("/"), defaultParams(), jsonHttpResponseHandler);
    }

    private String apiUrl(String path) {
        return REST_URL + path;
    }

    private RequestParams defaultParams() {
        RequestParams params = new RequestParams();
        params.put("format", "json");
        return params;
    }
}