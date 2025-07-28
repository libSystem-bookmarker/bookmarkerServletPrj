function selectMenu(selectedItem) {
  // 모든 메뉴 초기화
  const items = document.querySelectorAll('.menu-item');
  items.forEach(item => {
    item.classList.remove('menu-item-active');
  });

  // 클릭된 메뉴에만 클래스 부여
  selectedItem.classList.add('menu-item-active');
}
