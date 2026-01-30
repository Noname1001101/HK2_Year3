//[1] Hàm không có tham số

const thongBao = () => {
  console.log("Chưa có thông báo, khi nào có thông báo sẽ thông báo!");
};

thongBao();

//[2] Hàm có tham số
const binhPhuong = (x) => x * x;

console.log(binhPhuong(3));

//[3] Hàm có nhiều tham số
const cong = (x, y) => x + y;

console.log(cong(2, 4));

function Person() {
  this.age = 0;

  setInterval(() => {
    this.age++; // 'this' tham chiếu đến đối tượng Person

    console.log(this.age);
  }, 1000);
}

const p = new Person();

// chặn luồng

function chanLuong() {
  console.log("Bắt đầu tác vụ cần nhiều thời gian xử lý");

  let batDau = Date.now();

  while (Date.now() - batDau < 5000) {
    // Giả lập chờ 5 giây bằng vòng lặp
  }

  console.log("Tác vụ chạy 5 giây hoàn tất");
}

console.log("Trước khi chạy tác vụ");

chanLuong(); // chặn luồng 5 giây

console.log("Sau khi chạy tác vụ");
