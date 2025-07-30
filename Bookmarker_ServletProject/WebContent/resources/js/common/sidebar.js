//function selectMenu(selectedItem) {
//  // 모든 메뉴 초기화
//  const items = document.querySelectorAll('.menu-item');
//  items.forEach(item => {
//    item.classList.remove('menu-item-active');
//  });
//
//  // 클릭된 메뉴에만 클래스 부여
//  selectedItem.classList.add('menu-item-active');
//}
//document.getElementById('profileForm').addEventListener('submit', function(e) {
//	  e.preventDefault();
//	  const formData = new FormData(this);
//	  fetch('updateMember.do', {
//	    method: 'POST',
//	    body: formData
//	  })
//	  .then(res => res.text())
//	  .then(() => {
//	    // 업데이트 후 프로필 영역만 다시 불러옴
//	    loadTab('profile');
//	  });
//	});

function selectMenu(selectedItem, tab) {
  // 모든 메뉴 초기화
  const items = document.querySelectorAll('.menu-item');
  items.forEach(item => item.classList.remove('menu-item-active'));

  // 선택된 메뉴 강조
  selectedItem.classList.add('menu-item-active');

  // AJAX로 컨텐츠 영역만 로드
  fetch(`/myPage.do?tab=${tab}&isAjax=true`)
    .then(res => res.text())
    .then(html => {
    	console.log("response: ",html);
      document.getElementById('main-section').innerHTML = html;
    });
}

function toggleFacultySelector() {
    const role = document.getElementById("role").value;
    const section = document.getElementById("faculty-section");
    section.style.display = role === 'student' ? 'block' : 'none';
}

function loadDepartments() {
    const facultyId = document.getElementById("faculty").value;
    fetch(`/getDepartments.do?facultyId=${facultyId}`)
        .then(response => response.json())
        .then(data => {
            const deptSelect = document.getElementById("department");
            deptSelect.innerHTML = '<option value="">학과를 선택하세요</option>';
            data.forEach(dept => {
                const option = document.createElement("option");
                option.value = dept.unitId;
                option.text = dept.unitName;
                deptSelect.appendChild(option);
            });
        });
}

function toggleFacultySelector() {
    const role = document.getElementById("role").value;
    const facultySection = document.getElementById("faculty-section");
    if (role === "student") {
        facultySection.style.display = "block";
    } else {
        facultySection.style.display = "none";
    }
}

//학과 등록
function loadDepartments() {
    const facultyId = document.getElementById("faculty").value;

    fetch('/getDepartments.do?facultyId=' + facultyId)
        .then(response => response.json())
        .then(data => {
            const departmentSelect = document.getElementById("department");
            departmentSelect.innerHTML = "<option value=''>학과를 선택하세요</option>";

            data.forEach(dept => {
                const option = document.createElement("option");
                option.value = dept.unitId;
                option.textContent = dept.unitName;
                departmentSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error("학과 불러오기 실패:", error);
        });
}