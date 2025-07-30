package com.bookmark.myweb.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.service.AdminMemberService;

public class MemberlistDeleteGetController implements CommandController {

    @Override
    public String process(HttpServletRequest request, HttpServletResponse response) {
        String[] userIds = request.getParameterValues("userIds");

        if (userIds != null && userIds.length > 0) {
            AdminMemberService service = new AdminMemberService();
            for (String idStr : userIds) {
                try {
                    int userId = Integer.parseInt(idStr);
                    try {
						service.deleteMember(userId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                } catch (NumberFormatException e) {
                    System.out.println("잘못된 userId: " + idStr);
                }
            }
        }

        return "redirect:/myPage.do?tab=memberList";
    }
}
