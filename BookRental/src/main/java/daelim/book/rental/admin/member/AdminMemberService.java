package daelim.book.rental.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

    final static public int ADMIN_ACCOUNT_ALREADY_EXISTS = 0;
    final static public int ADMIN_ACCOUNT_CREATE_SUCCESS = 1;
    final static public int ADMIN_ACCOUNT_CREATE_FAIL = -1;


    @Autowired
    private AdminMemberDao adminMemberDao;

    public int createAccount(AdminMemberVo adminMemberVo) {
        System.out.println("[AdminMemberService] createAccount");

        boolean isMember = adminMemberDao.existAdminAccount(adminMemberVo.getId());
        if(!isMember) {
            int result = adminMemberDao.insertAdminAccount(adminMemberVo);
            if (result > 0) {
                System.out.println("[AdminMemberService] createAccount SUCCESS!!");
                return ADMIN_ACCOUNT_CREATE_SUCCESS;
            } else {
                System.out.println("[AdminMemberService] createAccount FAIL!!");
                return ADMIN_ACCOUNT_CREATE_FAIL;
            }
        }else{
            System.out.println("[AdminMemberService] createAccount already exist!!");
            return ADMIN_ACCOUNT_ALREADY_EXISTS;
        }

    }
}
