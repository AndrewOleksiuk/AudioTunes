package com.stochanskyi.itunesloader.presentation.activities.mainActivity.navigation;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class CustomBackStack implements Parcelable {

    private ArrayList<Screen> screens;

    public CustomBackStack() {
        screens = new ArrayList<>();
    }

    public void add(Screen screen) {
        screens.remove(screen);
        screens.add(screen);
    }

    public Screen pop() {
        if(screens.size() - 1 == 0)
            return null;
        screens.remove(screens.size() - 1);
        return screens.get(screens.size() - 1);
    }

    public Screen getLastScreen() {
        return screens.get(screens.size() - 1);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.screens);
    }

    protected CustomBackStack(Parcel in) {
        this.screens = new ArrayList<Screen>();
        in.readList(this.screens, Screen.class.getClassLoader());
    }

    public static final Creator<CustomBackStack> CREATOR = new Creator<CustomBackStack>() {
        @Override
        public CustomBackStack createFromParcel(Parcel source) {
            return new CustomBackStack(source);
        }

        @Override
        public CustomBackStack[] newArray(int size) {
            return new CustomBackStack[size];
        }
    };
}
