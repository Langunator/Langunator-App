package net.sprauer.langunator.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

@Table(name = "Category")
public class Category extends Model {
    // Define table fields
    @Column(name = "name")
    private String name;

    public Category() {
        super();
    }

    public Category(JSONObject object) {
        super();

        try {
            this.name = object.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Category> importFromJson(JSONArray jsonArray) {
        ArrayList<Category> categories = new ArrayList<Category>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject categoryJson = null;
            try {
                categoryJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }

            Category category = new Category(categoryJson);
            category.save();
            categories.add(category);
        }

        return categories;
    }
}
