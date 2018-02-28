// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.stopgap.mydemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamNewsResult {
    public String next;
    public @SerializedName("data") List<TeamNews> beauties;
}
