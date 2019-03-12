package com.haha;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Haha {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String LOG_URL_TEMPLATE = "http://%s:5051/files/read?path=%%2Fvar%%2Flib%%2Fmesos%%2Fslaves%%2F%s%%2Fframeworks%%2F%s%%2Fexecutors%%2F%s%%2Fruns%%2Flatest%%2Fstderr&offset=0";
        System.out.println(String.format(LOG_URL_TEMPLATE, "haha","haha","haha",URLEncoder.encode("14f51bbe-1bd6-426b-a884-3720956eb23c#8457061","UTF-8")));
    }
}
