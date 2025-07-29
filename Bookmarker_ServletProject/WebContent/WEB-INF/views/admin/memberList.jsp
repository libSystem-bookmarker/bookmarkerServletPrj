 <h2>회원 목록</h2>
  <!-- 필터/검색 영역 -->
  <form id="filterForm" action="memberInfo.do" method="get">
    <input type="hidden" name="tab" value="memberList" />

    <label for="role">역할:</label>
    <select name="role" id="role">
      <option value="">전체</option>
      <option value="admin" ${param.role == 'admin' ? 'selected' : ''}>관리자</option>
      <option value="librarian" ${param.role == 'librarian' ? 'selected' : ''}>사서</option>
      <option value="student" ${param.role == 'student' ? 'selected' : ''}>학생</option>
    </select>

    <label for="searchKeyword">검색:</label>
    <input type="text" name="searchKeyword" placeholder="이름, 학과명 등" value="${param.searchKeyword}" />
    
    <button type="submit">검색</button>
    <button type="button" onclick="deleteSelected()">선택 삭제</button>
  </form>

  <!-- 회원 목록 테이블 -->
  <table border="1" class="member-table">
    <thead>
      <tr>
        <th><input type="checkbox" id="selectAll" /></th>
        <th>아이디</th>
        <th>이름</th>
        <th>역할</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="member" items="${selectMembersList}">
        <tr>
          <td><input type="checkbox" name="selectedIds" value="${member.userId}" /></td>
          <td><a href="memberInfo.do?tab=selectMember&userId=${member.userId}">${member.userId}</a></td>
          <td>${member.name}</td>
          <td>${member.role}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>