package com.example.fakestagram;

import android.app.Application;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("YEuw4Jf22ZgDc8LcoXtGHOjRzhOHj3hM3qALYV9R")
                .clientKey("w75xcDw8CjA4KlmjRFj8apEXkZp263w9LRWr2G4E")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

}
