# Sử dụng một image nền có sẵn
FROM node:18

# Tạo thư mục làm việc trong container
WORKDIR /app

# Copy file cấu hình vào trước để cài đặt thư viện
COPY package*.json ./
RUN npm install

# Copy toàn bộ mã nguồn vào container
COPY . .

# Chạy ứng dụng
CMD ["node", "index.js"]