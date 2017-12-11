package com.rkovtiuk.timelast.common.utils;

public class RoutesConstant {

    private static final String API = "/api/v1";

    public static final String ALL_TWEETS = API + "/tweets";
    public static final String ONE_TWEET = API + "/tweets/{id}";
    public static final String ADD_TWEET = API + "/tweets";

    public static final String USER = API + "/users/{nickname}";

}
