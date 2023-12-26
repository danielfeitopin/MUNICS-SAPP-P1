-- Categories
INSERT INTO Categories (category_id, name, description, icon, highlighted) VALUES (1, 'Laptops', 'Lightweight, Everyday Computing and Desktop Replacement', 'fa-laptop', true);
INSERT INTO Categories (category_id, name, description, icon, highlighted) VALUES (2, 'Cameras', 'Standard Compact, Zoom Compact, Adventure Cameras, Advanced Compact and Super-Zoom', 'fa-camera', true);
INSERT INTO Categories (category_id, name, description, icon, highlighted) VALUES (3, 'Mobile Phones', 'Smart Phones, Camera Phones, Music Phones, 5G Phones and Basic Phones', 'fa-mobile-alt', true);
INSERT INTO Categories (category_id, name, description, icon, highlighted) VALUES (4, 'TV', 'LCD, LED and OLED', 'fa-tv', true);
INSERT INTO Categories (category_id, name, description, icon, highlighted) VALUES (5, 'Desktop Computers', 'Small Desktops, Gaming PC, All-In-One and Power Servers', 'fa-desktop', false);
INSERT INTO Categories (category_id, name, description, icon, highlighted) VALUES (6, 'Tablets', 'High Resolution, Best Performance, Wi-Fi Only and 4G', 'fa-tablet-alt', false);
INSERT INTO Categories (category_id, name, description, icon, highlighted) VALUES (7, 'Accesories', 'Keyboards, Hard Disks, Microphones, Wifi Routers, and much more', 'fa-keyboard', false);

