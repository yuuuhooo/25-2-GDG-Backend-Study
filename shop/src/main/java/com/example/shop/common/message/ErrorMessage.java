package com.example.shop.common.message;

// TODO
public class ErrorMessage {

    //
    public static final String MEMBER_NOT_FOUND = "회원을 찾을 수 없습니다.";
    public static final String MEMBER_ALREADY_EXISTS = "이미 존재하는 로그인 아이디입니다.";



    // MemberCreateRequest & MemberUpdateRequest
    public static final String LOGIN_ID_NOT_NULL = "로그인 아이디는 필수입니다.";
    public static final String LOGIN_ID_SIZE = "로그인 아이디는 4자 이상 20자 이하입니다.";

    public static final String PASSWORD_NOT_NULL = "비밀번호는 필수입니다.";
    public static final String PASSWORD_SIZE = "비밀번호는 8자 이상 20자 이하여야 합니다.";

    public static final String PHONENUMBER_NOT_NULL = "전화번호는 필수입니다.";
    public static final String PHONENUMBER_FORM = "전화번호 형식은 010-xxxx-xxxx입니다.";

    public static final String ADDRESS_NOT_NULL = "주소는 필수입니다.";
    public static final String ADDRESS_SIZE = "주소는 1자 이상 255자 이하입니다.";



    // Product
    public static final String PRODUCTCODE_NOT_NULL = "제품코드는 필수입니다.";
    public static final String PRODUCTCODE_SIZE = "제품코드는 N자 이하입니다.";


//    public static final String
//    public static final String
//    public static final String
//    public static final String

}
