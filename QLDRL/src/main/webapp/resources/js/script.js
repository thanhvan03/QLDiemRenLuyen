function confirmDeleteHoatDong(hoatdongId) {
    if (confirm('Bạn có chắc chắn muốn xóa Hoạt Động này?')) {
        deleteHoatDong(hoatdongId);
    }
}

function confirmDeleteBaoThieu(baothieuId) {
    if (confirm('Bạn có chắc chắn muốn xóa báo thiếu này?')) {
        deleteBaoThieu(baothieuId);
    }
}

function deleteHoatDong(hoatdongId) {
    fetch(`/QLDRL/tlsv/hoatdongs/delete/${hoatdongId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    })
            .then(res => {
                if (res.ok) {
                    location.reload();
                } else {
                    return res.text().then(text => {
                        throw new Error(text);
                    });
                }
            })
            .catch(err => {
                console.error('Error:', err);
                alert("ERROR: " + err.message);
            });
}


function deleteBaoThieu(baothieuId) {
    fetch(`/QLDRL/api/tlsv/xoabaothieu/${baothieuId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        if (res.ok) {
            location.reload();
        } else {
            res.text().then(text => alert("ERROR: " + text));
        }
    }).catch(err => {
        console.error('Error:', err);
        alert("ERROR");
    });
}


function duyetBaoThieu(baoThieuId, userId) {
    console.log(`Calling duyetBaoThieu with baoThieuId: ${baoThieuId}, userId: ${userId}`);
    fetch(`/QLDRL/api/tlsv/duyetbaothieu/${baoThieuId}/${userId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        if (res.ok) {
            alert("Đã duyệt báo thiếu.");
            location.reload();
        } else {
            alert("Không thể duyệt báo thiếu.");
            console.log('Response status:', res.status);
            console.log('Response text:', res.statusText);
        }
    }).catch(error => {
        console.error('Error:', error);
        alert("Có lỗi xảy ra khi duyệt báo thiếu.");
    });
}


function nhapDiem(hoatDongId, userId) {
    console.log(`Calling nhập điểm with hoatdongId: ${hoatDongId}, userId: ${userId}`);
    fetch(`/QLDRL/api/tlsv/diemrenluyen/nhapdiem/${hoatDongId}/${userId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => {
        if (res.ok) {
            alert("Đã Nhập Điểm");
            location.reload();
        } else {
            alert("Sinh Viên này đã được nhập điểm");
            console.log('Response status:', res.status);
            console.log('Response text:', res.statusText);
        }
    }).catch(error => {
        console.error('Error:', error);
        alert("Có lỗi xảy ra khi nhập điểm");
    });
}


function confirmDeleteEmail(url, id) {
    if (confirm('Bạn có chắc chắn muốn xóa email này?')) {
        deleteEmail(url, id);
    }
}

function deleteEmail(url, id) {
    fetch(url, {
        method: 'delete'
    }).then(res => {
        if (res.status === 204)
            location.reload();
        else
            alert("ERROR");
    });
}
