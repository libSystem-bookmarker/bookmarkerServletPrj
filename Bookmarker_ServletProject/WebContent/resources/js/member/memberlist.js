// 전체 선택/해제
document.addEventListener("DOMContentLoaded", function () {
  const selectAll = document.getElementById("selectAll");

  if (selectAll) {
    selectAll.addEventListener("change", function () {
      const checkboxes = document.querySelectorAll("input[name='selectedIds']");
      checkboxes.forEach(chk => chk.checked = selectAll.checked);
    });
  }
});


function deleteSelected() {
	  const checked = Array.from(document.querySelectorAll("input[name='selectedIds']:checked"));
	  if (checked.length === 0) {
	    alert("삭제할 회원을 선택하세요.");
	    return;
	  }

	  if (!confirm("선택한 회원을 삭제하시겠습니까?")) return;

	  const form = document.createElement("form");
	  form.method = "post";
	  form.action = "deleteMembers.do";

	  checked.forEach(chk => {
	    const input = document.createElement("input");
	    input.type = "hidden";
	    input.name = "userIds";
	    input.value = chk.value;
	    form.appendChild(input);
	  });

	  document.body.appendChild(form); // 기존 form 밖에서 동작함
	  form.submit();
	}
