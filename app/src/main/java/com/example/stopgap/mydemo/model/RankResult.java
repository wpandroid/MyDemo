// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.stopgap.mydemo.model;


import java.util.List;

public class RankResult {
    public ContentBean content;
    public class ContentBean {
        public  List<RoundsBean> rounds;
    }
    public class RoundsBean {
        public  ContentBean2 content;
    }

    public class ContentBean2 {
        public  List<Data> data;
        public  List<String> header;
    }

    public class Data {
        public String team_id;
        public String team_name;
        public String team_logo;
        public String goals_against;
        public String goals_pro;
        public String matches_draw;
        public String matches_lost;
        public String matches_total;
        public String matches_won;
        public String points;
    }



}


