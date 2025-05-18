function validateForm(form1) {
    console.log("Script đang chạy");
    const bb = document.getElementById(form1);
        // Lấy giá trị từ các input
        const originalPrice = bb.querySelector('input[name="laptop_originalPrice"]').value.trim();
        const currentPrice = bb.querySelector('input[name="laptop_currentPrice"]').value.trim();
        
        // Kiểm tra có phải số nguyên dương (> 0) không
        const numberRegex = /^[1-9]\d*$/;

        if (!numberRegex.test(originalPrice)) {
            alert("Giá Gốc phải là số nguyên dương lớn hơn 0!");
            return false; // chặn form submit
        }

        if (!numberRegex.test(currentPrice)) {
            alert("Giá Hiện Tại phải là số nguyên dương lớn hơn 0!");
            return false; // chặn form submit
        }

        // Nếu kiểm tra ok thì submit form
        console.log("Script cuối trang đã được tải!");
        return true;
        
    }