// 'use strict'

// const express = require('express')

// const app = express();

// const port = process.env.PORT || 9000

 

// // gọi module events

// const events = require('events');

// // tạo ra một EventEmitter

// const eventEmitter = new events.EventEmitter();

// // đăng ký tên sự kiện, gắn với hàm lắng nghe và

// // xử lý sự kiện tương ứng

// eventEmitter.on('hetGio', (thongBao) => {

//     console.log(thongBao);

// });

// // phát ra sự kiện, kèm theo thông điệp

// setTimeout(()=> {

//     eventEmitter.emit('hetGio','Hết giờ học rồi, về thôi!!!!!!!')

// }, 2000);

 

// // khoi dong web server

// app.listen(port, () => {

//     console.log(`server dang chay tren cong ${port}`);

// });



'use strict'

const express = require('express')

const app = express();

const port = process.env.PORT || 9000

 

// gọi module events

const vaoLop = require('events');

// tạo ra một EventEmitter

const eventEmitter = new vaoLop.EventEmitter();
// đăng ký tên sự kiện, gắn với hàm lắng nghe và

// xử lý sự kiện tương ứng

eventEmitter.on('vaoLop', (thongBao) => {

    console.log(thongBao);

});

// phát ra sự kiện, kèm theo thông điệp

setTimeout(()=> {

    eventEmitter.emit('vaoLop','Đã đến giờ học')

}, 2000);

 

// khoi dong web server

app.listen(port, () => {

    console.log(`server dang chay tren cong ${port}`);

});