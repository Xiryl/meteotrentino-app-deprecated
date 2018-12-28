package it.chiarani.meteotrentinoapp.models;

import android.content.Intent;

public class CardRemovableInformation {
    private String title, description;
    private int resource, backgroundRes;
    private boolean isDismiss;
    private Intent intent;

    public CardRemovableInformation() {}

    public CardRemovableInformation(String title, String description, int resource, int backgroundRes,  boolean isDismiss, Intent intent) {
        this.title = title;
        this.description = description;
        this.resource = resource;
        this.isDismiss = isDismiss;
        this.backgroundRes = backgroundRes;
        this.intent = intent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public boolean isDismiss() {
        return isDismiss;
    }

    public void setDismiss(boolean dismiss) {
        isDismiss = dismiss;
    }

    public int getBackgroundRes() {
        return backgroundRes;
    }

    public void setBackgroundRes(int backgroundRes) {
        this.backgroundRes = backgroundRes;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
