// "use strict";
// const express = require("express");
// const app = express();
// const port = process.env.port || 9000;
// const events = require("events");
// const eventEmitter = new events.EventEmitter();
// eventEmitter.on("hetGio", xuLyThongBao);
// function xuLyThongBao(tb) {
//   console.log(tb);
// }
// setTimeout(() => {
//   eventEmitter.emit("hetGio", "Hết giờ học rồi, về thôi!!!!!!!");
// }, 2000);
// app.listen(port, () => {
//   console.log(`server dang chay tren cong ${port}`);
// });

"use strict";
const express = require("express");
const app = express();
const port = process.env.port || 9000;
const vaoLop = require("events");
const eventEmitter = new vaoLop.EventEmitter();
eventEmitter.on("vaoLop", xuLyVaoLop);
function xuLyVaoLop(tb) {
  console.log(tb);
}
setTimeout(() => {
  eventEmitter.emit("vaoLop", "Đã đến giờ học");
}, 5000);
app.listen(port, () => {
  console.log(`server dang chay tren cong ${port}`);
});

