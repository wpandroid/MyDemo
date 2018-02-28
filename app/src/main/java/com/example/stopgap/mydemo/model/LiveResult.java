// (c)2016 Flipboard Inc, All Rights Reserved.

package com.example.stopgap.mydemo.model;


import java.util.List;

public class LiveResult {
    public List<Livelist> list;
    public class Livelist {
        public String program_summary;
        public String online_num;
        public String live_cover;
        public String relate_id;
        public User user;
    }


    public class User {
        public String name;
        public String url;
    }



}


