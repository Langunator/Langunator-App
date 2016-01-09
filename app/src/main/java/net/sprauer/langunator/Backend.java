package net.sprauer.langunator;

import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import net.sprauer.langunator.models.Category;
import net.sprauer.langunator.net.RestClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Backend {
    static Backend _instance = null;
    private final RestClient client;
    private MainActivity activity;

    public Backend(MainActivity activity) {
        this.activity = activity;
        client = new RestClient();
        client.getIndex(receiveIndex());
    }

    public static Backend load(MainActivity activity) {
        if (_instance == null) {
            _instance = new Backend(activity);
        }
        return _instance;
    }

    private JsonHttpResponseHandler receiveIndex() {
        return new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                try {
                    ArrayList<Category> list = Category.importFromJson(json.getJSONArray("categories"));
                    String msg = String.format("%d categories loaded", list.size());
                    Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

}
