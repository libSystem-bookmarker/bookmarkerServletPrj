// 전체 선택/해제
document.addEventListener("DOMContentLoaded", function () {
  const selectAll = document.getElementById("selectAll");
  const checkboxes = document.querySelectorAll("input[name='selectedIds']");

  if (selectAll) {
    selectAll.addEventListener("change", function () {
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

  if (!confirm("선택한 회원을 삭제하시겠습니까?")) {
    return;
  }

  const ids = checked.map(chk => chk.value);
  const form = document.createElement("form");
  form.method = "post";
  form.action = "deleteMember.do";

  ids.forEach(id => {
    const input = document.createElement("input");
    input.type = "hidden";
    input.name = "userIds";
    input.value = id;
    form.appendChild(input);
  });

  document.body.appendChild(form);
  form.submit();
}