-- Laptops
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (1, 1, 'Ultra Portable', 'Processor Speed 1.1MHz, RAM 4G, Storage 64 GB, Screen 13.3 inches', 'fa-laptop', 380, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (2, 1, 'General Purpose', 'Processor Speed 2.4MHz, RAM 4G, Storage 1 TB, Screen 15.6 inches', 'fa-laptop', 420, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (3, 1, 'Business Small', 'Processor Speed 2.4MHz, RAM 4G, Storage 128 GB, Screen 13.3 inches', 'fa-laptop', 535, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (4, 1, 'Business', 'Processor Speed 2.4MHz, RAM 8G, Storage 512 GB, Screen 14 inches', 'fa-laptop', 780, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (5, 1, 'Gaming', 'Processor Speed 2.8MHz, RAM 16G, Storage 2 TB, Screen 15.6 inches', 'fa-laptop', 925, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (6, 1, 'Ultra Power Small', 'Processor Speed 2.4MHz, RAM 16G, Storage 1 TB, Screen 12.2 inches', 'fa-laptop', 1095, 0, 0, 0);

-- Cameras
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (7,  2, 'Compact 16', 'Resolution 16 MP, Optical Zoom 3.8, Popup flash, Wi-Fi', 'fa-camera', 165, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (8,  2, 'Compact 20', 'Resolution 20.2 MP, Optical Zoom 4.2, Popup flash, Touch Screen Display', 'fa-camera', 430, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (9,  2, 'DSLR 24', 'Resolution  24.2MP, Autofocus 11-point AF, ISO Range 100 to 51200', 'fa-camera', 540, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (10, 2, 'DSLR 36', 'Resolution  35.3MP, Autofocus 51-point AF, ISO Range 64 to 51200', 'fa-camera-retro', 975, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (11, 2, 'WaterProof 14', 'Resolution  14.2MP, Interchangeable Lenses, Operational Water Depep up to 49 feet', 'fa-camera-retro', 780, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (12, 2, 'Camcorder 20', 'Resolution  20MP, LCD Screen 3 inches', 'fa-video', 990, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (13, 2, 'Camcorder 36', 'Resolution  35.3MP, LCD Screen 3.5 inches', 'fa-video', 1090, 0, 0, 0);

-- Mobile Phones
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (14, 3, '2G Home', 'Display 3 inches, Camera 2MP, Battery 500mAh', 'fa-mobile', 60, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (15, 3, 'Smart Quad core 5', 'RAM 3GB, Display 5 inches, Storage 16GB, Camera 12MP, Battery 3000mAh', 'fa-mobile-alt', 150, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (16, 3, 'Smart Quad core 5.5', 'RAM 3GB, Display 5.5 inches, Storage 16GB, Camera 12MP, Battery 3500mAh', 'fa-mobile-alt', 170, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (17, 3, 'Smart Quad core 6', 'RAM 4GB, Display 6 inches, Storage 32GB, Camera 20MP, Battery 4000mAh', 'fa-mobile-alt', 250, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (18, 3, 'Smart Octa core 5', 'RAM 4GB, Display 5 inches, Storage 16GB, Camera 16MP, Battery 4000mAh', 'fa-mobile-alt', 280, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (19, 3, 'Smart Octa core 5.5', 'RAM 8GB, Display 5.5 inches, Storage 32GB, Camera 20MP, Battery 4000mAh', 'fa-mobile-alt', 350, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (20, 3, 'Smart Octa core 6', 'RAM 8GB, Display 6 inches, Storage 64GB, Camera 24MP, Battery 5000mAh', 'fa-mobile-alt', 505, 0, 0, 0);

-- TV
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (21, 4, 'LED 32', 'Display Size 32 inches, Resolution 720p, Flat', 'fa-tv', 200, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (22, 4, 'Smart LED 32', 'Display Size 32 inches, Resolution 720p, Smart TV, Flat', 'fa-tv', 235, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (23, 4, 'Smart LED HD 64', 'Display Size 64 inches, Resolution 1080p, Smart TV, Flat', 'fa-tv', 400, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (24, 4, 'OLED 64', 'Display Size 64 inches, Resolution 1080p, Flat', 'fa-tv', 510, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (25, 4, 'Smart OLED 72', 'Display Size 72 inches, Resolution 1080p, Smart TV, Flat', 'fa-tv', 730, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (26, 4, 'Smart LED HD 80', 'Display Size 80 inches, Resolution 4K, Smart TV, Flat', 'fa-tv', 970, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (27, 4, 'Smart LED HD Plus 120', 'Display Size 120 inches, Resolution 4k, Smart TV, Curved', 'fa-tv', 1295, 0, 0, 0);

-- Desktop Computers
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (28, 5, 'Single Core Home 3', 'Single core 3GHz processor, 4GB RAM, 1TB Hard Drive', 'fa-desktop', 200, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (29, 5, 'Dual Core Home 3', 'Dual core 3GHz processor, 8GB RAM, 1TB Hard Drive', 'fa-desktop', 230, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (30, 5, 'Dual Core Home 3 Gamming', 'Dual core 3GHz processor, 8GB RAM, 1TB Hard Drive, Advanced Graphic Card', 'fa-desktop', 280, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (31, 5, 'Dual Core Office 3', 'Dual core 3.1GHz processor, 8GB RAM, 1TB Hard Drive, Wi-Fi', 'fa-desktop', 350, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (32, 5, 'Quad Core Office 4', 'Dual core 3.9GHz processor, 16GB RAM, 2TB Hard Drive, Wi-Fi', 'fa-desktop', 400, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (33, 5, 'Quad Core Office 4 Premiun', 'Dual core 3.9GHz processor, 32GB RAM, 3TB Hard Drive, Wi-Fi', 'fa-desktop', 600, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (34, 5, 'Quad Core Office 4 Server', 'Dual core 3.9GHz processor plus, 64GB RAM, 4TB Hard Drive, Wi-Fi', 'fa-server', 850, 0, 0, 0);

-- Tablets
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (35, 6, 'Home 7', 'RAM 2GB, Display 7 inches, Storage 8GB, Camera 2MP, Battery 3000mAh', 'fa-tablet-alt', 150, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (36, 6, 'Home 8', 'RAM 2GB, Display 8 inches, Storage 16GB, Camera 2MP, Battery 3000mAh', 'fa-tablet-alt', 180, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (37, 6, 'Home 8 HD', 'RAM 2GB, Display 8 inches HD, Storage 16GB, Camera 2MP, Battery 3500mAh', 'fa-tablet-alt', 250, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (38, 6, 'Business 7', 'RAM 3GB, Display 7 inches, Storage 16GB, Camera 5MP, Battery 4000mAh', 'fa-tablet-alt', 230, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (39, 6, 'Business 8', 'RAM 4GB, Display 8 inches, Storage 32GB, Camera 5MP, Battery 4000mAh', 'fa-tablet-alt', 295, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (40, 6, 'Business 8 HD', 'RAM 4GB, Display 8 inches HD, Storage 32GB, Camera 5MP, Battery 4800mAh', 'fa-tablet-alt', 430, 0, 0, 0);

-- Accesories
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (41, 7, 'Wireless Keyboard', 'Wireless wave keyboard and mouse combo, includes keyboard and mouse, long battery life, ergonomic wave design', 'fa-keyboard', 90, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (42, 7, 'HDD 1TB', 'External HDD, 1TB SATA, 7200 RPM, 64 MB Cache', 'fa-hdd', 55, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (43, 7, 'HDD 2TB', 'External HDD, 2TB SATA, 7200 RPM, 64 MB Cache', 'fa-hdd', 75, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (44, 7, 'HDD SSD 500', 'External HDD, 512MB SATA III, 520Mb/s', 'fa-hdd', 120, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (45, 7, 'HDD SSD 1TB', 'External HDD, 1TB SATA III, 520Mb/s', 'fa-hdd', 250, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (46, 7, 'Headphones', 'Lightweight over ear headphones with microphone integrated', 'fa-headphones', 45, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (47, 7, 'Wireless Headphones', 'Wireless lightweight over ear headphones with microphone integrated', 'fa-headphones', 120, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (48, 7, 'Compact Printer', 'Wireless All-In-One compact printer', 'fa-print', 265, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (49, 7, 'Portable Power Bank 5', 'Phone charger 5000mAh, portable power bank ultra slim LED', 'fa-battery-full', 50, 0, 0, 0);
INSERT INTO Products (product_id, category, name, description, icon, price, total_score, total_comments, sales) VALUES (50, 7, 'Portable Power Bank 10', 'Phone charger 10000mAh, portable power bank ultra slim LED', 'fa-battery-full', 75, 0, 0, 0);