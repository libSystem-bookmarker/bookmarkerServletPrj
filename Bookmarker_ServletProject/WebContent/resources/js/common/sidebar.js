function selectMenu(selectedItem) {
  // 모든 메뉴 초기화
  const items = document.querySelectorAll('.menu-item');
  items.forEach(item => {
    item.classList.remove('menu-item-active');
  });

  // 클릭된 메뉴에만 클래스 부여
  selectedItem.classList.add('menu-item-active');
}
document.getElementById('profileForm').addEventListener('submit', function(e) {
	  e.preventDefault();
	  const formData = new FormData(this);
	  fetch('updateMember.do', {
	    method: 'POST',
	    body: formData
	  })
	  .then(res => res.text())
	  .then(() => {
	    // 업데이트 후 프로필 영역만 다시 불러옴
	    loadTab('profile');
	  });
	});

