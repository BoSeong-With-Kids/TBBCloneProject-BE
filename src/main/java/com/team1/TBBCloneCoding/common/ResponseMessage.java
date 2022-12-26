package com.team1.TBBCloneCoding.common;


import lombok.Getter;

@Getter
public enum ResponseMessage {

    // post
    READ_POSTING_SUCCESS_MSG(200, "포스팅 정보 조회 성공"),
    CREATE_POSTING_SUCCESS_MSG(201, "포스팅 생성 성공 "),
    UPDATE_POSTING_SUCCESS_MSG(201,"포스팅 수정 성공"),
    DELETE_POSTING_SUCCESS_MSG(200,"포스팅 삭제 성공"),
    CHECK_POSTING_SUCCESS_MSG(200, "게시글 수정 가능"),
    RECOMMEND_POSTING_SUCCESS_MSG(200, "게시글 좋아요 성공"),
    RECOMMEND_CANCEL_POSTING_SUCCESS_MSG(200, "게시글 좋아요 취소"),


    // comment
    CREATE_COMMENT_SUCCESS_MSG(201, "댓글 생성 성공"),
    UPDATE_COMMENT_SUCCESS_MSG(200, "댓글 수정 성공"),
    DELETE_COMMENT_SUCCESS_MSG(200, "댓글 삭제 성공"),
    CHECK_COMMENT_SUCCESS_MSG(200, "댓글 수정 가능"),

    // user
    SIGNUP_USER_SUCCESS_MSG(201, "유저 회원가입 성공 "),
    LOGIN_USER_SUCCESS_MSG(201, "유저 로그인 성공 "),
    USER_DELETE_SUCCESS_MSG(201, "회원 탈퇴 성공 "),
    USER_DUBLE_CHECK_SUCCESS_MSG(200,"사용 가능한 아이디"),
    NICK_DUBLE_CHECK_SUCCESS_MSG(200,"사용 가능한 닉네임");


    private final int status;
    private final String msg;
    ResponseMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
