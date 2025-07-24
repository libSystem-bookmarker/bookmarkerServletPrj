package com.bookmark.myweb.model;

import lombok.Data;

@Data
public class MajorVO {
	
    private int unitId;
    private String unitName;
    private String unitType;
    private int parentId;
}
