package com.team1.TBBCloneCoding.common.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {

    TOKEN_ERROR_MSG(401,"토큰이 유효하지 않습니다."),
    USER_DOES_NOT_EXIST_ERROR_MSG(401,"존재하지 않는 유저입니다."),
    DO_NOT_HAVE_PERMISSION_ERROR_MSG(403,"사용 권한이 없습니다."),
    NO_EXIST_POSTING_ERROR_MSG(404, "해당 게시글이 존재하지 않습니다."),
    USER_NOT_MATCH_ERROR_MSG(400, "작성자가 일치하지 않습니다."),
    NO_EXIST_COMMENT_ERROR_MSG(400, "해당 댓글이 존재하지 않습니다.");

    private final int status;
    private final String msg;
    ExceptionMessage(final int status, final String msg) {
        this.status = status;
        this.msg = msg;
    }
}
