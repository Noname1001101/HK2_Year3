const express = require('express');
const app = express();
const port = 3000;

// Xử lý GET request từ trình duyệt
app.get('/sinhvien', (req, res) => {
    // Lấy dữ liệu từ query parameters (các tham số sau dấu ?)
    const mssv = req.query.MSSV;
    const hoTen = req.query.HoTen;
    const diemTB = req.query.DiemTrungBinh;

    // Kiểm tra xem dữ liệu có tồn tại không
    if (!mssv || !hoTen || !diemTB) {
        return res.send('Vui lòng cung cấp đầy đủ thông tin: MSSV, HoTen, DiemTrungBinh');
    }

    // Tạo nội dung phản hồi theo định dạng yêu cầu
    const responseText = `
        Chào bạn ${hoTen} <br>
        MSSV: ${mssv} <br>
        Điểm trung bình của bạn là: ${diemTB} <br>
        Chúc bạn học chăm!
    `;

    // Gửi phản hồi về client
    res.send(responseText);
});

// Khởi động server
app.listen(port, () => {
    console.log(`Server đang chạy tại http://localhost:${port}`);
});