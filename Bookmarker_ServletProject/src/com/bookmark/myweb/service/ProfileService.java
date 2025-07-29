package com.bookmark.myweb.service;

import java.io.File;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.http.Part;

import com.bookmark.myweb.dao.ProfileDAO;
import com.bookmark.myweb.model.ProfileVO;

public class ProfileService {

	private ProfileDAO profileDAO;

	public void insertProfile(int userId, Part filePart, String uploadDir) {
		try {
			String submittedFileName = filePart.getSubmittedFileName();
			if (submittedFileName == null || submittedFileName.isEmpty()) {
				return;
			}

			// uuid
			String uuid = UUID.randomUUID().toString();
			String extention = submittedFileName.substring(submittedFileName.lastIndexOf("."));
			String newFileName = uuid + extention;

			// save path
			String fullPath = uploadDir + File.separator + newFileName;
			filePart.write(fullPath);

			// save db
			ProfileVO profile = new ProfileVO();
			profile.setUserId(userId);
			profile.setFileName(newFileName);
			profile.setFilePath(fullPath);

			profileDAO.insertProfile(profile);

		} catch (Exception e) {
			System.out.println("insertProfile service error: " + e.getMessage());
			throw new RuntimeException(e);
		}

	}
	
	public ProfileVO getImage (int userId) {
		return profileDAO.selectUserId(userId);
	}

	public void deleteProfileByUserId(int userId) throws SQLException {
	    profileDAO.deleteByUserId(userId);
	}

	public void saveProfile(ProfileVO profile) {
		try {
			profileDAO.insertProfile(profile);
		} catch (Exception e) {
			System.out.println("saveProfile error: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}



}
