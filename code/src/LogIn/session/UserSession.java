package LogIn.session;

import database.database.vo.BookVO;
import database.database.vo.UserBookVO;
import database.database.vo.UserLoginVO;

public  class UserSession {

    private static UserLoginVO userLoginVO;
    private static UserBookVO userBookVO;
    private static BookVO bookVO;

    public static UserLoginVO getUserLoginVO() {
        return userLoginVO;
    }

    public static UserBookVO getUserBookVO(){
        return userBookVO;
    }

    public static BookVO getBookVO(){return bookVO;}

    public static void setUserBookVO(UserBookVO userBookVO){UserSession.userBookVO = userBookVO;}

    public static void setUserLoginVO(UserLoginVO userLoginVO) {
        UserSession.userLoginVO = userLoginVO;
    }

    public static void setBookVO(BookVO bookVO){UserSession.bookVO = bookVO;}
}
