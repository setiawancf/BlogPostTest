package com.sigma.BlogPostTest.constant;

public interface Urls {
    String V1 = "/v1";

    interface Login {
        public static final String MODULE = "/login";
        public static final String V1_USER_LOGIN_GET_TOKEN = V1 + "/token";
    }

    interface Post {
        public static final String MODULE = "/post";
        public static final String V1_CREATE_POST = V1 + "/create-post";
        public static final String V1_UPDATE_POST = V1 + "/update-post";
        public static final String V1_DELETE_POST = V1 + "/delete-post";
        public static final String V1_ALL_POSTS = V1 + "/find-all";
        public static final String V1_POST_BY_ID = V1 + "/find";
    }

}
