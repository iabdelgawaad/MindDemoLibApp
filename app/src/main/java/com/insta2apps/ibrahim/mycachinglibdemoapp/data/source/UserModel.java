package com.insta2apps.ibrahim.mycachinglibdemoapp.data.source;

import com.insta2apps.ibrahim.mycachinglibdemoapp.data.source.model.Item;

import java.util.List;

/**
 * Created by Ibrahim AbdelGawad on 4/9/2018.
 */

public class UserModel extends BaseModel {
    private List<Item> items = null;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
