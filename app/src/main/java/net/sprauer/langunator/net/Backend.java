package net.sprauer.langunator.net;

import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;

import net.sprauer.langunator.MainActivity;
import net.sprauer.langunator.R;
import net.sprauer.langunator.models.Category;

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

            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                ((TextView) activity.findViewById(R.id.txt)).setText("Can't fetch index: (Code " + statusCode + "): ");
                Toast.makeText(activity, "Can't fetch index: (Code " + statusCode + ") ", Toast.LENGTH_LONG);
            }
        };
    }

}
