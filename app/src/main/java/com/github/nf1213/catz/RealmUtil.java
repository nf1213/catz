package com.github.nf1213.catz;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by nicole on 1/30/16.
 */
public class RealmUtil {
    public static <E extends RealmObject> int getId(Context context, Class<E> z) {
        return Realm.getInstance(context).where(z).max("id").intValue() + 1;
    }
}
