"use strict";

const express = require("express");

const app = express();

const port = process.env.PORT || 9000;

//cấu hình static files

app.use(express.static(__dirname + "/public"));

//xu ly khi nguoi dung gui request toi web server

app.get("/", (req, res) => {
  res.send("Chao ban Mai Quy Phước!");
});

// khoi dong web server

app.listen(port, () => {
  console.log(`server dang chay tren cong ${port}`);
});
