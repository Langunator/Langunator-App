package net.sprauer.langunator.models;


import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*
 * This is a temporary, sample model that demonstrates the basic structure
 * of a SQLite persisted Model object. Check out the ActiveAndroid wiki for more details:
 * https://github.com/pardom/ActiveAndroid/wiki/Creating-your-database-model
 *
 */
@Table(name = "Vocables")
public class Vocable extends Model {
    // Define table fields
    @Column(name = "category")
    private String category;
    @Column(name = "text1")
    private String text1;
    @Column(name = "text2")
    private String text2;

    public Vocable() {
        super();
    }

    public Vocable(JSONObject object) {
        super();

        try {
            this.category = object.getString("category");
            this.text1 = object.getString("text1");
            this.text2 = object.getString("text2");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Vocable> fromJson(JSONArray jsonArray) {
        ArrayList<Vocable> vocables = new ArrayList<Vocable>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject vocableJson = null;
            try {
                vocableJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Vocable vocable = new Vocable(vocableJson);
            vocable.save();
            vocables.add(vocable);
        }

        return vocables;
    }

    public static Vocable byId(long id) {
        return new Select().from(Vocable.class).where("id = ?", id).executeSingle();
    }
}