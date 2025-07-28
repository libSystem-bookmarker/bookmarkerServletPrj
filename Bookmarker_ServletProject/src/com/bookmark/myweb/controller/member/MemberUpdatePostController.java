package com.bookmark.myweb.controller.member;

import java.io.File;
import java.nio.file.Paths;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bookmark.myweb.common.CommandController;
import com.bookmark.myweb.dao.AdminMemberDAO;
import com.bookmark.myweb.dao.ProfileDAO;
import com.bookmark.myweb.model.MemberVO;
import com.bookmark.myweb.model.ProfileVO;
import com.bookmark.myweb.service.AdminMemberService;
import com.bookmark.myweb.service.ProfileService;

@MultipartConfig // file upload
public class MemberUpdatePostController implements CommandController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) {
	    HttpSession session = request.getSession();
	    MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

	    int userId = loginMember.getUserId();
	    String role = loginMember.getRole();

	    String name = request.getParameter("name");
	    String phone = request.getParameter("phoneNumber");
	    String email = request.getParameter("email");
	    String address = request.getParameter("address");
	    String deleteImage = request.getParameter("deleteImage"); // 체크박스 또는 hidden 처리

	    AdminMemberService memberService = new AdminMemberService();
	    ProfileService profileService = new ProfileService();

	    try {
	        if ("admin".equals(role)) {
	            int unitId = Integer.parseInt(request.getParameter("unitId")); // 관리자는 학과 변경 가능
	            memberService.updateAdminMember(userId, name, role, phone, email, address, unitId);

	        } else {
	            memberService.updateMemberInfo(userId, phone, email, address); // 기본 정보 수정
	            memberService.updateName(userId, name); // 이름도 변경

	            // 삭제 요청이 들어온 경우
	            if ("true".equals(deleteImage)) {
	                profileService.deleteProfileByUserId(userId);
	            }

	            // 프로필 이미지 등록
	            Part filePart = request.getPart("profileImage");
	            if (filePart != null && filePart.getSize() > 0) {
	                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
	                String uploadPath = request.getServletContext().getRealPath("/uploads");

	                File uploadDir = new File(uploadPath);
	                if (!uploadDir.exists()) uploadDir.mkdirs();

	                File file = new File(uploadPath, fileName);
	                filePart.write(file.getAbsolutePath());

	                // 이미지 등록 전 기존 이미지 삭제
	                profileService.deleteProfileByUserId(userId);

	                // 새 이미지 등록
	                ProfileVO profile = new ProfileVO();
	                profile.setUserId(userId);
	                profile.setFileName(fileName);
	                profile.setFilePath("uploads/" + fileName);

	                profileService.saveProfile(profile);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("error", "회원 정보 수정 실패: " + e.getMessage());
	        return "/error.jsp";
	    }

	    return "redirect:/member/mypage.jsp";
	}


}
