"use strict";

const express = require("express");
const { cwd } = require("process");

const app = express();

const port = process.env.PORT || 9000;

// Express lắng nghe sự kiện GET request, tại route /
app.use((req, res, next) => {
  console.log(`[${new Date().toISOString()}] nhận GET request tại ${req.url}`);
  // Chuyển tiếp sự kiện đến route handler
  next();
});

// nếu có sự kiện, gọi hàm callback (route handler) tương ứng

app.get(
  "/profile",
  (req, res, next) => {
    console.log("Kiểm tra quyền truy cập...");
    req.use = { name: "Ti" };
    next();
  },
  (req, res) => {
    res.send(`Xin chào ${req.use.name}, đây là trang cá nhân của bạn.`);
  },
);

// khoi dong web server

app.listen(port, () => {
  console.log(`server dang chay tren cong ${port}`);
});
